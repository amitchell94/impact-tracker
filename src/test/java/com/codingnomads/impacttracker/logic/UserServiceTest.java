package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.repositories.MySqlRoleRepository;
import com.codingnomads.impacttracker.data.repositories.MySqlUserRepository;
import com.codingnomads.impacttracker.data.repositories.MySqlUserRoleRepository;
import com.codingnomads.impacttracker.logic.user.RoleRepository;
import com.codingnomads.impacttracker.model.User;
import com.codingnomads.impacttracker.logic.user.UserRepository;
import com.codingnomads.impacttracker.logic.user.UserRoleRepository;
import com.codingnomads.impacttracker.logic.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static final String VALID_USERNAME = "User";
    private static final int VALID_USER_ID = 1;
    private User user;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {

        user = new User();
        user.setUsername(VALID_USERNAME);
        user.setId(VALID_USER_ID);
    }

    @Test
    public void whenUserServiceGetUserByUserName_returnUserByUserName() {

        when(userService.getUserByUserName(user.getUsername())).thenReturn(user);
        User userByUserName = userService.getUserByUserName(VALID_USERNAME);

        assertThat(userByUserName).isEqualTo(user);

        @TestConfiguration
        class UserServiceConfiguration {

            @Bean
            public UserService userService(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
                return new UserService(userRepository, roleRepository, userRoleRepository, bCryptPasswordEncoder);
            }
        }
    }

    @Test
    public void whenGettingCurrentUserId_shouldReturnCurrentUserId () {

        Authentication mockAuthentication = Mockito.mock(TestingAuthenticationToken.class);
        SecurityContext mockSecurityContext = Mockito.mock(SecurityContext.class);
        MySqlUserRepository userRepositoryMock = Mockito.mock(MySqlUserRepository.class);
        MySqlRoleRepository roleRepositoryMock = Mockito.mock(MySqlRoleRepository.class);
        MySqlUserRoleRepository userRoleRepositoryMock = Mockito.mock(MySqlUserRoleRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        UserService userServiceMock = Mockito.spy(new UserService(userRepositoryMock,roleRepositoryMock,userRoleRepositoryMock,bCryptPasswordEncoder));
        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
        when(mockAuthentication.getName()).thenReturn(VALID_USERNAME);
        when(userServiceMock.getUserByUserName(VALID_USERNAME)).thenReturn(user);
        SecurityContextHolder.setContext(mockSecurityContext);

        int output = userServiceMock.getCurrentUserId();

        assertThat(output).isEqualTo(VALID_USER_ID);
    }
}
