package com.stepasha.buildinglocator.services;

import com.stepasha.buildinglocator.logging.Loggable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

//TODO 15 Auditing

@Loggable
@Component
public class UserAuditing implements AuditorAware<String>
{
    @Override
    public Optional<String> getCurrentAuditor()
    {
        String uname;
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null)
        {
            uname = authentication.getName();
        } else
        {
            uname = "SYSTEM";
        }
        return Optional.of(uname);
    }
}
