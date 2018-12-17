package com.codingnomads.impacttracker.logic.user;

import com.codingnomads.impacttracker.logic.authentication.AuthenticationProvider;
import com.codingnomads.impacttracker.model.Role;
import com.codingnomads.impacttracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationProvider authenticationProvider;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationProvider authenticationProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
    }

    public User getUserByUserName(String username) {

        return userRepository.getUserByUserName(username);
    }

    public User validateUser(User user) {

        User userName = getUserByUserName(user.getUsername());

        if (userName != null &&
                bCryptPasswordEncoder.matches(user.getPassword(), userName.getPassword())) {
            return userName;
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

    public Boolean checkCredentials(String userName, String password) {
        User thisUser = userRepository.getUserByUserName(userName);
        boolean passwordMatch = bCryptPasswordEncoder.matches(password, thisUser.getPassword());
        if(passwordMatch){
            return true;
        }else{
            return false;
        }

    }

    public Integer getCurrentUserId() {

        Authentication authentication = authenticationProvider.getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return getUserByUserName(currentUserName).getId();
        }
        return null;
    }


}
