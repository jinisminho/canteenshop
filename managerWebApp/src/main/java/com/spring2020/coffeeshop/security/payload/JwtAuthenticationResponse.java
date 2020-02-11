package com.spring2020.coffeeshop.security.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static com.spring2020.coffeeshop.util.ConstantUtil.TOKEN_TYPE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = TOKEN_TYPE;
    private Long userId;
    private Date expiryDate;

    public JwtAuthenticationResponse(String accessToken, Long userId, Date expiryTime) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.expiryDate = expiryTime;
    }
}
