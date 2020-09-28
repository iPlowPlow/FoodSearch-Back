package fr.iplowplow.foodsearch.services.authenticate;

import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.dtos.UserDTO;

public interface AuthenticateService {

    UserDTO authenticate(AuthenticateDTO authenticateDTO);
}
