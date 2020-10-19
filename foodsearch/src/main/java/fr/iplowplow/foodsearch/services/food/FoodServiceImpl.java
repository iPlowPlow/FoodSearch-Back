package fr.iplowplow.foodsearch.services.food;

import fr.iplowplow.foodsearch.daos.FoodDAO;
import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.FoodDTO;
import fr.iplowplow.foodsearch.entitys.Food;
import fr.iplowplow.foodsearch.mappers.FoodMapper;
import fr.iplowplow.foodsearch.mappers.UserMapper;
import fr.iplowplow.foodsearch.services.token.JWTTokenService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{


    private static final FoodMapper FOOD_MAPPER = Mappers.getMapper(FoodMapper.class);

    private final FoodDAO foodDAO;

    @Autowired
    public FoodServiceImpl(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    @Override
    public List<FoodDTO> getFood() {

        List<Food> foodDAOList = this.foodDAO.findAll();

        List<FoodDTO> foodDTOList = foodDAOList.stream()
                .map(x -> FOOD_MAPPER.foodToFoodDTO(x))
                .collect(Collectors.toList());

        return foodDTOList;
    }
}
