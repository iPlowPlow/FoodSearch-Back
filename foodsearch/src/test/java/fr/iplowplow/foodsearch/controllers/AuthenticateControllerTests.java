package fr.iplowplow.foodsearch.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.dtos.UserDTO;
import fr.iplowplow.foodsearch.exceptions.AuthenticateException;
import fr.iplowplow.foodsearch.services.authenticate.AuthenticateService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class AuthenticateControllerTests {

    @InjectMocks
    private AuthenticateController authenticateController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticateService authenticateService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticateController).build();
    }

    @Test
    public void shouldAuthenticate() throws Exception {
        AuthenticateDTO authenticateDTO = new AuthenticateDTO("username","aaaa");

       when(authenticateService.authenticate(any())).thenReturn(new UserDTO());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/public/authenticate")
                .content(new ObjectMapper().writeValueAsString(authenticateDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(authenticateService, times(1)).authenticate(any());

    }

    @Test
    public void shouldNotAuthenticate() throws Exception {
        AuthenticateDTO authenticateDTO = new AuthenticateDTO("username","aaaa");

        when(authenticateService.authenticate(any())).thenThrow(new AuthenticateException(""));

        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/public/authenticate")
                .content(new ObjectMapper().writeValueAsString(authenticateDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(authenticateService, times(1)).authenticate(any());

    }
}
