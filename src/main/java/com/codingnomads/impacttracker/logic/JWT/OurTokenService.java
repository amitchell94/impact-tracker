package com.codingnomads.impacttracker.logic.JWT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OurTokenService {
    private Logger logger = LoggerFactory.getLogger(OurTokenService.class);

    @Autowired
    private TokenRepository tokenRepository;

    public Token createApiToken(Credentials credentials) {
        Token token = new Token();
        token.setValue(hashCode(credentials));
        return tokenRepository.save(token);
    }

    private String hashCode(Credentials credentials) {
        return String.valueOf(credentials.hashCode());
    }

    public void validateTokenByValue(String tokenValue){
        if(!tokenRepository.existsByValue(tokenValue)){
            logger.info("Token isn't valid");
            throw new InvalidTokenException("Invalid Token");
        }
    }


}
