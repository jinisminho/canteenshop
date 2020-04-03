package com.spring2020.customerapp.controller;

import com.spring2020.customerapp.domain.entity.AppUser;
import com.spring2020.customerapp.repository.AppUserRepository;
import com.spring2020.customerapp.security.JwtTokenProvider;
import com.spring2020.customerapp.security.payload.JwtAuthenticationResponse;
import com.spring2020.customerapp.security.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        Long appUserId = tokenProvider.getUserIdFromJwt(jwt);
        AppUser appUser = appUserRepository.findById(appUserId).orElse(null);
        String role = "";
        if(appUser != null) {
            role = appUser.getAppRole().getName().name();
        }

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt,
                tokenProvider.getUserIdFromJwt(jwt),
                tokenProvider.getExpiryDateFromJwt(jwt).getTime(),
                role
        );
        return ResponseEntity.ok(response);
    }


}
