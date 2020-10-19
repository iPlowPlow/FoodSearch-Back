package fr.iplowplow.foodsearch.controllers;

import fr.iplowplow.foodsearch.dtos.FoodDTO;
import fr.iplowplow.foodsearch.entitys.Food;
import fr.iplowplow.foodsearch.services.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/public")
public class FoodController {

        private final FoodService foodService;

        @Autowired
        public FoodController(FoodService foodService) {
            this.foodService = foodService;
        }

        @GetMapping("/food")
        public ResponseEntity<List<FoodDTO>> getFood() {


            List<FoodDTO> result = foodService.getFood();

            return new ResponseEntity<>(result, HttpStatus.OK);

        }
    }
