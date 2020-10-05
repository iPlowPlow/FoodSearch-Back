package fr.iplowplow.foodsearch.services;

import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.SignupDTO;
import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.exceptions.UserAlreadyExistException;
import fr.iplowplow.foodsearch.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserServiceImplTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @Test
    void shouldCreateUserSuccess(){

        when(userDAO.findUserByUsername(any())).thenReturn(null);

        SignupDTO signupDTO = new SignupDTO("username200","password","lastname","firstname");

        try{
           userService.createUser(signupDTO);
        }catch (UserAlreadyExistException e){
            fail();
        }

       verify(userDAO, times(1)).save(any());

    }

    @Test
    void shouldCreateUserFail(){

        User user = new User(null,"username2","passsword","lastname","name");

        when(userDAO.findUserByUsername(any())).thenReturn(user);

        SignupDTO signupDTO = new SignupDTO("username2","abcdjfdshfdk","lastname","firstname");

        try{
            userService.createUser(signupDTO);
        }catch (UserAlreadyExistException e){
            verify(userDAO, times(1)).findUserByUsername(any());

        }

        verify(userDAO, times(0)).save(any());

    }

}
