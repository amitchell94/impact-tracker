package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.logic.authentication.AuthenticationProvider;
import com.codingnomads.impacttracker.logic.user.UserRepository;
import com.codingnomads.impacttracker.logic.user.UserService;
import com.codingnomads.impacttracker.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static final String VALID_USERNAME = "User";
    private static final int VALID_USER_ID = 1;
    private User user;

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AuthenticationProvider authenticationProviderMock;
    @Mock
    private Authentication authenticationMock;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername(VALID_USERNAME);
        user.setId(VALID_USER_ID);
    }

    @Test
    public void whenUserServiceGetUserByUserName_returnUserByUserName() {
        when(userRepositoryMock.getUserByUserName(VALID_USERNAME)).thenReturn(user);

        User userByUserName = userService.getUserByUserName(VALID_USERNAME);

        assertThat(userByUserName).isEqualTo(user);
    }

    @Test
    public void whenGettingCurrentUserId_shouldReturnCurrentUserId() {
        when(authenticationProviderMock.getAuthentication()).thenReturn(authenticationMock);
        when(authenticationMock.getName()).thenReturn(VALID_USERNAME);
        when(userRepositoryMock.getUserByUserName(VALID_USERNAME)).thenReturn(user);

        int currentUserId = userService.getCurrentUserId();

        assertThat(currentUserId).isEqualTo(VALID_USER_ID);
    }
}
