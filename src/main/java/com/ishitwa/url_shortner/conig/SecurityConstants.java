package com.ishitwa.url_shortner.conig;

public class SecurityConstants {
    public static final String SECRET="secret";
    public static final long EXPIRATION_TIME = 900_000;//15 MINUTES
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/signup";
}
