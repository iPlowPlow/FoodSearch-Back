package fr.iplowplow.foodsearch.services;

import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.exceptions.AuthenticateException;
import fr.iplowplow.foodsearch.services.authenticate.AuthenticateService;
import fr.iplowplow.foodsearch.services.token.JWTTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class AuthenticateServiceImplTests {

    @Autowired
    private AuthenticateService authenticateService;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private JWTTokenService jwtTokenService;

    @Test
    void shouldAuthenticateSuccess(){

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
    void shouldAuthenticateFail(){

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
