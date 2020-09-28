package fr.iplowplow.foodsearch.services.token;

import fr.iplowplow.foodsearch.entitys.User;
import io.jsonwebtoken.Claims;

public interface JWTTokenService {
    String createJWT(User user);
    Claims decodeJWT(String jwt);
}
