package fr.iplowplow.foodsearch.services;

import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.exceptions.AuthenticateException;
import fr.iplowplow.foodsearch.services.authenticate.AuthenticateService;
import fr.iplowplow.foodsearch.services.authenticate.AuthenticateServiceImpl;
import fr.iplowplow.foodsearch.services.token.JWTTokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class AuthenticateServiceImplTests {

    @Autowired
    private AuthenticateService authenticateService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private JWTTokenService jwtTokenService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        authenticateService = new AuthenticateServiceImpl(userDAO, jwtTokenService);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticateService).build();
    }

    @Test
    public void shouldAuthenticateSuccess(){

        User user = new User(null,"username10","password","lastname","name");
        when(userDAO.findUserByUsernameAndPassword(any(), any())).thenReturn(user);
        when(jwtTokenService.createJWT(any())).thenReturn("untoken");

        AuthenticateDTO authenticateDTO = new AuthenticateDTO("username10","password");

        try{
            authenticateService.authenticate(authenticateDTO);
        }catch (AuthenticateException e){
            fail();
        }

        verify(jwtTokenService, times(1)).createJWT(any());

    }

    @Test
    public void shouldAuthenticateFail(){

        User user = new User(null,"username2","password","lastname","name");
        when(userDAO.findUserByUsernameAndPassword(any(), any())).thenReturn(null);

        AuthenticateDTO authenticateDTO = new AuthenticateDTO("username2","password");

        try{
            authenticateService.authenticate(authenticateDTO);
        }catch (AuthenticateException e){
            verify(userDAO, times(1)).findUserByUsernameAndPassword(any(),any());
        }
        verify(jwtTokenService, times(0)).createJWT(any());
    }



}
