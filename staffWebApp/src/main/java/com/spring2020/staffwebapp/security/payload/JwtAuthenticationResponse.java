package com.spring2020.staffwebapp.security.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.spring2020.staffwebapp.domain.constants.SecurityConstants.TOKEN_PREFIX;


@Getter
@Setter
@NoArgsConstructor
public class JwtAuthenticationResponse
{

    private String accessToken;
    private String tokenType = TOKEN_PREFIX;
    private Long userId;
    private String username;
    private long expiryTime;
    private String role;

    public JwtAuthenticationResponse(String accessToken, Long userId, String username, long expiryTime, String role)
    {
        this.accessToken = accessToken;
        this.userId = userId;
        this.username = username;
        this.expiryTime = expiryTime;
        this.role = role;
    }
}
