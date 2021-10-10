package com.atm.emulator.config.security;

import com.atm.emulator.exception.BaseException;
import com.atm.emulator.model.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class JwtService {

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    String JWT_SECRET = "6v9y\\$B&E)H@McQfThWmZq4t7w!z%C*F-JaNdRgUkXp2r5u8x/A?D(G+KbPeShVmY";
    byte[] signingKey = JWT_SECRET.getBytes();
    String AUTH_LOGIN_URL = "/api/auth/token";
    // JWT token defaults
    public static String TOKEN_HEADER = "Authorization";
    public static String TOKEN_PREFIX = "Bearer ";
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "secure-api";
    String TOKEN_AUDIENCE = "secure-app";
    //For test is considered for 1 hours.
    Long TOKEN_LIFE_TIME = 3600000 * 1L; // 1 hour;
    public static Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private Long duration = 40L;


    public String getToken(UserEntity user, String userName) {
        boolean expiration = true;

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("type", TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIFE_TIME))
                .claim("resetPassRequired", expiration)
                .claim("userName", userName)
                .compact();
    }

    public Jws<Claims> parseToken(String token, HttpServletResponse response) throws IOException {
        //todo: exception handler
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        } catch (Exception e) {
          //  response.sendError(HttpStatus.UNAUTHORIZED.value(), "Access Denied!");
           throw  new  BaseException("Access Denied!",HttpStatus.UNAUTHORIZED.value());
        }

    }


}
