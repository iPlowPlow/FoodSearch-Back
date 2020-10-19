package fr.iplowplow.foodsearch.mappers;

import fr.iplowplow.foodsearch.dtos.FoodDTO;
import fr.iplowplow.foodsearch.entitys.Food;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FoodMapper {

    /**
     * convert Food to FoodDTO
     *
     * @return User
     */
    @Mapping(source = "libelle", target = "libelle")
    FoodDTO foodToFoodDTO(Food food);


}
