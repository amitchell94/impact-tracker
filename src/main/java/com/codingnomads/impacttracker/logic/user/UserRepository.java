package com.codingnomads.impacttracker.logic.user;

import com.codingnomads.impacttracker.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User getUserByUserName(String username);

    User save(User user);
}
