package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.entity.AppUser;
import com.spring2020.staffwebapp.repositories.AppUserRepository;
import com.spring2020.staffwebapp.security.JwtTokenProvider;
import com.spring2020.staffwebapp.security.payload.JwtAuthenticationResponse;
import com.spring2020.staffwebapp.security.payload.LoginRequest;
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
@RequestMapping("/api/auth")
public class AuthController
{

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
    {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        /*Generate token*/
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        /*===============*/

        /*Create response object*/
        Long appUserId = tokenProvider.getUserIdFromJwt(token);
        String username = appUserRepository.findById(appUserId).orElse(new AppUser()).getUsername();
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token,
                appUserId,
                username,
                tokenProvider.getExpiryDateFromJwt(token).getTime(),
                "STAFF");
        /*=======================*/

        return ResponseEntity.ok(response);

    }


}
