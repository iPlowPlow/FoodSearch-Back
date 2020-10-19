package fr.iplowplow.foodsearch.daos;

import fr.iplowplow.foodsearch.entitys.Food;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@DataJpaTest
public class FoodDAOTests {

    @Autowired
    private FoodDAO foodDAO;

    @Test
    void shouldReturnAll(){
        List<Food> foodList = foodDAO.findAll();
        assertNotNull(foodList);
    }

}
