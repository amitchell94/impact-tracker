package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.AuthenticationService;
import com.codingnomads.impacttracker.logic.JWT.Token;
import com.codingnomads.impacttracker.logic.user.UserService;
import com.codingnomads.impacttracker.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class AuthenticationApiController {
    private Logger logger = LoggerFactory.getLogger(AuthenticationApiController.class);

    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public Token createToken(@RequestBody User user) {
        User userFromDb = userService.validateUser(user);
        if (userFromDb != null) {
            return authenticationService.createToken(userFromDb.getId());
        }
        return null;
    }

}

