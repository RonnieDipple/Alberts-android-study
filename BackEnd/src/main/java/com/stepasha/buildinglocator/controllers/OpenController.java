package com.stepasha.buildinglocator.controllers;

import com.stepasha.buildinglocator.logging.Loggable;
import com.stepasha.buildinglocator.models.User;
import com.stepasha.buildinglocator.models.UserMinimum;
import com.stepasha.buildinglocator.models.UserRoles;
import com.stepasha.buildinglocator.services.RoleService;
import com.stepasha.buildinglocator.services.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

// TODO 26 creating user
// TODO CU 1

@Loggable
@RestController
@Api(tags = {"OpenEndpoint"})
public class OpenController
{
    private static final Logger logger = LoggerFactory.getLogger(OpenController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // Create the user and Return the access token
    // http://localhost:2222/createnewuser
    // Just create the user
    // http://localhost:2222/createnewuser?access=false
    //
    // {
    //     "username" : "test1",
    //     "password" : "test1",
    //     "primaryemail" : "home@local.house"
    // }

    private String getPort(HttpServletRequest httpServletRequest)
    {
        if (httpServletRequest.getServerName()
                              .equalsIgnoreCase("localhost"))
        {
            return ":" + httpServletRequest.getLocalPort();
        } else
        {
            return "";
        }
    }

    @PostMapping(value = "/createnewuser",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest httpServletRequest,
                                        @RequestParam(defaultValue = "true")
                                                boolean getaccess,
                                        @Valid
                                        @RequestBody
                                                UserMinimum newminuser) throws URISyntaxException
    {
        logger.trace(httpServletRequest.getMethod()
                                       .toUpperCase() + " " + httpServletRequest.getRequestURI() + " accessed");

        // Create the user
        User newuser = new User();
        newuser.setProfilepicture(newminuser.getProfilepicture());
        newuser.setUsername(newminuser.getUsername());
        newuser.setPassword(newminuser.getPassword());
        newuser.setPrimaryemail(newminuser.getPrimaryemail());
        newuser.setFirstname(newminuser.getFirstname());
        newuser.setLastname(newminuser.getLastname());
        newuser.setAge(newminuser.getAge());
        newuser.setLocation(newminuser.getLocation());
//TODO CU 1a (auto defaults to user)(requires model UserMinimum)
        ArrayList<UserRoles> newRoles = new ArrayList<>();
        newRoles.add(new UserRoles(newuser,
                                   roleService.findByName("user")));
        newuser.setUserroles(newRoles);

        newuser = userService.save(newuser);

        // set the location header for the newly created resource - to another controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
                                                    .buildAndExpand(newuser.getUserid())
                                                    .toUri();
        responseHeaders.setLocation(newUserURI);

        String theToken = "";
        if (getaccess)
        {
            // return the access token
            RestTemplate restTemplate = new RestTemplate();
            String requestURI = "http://" + httpServletRequest.getServerName() + getPort(httpServletRequest) + "/login";

            List<MediaType> acceptableMediaTypes = new ArrayList<>();
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(acceptableMediaTypes);
            headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
                                 System.getenv("OAUTHCLIENTSECRET"));

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type",
                    "password");
            map.add("scope",
                    "read write trust");
            map.add("username",
                    newminuser.getUsername());
            map.add("password",
                    newminuser.getPassword());

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
                                                                                 headers);

            theToken = restTemplate.postForObject(requestURI,
                                                  request,
                                                  String.class);
        } else
        {
            // nothing;
        }
        return new ResponseEntity<>(theToken,
                                    responseHeaders,
                                    HttpStatus.CREATED);
    }

    @ApiIgnore
    @GetMapping("favicon.ico")
    void returnNoFavicon()
    {
        logger.trace("favicon.ico endpoint accessed!");
    }
}
