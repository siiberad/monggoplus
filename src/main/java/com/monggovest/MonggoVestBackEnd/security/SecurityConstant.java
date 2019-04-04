package com.monggovest.MonggoVestBackEnd.security;

public class SecurityConstant {
    public static final String SIGN_UP_URL = "/api/**";
    public static final String SECRET = "SecretKeyToGenerateJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 864_000_00;

}
