package fr.iplowplow.foodsearch.services;

import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.signup.SignupDTO;
import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.exceptions.UserAlreadyExistException;
import fr.iplowplow.foodsearch.services.user.UserService;
import fr.iplowplow.foodsearch.services.user.UserServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@WebMvcTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {

    @Autowired
    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        userService = new UserServiceImpl(userDAO);
        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
    }

    @Test
    public void shouldCreateUserSuccess(){

        User user = new User(new Long(1),"username2","passsword","lastname","name");
        List<User> l = new ArrayList<>();
        when(userDAO.findByUsername(any())).thenReturn(l);

        SignupDTO signupDTO = new SignupDTO("username2","abcdjfdshfdk","lastname","firstname");

        try{
           userService.createUser(signupDTO);
        }catch (UserAlreadyExistException e){
            fail();
        }

       verify(userDAO, times(1)).save(any());

    }

    @Test
    public void shouldCreateUserFail(){

        User user = new User(new Long(1),"username2","passsword","lastname","name");
        List<User> l = new ArrayList<>();
        l.add(user);
        when(userDAO.findByUsername(any())).thenReturn(l);

        SignupDTO signupDTO = new SignupDTO("username2","abcdjfdshfdk","lastname","firstname");

        try{
            userService.createUser(signupDTO);
        }catch (UserAlreadyExistException e){
            verify(userDAO, times(1)).findByUsername(any());

        }

        verify(userDAO, times(0)).save(any());

    }

}
