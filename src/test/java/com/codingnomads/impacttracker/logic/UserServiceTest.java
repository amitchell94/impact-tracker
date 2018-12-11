package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.logic.user.RoleRepository;
import com.codingnomads.impacttracker.model.User;
import com.codingnomads.impacttracker.logic.user.UserRepository;
import com.codingnomads.impacttracker.logic.user.UserRoleRepository;
import com.codingnomads.impacttracker.logic.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private User user;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {

        user = new User();
        user.setUsername("testuser");
        user.setPassword("password7");
    }

    @Test
    public void whenUserServiceGetUserByUserNameAndPassword_returnUserByUserNameAndPassword() {

        Mockito.when(userService.getUserByUserNameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
        User userByUserNameAndPassword = userService.getUserByUserNameAndPassword("testuser", "password7");

        assertThat(userByUserNameAndPassword).isEqualTo(user);

        @TestConfiguration
        class UserServiceConfiguration {

            @Bean
            public UserService userService(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
                return new UserService(userRepository, roleRepository, userRoleRepository, bCryptPasswordEncoder);
            }
        }
    }
}
