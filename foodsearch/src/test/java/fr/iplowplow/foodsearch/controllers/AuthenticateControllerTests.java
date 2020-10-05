package fr.iplowplow.foodsearch.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.dtos.UserDTO;
import fr.iplowplow.foodsearch.exceptions.AuthenticateException;
import fr.iplowplow.foodsearch.services.authenticate.AuthenticateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class AuthenticateControllerTests {

    @InjectMocks
    private AuthenticateController authenticateController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticateService authenticateService;

    @Test
    void shouldAuthenticate() throws Exception {
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
    void shouldNotAuthenticate() throws Exception {
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
