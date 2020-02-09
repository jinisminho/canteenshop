package com.spring2020.staffwebapp.services.impl;

import com.spring2020.staffwebapp.domain.entity.AppUser;
import com.spring2020.staffwebapp.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository applicationUserRepository)
    {
        this.appUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        Optional<AppUser> appUserOptional = appUserRepository.findAppUserByUsername(username);
        if (appUserOptional.isPresent())
        {
            AppUser applicationUser = appUserOptional.get();
            return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
        }

        throw new UsernameNotFoundException(username);
    }
}