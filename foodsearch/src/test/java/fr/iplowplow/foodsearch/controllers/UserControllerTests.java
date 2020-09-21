package fr.iplowplow.foodsearch.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iplowplow.foodsearch.controllers.UserController;
import fr.iplowplow.foodsearch.dtos.signup.SignupDTO;
import fr.iplowplow.foodsearch.exceptions.UserAlreadyExistException;
import fr.iplowplow.foodsearch.services.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void shouldCreateUser() throws Exception {

        SignupDTO signupDTO = new SignupDTO("username","abcdjfdshfdk","lastname","firstname");

        doNothing().when(userService).createUser(any());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/user")
                .content(new ObjectMapper().writeValueAsString(signupDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(any());

    }

    @Test
    public void shouldNotCreateUser() throws Exception {

        SignupDTO signupDTO = new SignupDTO("username","abcdjfdshfdk","lastname","firstname");

        doThrow(new UserAlreadyExistException("Already")).when(userService).createUser(any());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/user")
                .content(new ObjectMapper().writeValueAsString(signupDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService, times(1)).createUser(any());

    }

}
