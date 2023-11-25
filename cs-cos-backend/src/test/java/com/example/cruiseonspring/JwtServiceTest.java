package com.example.cruiseonspring;

import com.example.cruiseonspring.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {
    private final JwtService jwtService = new JwtService();
    private UserDetails userDetails;
    private String token;
    String SECRET_KEY;

    @BeforeEach
    public void setup() {
        userDetails = new User("user", "password", new ArrayList<>());
        SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
        jwtService.setSECRET_KEY(SECRET_KEY);
        token = jwtService.generateToken(userDetails);
    }

    @Test
    public void extractUsernameReturnsCorrectUsername() {
        String username = jwtService.extractUsername(token);
        assertEquals(userDetails.getUsername(), username);
    }

    @Test
    public void generateTokenReturnsValidToken() {
        String generatedToken = jwtService.generateToken(userDetails);
        assertNotNull(generatedToken);
        assertEquals(userDetails.getUsername(), jwtService.extractUsername(generatedToken));
    }

    @Test
    public void isTokenValidReturnsTrueForValidToken() {
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    public void isTokenValidReturnsFalseForInvalidToken() {
        String invalidToken = token + "invalid";
        assertThrows(ExpiredJwtException.class, () -> jwtService.isTokenValid(getExpiredToken(), userDetails));
    }

    @Test
    public void isTokenValidReturnsFalseForExpiredToken() {
        assertThrows(ExpiredJwtException.class, () -> jwtService.isTokenValid(getExpiredToken(), userDetails));
    }

    private String getExpiredToken() {
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() - 10))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}