package com.spring2020.customerapp.security.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static com.spring2020.customerapp.util.ConstantUtil.TOKEN_TYPE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = TOKEN_TYPE;
    private Long userId;
    private long expiryDate;
    private String role;

    public JwtAuthenticationResponse(String accessToken, Long userId, long expiryTime, String role) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.expiryDate = expiryTime;
        this.role = role;
    }
}
