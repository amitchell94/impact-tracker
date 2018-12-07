package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.logic.Role;
import com.codingnomads.impacttracker.logic.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getUserByUserNameAndPassword(String username, String password) {

        return userRepository.getUserByUserNameAndPassword(username, password);
    }

    public User validateUser(User user) {

        User userNameAndPassword = getUserByUserNameAndPassword(user.getUsername(), user.getPassword());

        if (userNameAndPassword != null &&
                bCryptPasswordEncoder.matches(user.getPassword(), userNameAndPassword.getPassword())) {
            return userNameAndPassword;
        } else {
            return null;
        }
    }

    @Transactional
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("USER");

        User savedUser = userRepository.save(user);
        userRoleRepository.addUserRoleLink(savedUser.getId(), userRole.getId());
    }
}
