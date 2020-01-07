package com.stepasha.buildinglocator.services;

import com.stepasha.buildinglocator.models.User;
import com.stepasha.buildinglocator.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//TODO 22 SecurityImpl
// TODO AUTH 3 (helps logining in)

@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userrepos;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username.toLowerCase());
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername()
                                                                          .toLowerCase(),
                                                                      user.getPassword(),
                                                                      user.getAuthority());
    }
}
