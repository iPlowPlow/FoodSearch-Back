package fr.iplowplow.foodsearch.daos;

import fr.iplowplow.foodsearch.entitys.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodDAO extends CrudRepository<Food, Long> {

    List<Food> findAll();

}
