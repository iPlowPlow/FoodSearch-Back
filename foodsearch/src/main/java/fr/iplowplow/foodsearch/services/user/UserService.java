package fr.iplowplow.foodsearch.services.user;

import fr.iplowplow.foodsearch.dtos.SignupDTO;

public interface UserService {

    void createUser(SignupDTO signupDTO);
}
