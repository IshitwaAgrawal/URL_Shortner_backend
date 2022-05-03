package com.ishitwa.url_shortner.model;

import com.ishitwa.url_shortner.DTO.UserResponse;

public class AuthenticationResponse {

    private String jwt;
    private UserResponse user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt, UserResponse user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
