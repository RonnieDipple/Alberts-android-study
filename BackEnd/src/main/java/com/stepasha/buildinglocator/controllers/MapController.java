package com.stepasha.buildinglocator.controllers;



//TODO 38 Art Controller


import com.stepasha.buildinglocator.models.ErrorDetail;
import com.stepasha.buildinglocator.models.Map;
import com.stepasha.buildinglocator.services.MapService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/map")
public class MapController {

    @Autowired
    private MapService mapService;

    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    @ApiOperation(value = "returns all Map",
            response = Map.class,
            responseContainer = "List")
    // http://localhost:2222/map/maps
    @GetMapping(value = "/maps", produces = "application/json")
    ResponseEntity<?> getMaps(){
        logger.info("map/maps Accessed");
        List<Map> maps = mapService.findAll();
        return new ResponseEntity<>(maps, HttpStatus.OK);
    }

    //CREATE
    //http://localhost:2222/map/map
    @PostMapping(value = "/map",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewMap(
            @Valid
            @RequestBody Map newMap){
        newMap = mapService.save(newMap);
        HttpHeaders responseHeader = new HttpHeaders();
        URI newCustomerUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMap.getMapid())
                .toUri();
        responseHeader.setLocation(newCustomerUri);

        return new ResponseEntity<>(null, responseHeader, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retrieve a user based of off user id",
            response = Map.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Map Found",
            response = Map.class), @ApiResponse(code = 404,
            message = "Map Not Found",
            response = ErrorDetail.class)})
    // http://localhost:2222/map/map/1
    @PutMapping(value = "/map/{mapid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateMap(@RequestBody Map updateMap,
                                       @ApiParam(value = "Map id",
                                               required = true,
                                               example = "4")
                                            @PathVariable long mapid){
        mapService.update(updateMap, mapid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // http://localhost:2222/map/map/1
    @DeleteMapping(value = "/map/{mapid}")
    public ResponseEntity<?> deleteMap(@PathVariable long mapid){
        mapService.delete(mapid);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
