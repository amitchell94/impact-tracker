package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.InvalidTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid Token")
    @ExceptionHandler(InvalidTokenException.class)
    public void handlesInvalidTokenException(HttpServletRequest request, Exception exception) {
        logger.info("Invalid Token Exception Occurred:: URL=" + request.getRequestURL());
        logger.info("the exception was : =" + exception.getMessage());
    }

}

