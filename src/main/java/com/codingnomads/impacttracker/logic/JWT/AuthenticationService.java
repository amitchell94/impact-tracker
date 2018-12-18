package com.codingnomads.impacttracker.logic.JWT;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class AuthenticationService {
    private Key tokenKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationService(AuthenticationRepository authenticationRepository){
        this.authenticationRepository = authenticationRepository;
    }

    public Token createToken(int userId){
        String tokenValue = Jwts.builder().setSubject(Integer.toString(userId)).signWith(tokenKey).compact();

        if (tokenIsFromUser(userId, tokenValue)){
            return authenticationRepository.createToken(tokenValue);
        }else{
            return null;
        }
    }
    public void validateToken(String token){
        if(!authenticationRepository.validateToken(token)){
            throw new InvalidTokenException("");
        }
    }

    private boolean tokenIsFromUser(int userId, String tokenValue){
        return Jwts.parser()
                .setSigningKey(tokenKey)
                .parseClaimsJws(tokenValue)
                .getBody()
                .getSubject()
                .equals(Integer.toString(userId));
    }
    public Integer getUserIdFromToken(String tokenValue) {
        return Integer.parseInt(Jwts.parser()
                .setSigningKey(tokenKey)
                .parseClaimsJws(tokenValue)
                .getBody()
                .getSubject());
    }
}






















