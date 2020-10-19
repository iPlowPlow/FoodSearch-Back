package fr.iplowplow.foodsearch.controllers;


import com.google.gson.GsonBuilder;
import fr.iplowplow.foodsearch.dtos.FoodDTO;
import com.google.gson.Gson;
import fr.iplowplow.foodsearch.services.food.FoodService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class FoodControllerTests {

    @InjectMocks
    private FoodController foodController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;

    @Test
    void shouldSuccess() throws Exception {
        FoodDTO foodDTO = new FoodDTO("Italien");
        FoodDTO foodDTO2 = new FoodDTO("Japonais");
        List<FoodDTO> foodDTOList = new ArrayList<>();
        foodDTOList.add(foodDTO);
        foodDTOList.add(foodDTO2);

        when(foodService.getFood()).thenReturn(foodDTOList);
        Gson gson = new GsonBuilder().serializeNulls().create();

        mockMvc.perform( MockMvcRequestBuilders
                .get("/api/public/food")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(foodDTOList), true))
                .andExpect(status().isOk()).andReturn();

        verify(foodService, times(1)).getFood();

    }
}
