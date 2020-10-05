package fr.iplowplow.foodsearch.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iplowplow.foodsearch.dtos.SignupDTO;
import fr.iplowplow.foodsearch.exceptions.UserAlreadyExistException;
import fr.iplowplow.foodsearch.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldCreateUser() throws Exception {

        SignupDTO signupDTO = new SignupDTO("username","abcdjfdshfdk","lastname","firstname");

        doNothing().when(userService).createUser(any());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/public/user")
                .content(new ObjectMapper().writeValueAsString(signupDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(any());

    }

    @Test
    void shouldNotCreateUser() throws Exception {

        SignupDTO signupDTO = new SignupDTO("username","abcdjfdshfdk","lastname","firstname");

        doThrow(new UserAlreadyExistException("Already")).when(userService).createUser(any());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/public/user")
                .content(new ObjectMapper().writeValueAsString(signupDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService, times(1)).createUser(any());

    }

}
