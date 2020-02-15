package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.dto.JwtResponseDto;
import com.spring2020.staffwebapp.domain.dto.StaffProfileDto;
import com.spring2020.staffwebapp.security.JwtTokenProvider;
import com.spring2020.staffwebapp.security.payload.LoginRequest;
import com.spring2020.staffwebapp.services.StaffProfileService;
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

import static com.spring2020.staffwebapp.domain.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    StaffProfileService staffProfileService;

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
        StaffProfileDto staffProfileDto = staffProfileService.viewStaffProfile(loginRequest.getUsername());
        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setToken(TOKEN_PREFIX + token);
        jwtResponseDto.setStaffProfileDto(staffProfileDto);
        /*=======================*/

        return ResponseEntity.ok(jwtResponseDto);

    }


}
