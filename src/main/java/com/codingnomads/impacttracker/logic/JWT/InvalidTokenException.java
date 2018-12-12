package com.codingnomads.impacttracker.logic.JWT;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message){
        super(message);
    }

}
