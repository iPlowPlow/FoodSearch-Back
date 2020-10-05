package fr.iplowplow.foodsearch.services;

import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.services.token.JWTTokenService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class JWTTokenServiceImplTests {

    @Autowired
    private JWTTokenService jwtTokenService;


    @Test
    void shouldCreateTokenSuccess(){

        User user = new User(null,"username2","passsword","lastname","name");
        String token = null;
        try{
            token = jwtTokenService.createJWT(user);
        }catch (Exception e){
            fail();
        }

        assertNotNull(token);

    }

    @Test
    void shouldDecodeTokenSuccess(){

        User user = new User(null,"username2","passsword","lastname","name");
        String token = null;
        Claims claims = null;
        try{
            token = jwtTokenService.createJWT(user);
            claims = jwtTokenService.decodeJWT(token);
        }catch (Exception e){
            fail();
        }

        assertNotNull(claims);
        assertEquals("username2", claims.get("username").toString());
        assertFalse((Boolean) claims.get("admin"));


    }


}
