package com.stepasha.buildinglocator.services;

import com.stepasha.buildinglocator.exceptions.ResourceFoundException;
import com.stepasha.buildinglocator.exceptions.ResourceNotFoundException;
import com.stepasha.buildinglocator.logging.Loggable;
import com.stepasha.buildinglocator.models.Role;
import com.stepasha.buildinglocator.models.User;
import com.stepasha.buildinglocator.models.UserRoles;
import com.stepasha.buildinglocator.models.Useremail;
import com.stepasha.buildinglocator.repos.RoleRepository;
import com.stepasha.buildinglocator.repos.UserRepository;
import com.stepasha.buildinglocator.view.UserNameCountEmails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Service(value = "userService")
public class UserServiceImpl implements UserService
{

    @Autowired
    UserAuditing userAuditing;
    @Autowired
    private UserRepository userrepos;
    @Autowired
    private RoleRepository rolerepos;

    public User findUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username,
                                           Pageable pageable)
    {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase(),
                                                            pageable);
    }

    @Override
    public List<User> findAll(Pageable pageable)
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll(pageable)
                 .iterator()
                 .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        if (userrepos.findByUsername(user.getUsername()
                                         .toLowerCase()) != null)
        {
            throw new ResourceFoundException(user.getUsername() + " is already taken!");
        }

        User newUser = new User();
        newUser.setProfilepicture(user.getProfilepicture());
        newUser.setUsername(user.getUsername()
                                .toLowerCase());
        newUser.setPasswordNotEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail()
                                    .toLowerCase());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setAge(user.getAge());
        newUser.setLocation(user.getLocation());



        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserroles())
        {
            long id = ur.getRole()
                        .getRoleid();
            Role role = rolerepos.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
            newRoles.add(new UserRoles(newUser,
                                       role));
        }
        newUser.setUserroles(newRoles);

        for (Useremail ue : user.getUseremails())
        {
            newUser.getUseremails()
                   .add(new Useremail(newUser,
                                      ue.getUseremail()));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user,
                       long id)
    {

        User currentUser = findUserById(id);

        if (user.getProfilepicture() != null){
            currentUser.setProfilepicture(user.getProfilepicture());
        }
        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername()
                                        .toLowerCase());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPasswordNotEncrypt(user.getPassword());
        }

        if (user.getPrimaryemail() != null)
        {
            currentUser.setPrimaryemail(user.getPrimaryemail()
                                            .toLowerCase());
        }
        if (currentUser.getFirstname() != null){
            currentUser.setFirstname(user.getFirstname());
        }
        if (currentUser.getLastname() != null){
            currentUser.setLastname(user.getLastname());
        }
        if (currentUser.hasAge){
            currentUser.setAge(user.getAge());
        }
        if (currentUser.getLocation() != null){
            currentUser.setLocation(user.getLocation());
        }

        if (user.getUserroles()
                .size() > 0)
        {
            throw new ResourceFoundException("User Roles are not updated through User. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        if (user.getUseremails()
                .size() > 0)
        {
            for (Useremail ue : user.getUseremails())
            {
                currentUser.getUseremails()
                           .add(new Useremail(currentUser,
                                              ue.getUseremail()));
            }
        }

        return userrepos.save(currentUser);
    }

    @Transactional
    @Override
    public void deleteUserRole(long userid,
                               long roleid)
    {
        userrepos.findById(userid)
                 .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        rolerepos.findById(roleid)
                 .orElseThrow(() -> new ResourceNotFoundException("Role id " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(userid,
                                          roleid)
                     .getCount() > 0)
        {
            rolerepos.deleteUserRoles(userid,
                                      roleid);
        } else
        {
            throw new ResourceNotFoundException("Role and User Combination Does Not Exists");
        }
    }

    @Transactional
    @Override
    public void addUserRole(long userid,
                            long roleid)
    {
        userrepos.findById(userid)
                 .orElseThrow(() -> new EntityNotFoundException("User id " + userid + " not found!"));
        rolerepos.findById(roleid)
                 .orElseThrow(() -> new EntityNotFoundException("Role id " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(userid,
                                          roleid)
                     .getCount() <= 0)
        {
            rolerepos.insertUserRoles(userAuditing.getCurrentAuditor()
                                                  .get(),
                                      userid,
                                      roleid);
        } else
        {
            throw new EntityNotFoundException("Role and User Combination Already Exists");
        }
    }

    @Override
    public List<UserNameCountEmails> getCountUserEmails()
    {
        return userrepos.getCountUserEmails();
    }
}
