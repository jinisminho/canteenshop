package com.spring2020.coffeeshop.controller;

import com.spring2020.coffeeshop.security.JwtTokenProvider;
import com.spring2020.coffeeshop.security.payload.JwtAuthenticationResponse;
import com.spring2020.coffeeshop.security.payload.LoginRequest;
import com.spring2020.coffeeshop.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private AppUserService appUserService;

        @Autowired
        private JwtTokenProvider tokenProvider;

        @PostMapping("/login")
        public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
                String username = loginRequest.getUsername();
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = tokenProvider.generateToken(authentication);
                String role = appUserService.findAppUserByUsername(username).getAppRole().getName().toString();
                JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt,
                                tokenProvider.getUserIdFromJwt(jwt), tokenProvider.getExpiryDateFromJwt(jwt), role,
                                username);
                return ResponseEntity.ok(response);
        }

}
