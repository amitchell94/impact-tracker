package com.codingnomads.impacttracker.logic.user;

import com.codingnomads.impacttracker.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User getUserByUserNameAndPassword(String username, String password);

    User save(User user);
}
