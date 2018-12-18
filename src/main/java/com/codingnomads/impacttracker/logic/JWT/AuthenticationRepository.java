package com.codingnomads.impacttracker.logic.JWT;

public interface AuthenticationRepository {
    Token createToken(String tokenName);

    boolean validateToken(String token);
}
