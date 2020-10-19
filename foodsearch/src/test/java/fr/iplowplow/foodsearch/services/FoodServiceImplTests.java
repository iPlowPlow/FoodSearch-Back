package fr.iplowplow.foodsearch.services;

import fr.iplowplow.foodsearch.daos.FoodDAO;
import fr.iplowplow.foodsearch.dtos.FoodDTO;
import fr.iplowplow.foodsearch.entitys.Food;
import fr.iplowplow.foodsearch.services.food.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FoodServiceImplTests {


    @Autowired
    private FoodService foodService;

    @MockBean
    private FoodDAO foodDAO;

    @Test
    void shouldGetFoodSuccess(){

        Food food = new Food(456L, "Italien");
        Food food2 = new Food(459L, "Jap");

        List<Food> foodList = new ArrayList<>();
        foodList.add(food);
        foodList.add(food2);

        when(foodDAO.findAll()).thenReturn(foodList);

        List<FoodDTO> foodDTOList = foodService.getFood();
        assertEquals("Italien", foodDTOList.get(0).getLibelle());
        assertEquals("Jap", foodDTOList.get(1).getLibelle());

    }
}
