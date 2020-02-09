package com.spring2020.staffwebapp.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spring2020.staffwebapp.domain.dto.JwtResponseDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.spring2020.staffwebapp.domain.constants.SecurityConstants.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException
    {
        try
        {
            AppUser credential = new ObjectMapper()
                    .readValue(req.getInputStream(), AppUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credential.getUsername(),
                            credential.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException
    {
        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        
        /*Add token to HEADER*/
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        /*==================*/

        /*Create a copy of token in response body*/
        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setToken(TOKEN_PREFIX + token);
        Gson gson = new Gson();
        String jwtResponseDtoJson = gson.toJson(jwtResponseDto);
        res.setContentType("application/json");
        res.getWriter().write(jwtResponseDtoJson);
        res.getWriter().flush();
        res.getWriter().close();
        /*===================================*/
    }
}