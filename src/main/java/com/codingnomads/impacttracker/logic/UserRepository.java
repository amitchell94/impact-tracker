package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.logic.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User getUserByUserNameAndPassword(String username, String password);

    User save(User user);
}
