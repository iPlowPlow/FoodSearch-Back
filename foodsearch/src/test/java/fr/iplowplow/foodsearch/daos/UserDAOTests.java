package fr.iplowplow.foodsearch.daos;

import fr.iplowplow.foodsearch.entitys.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@DataJpaTest
class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @BeforeEach
    public void setup(){
        User user1 = new User(null, "username2","password","lastname","name");
        userDAO.save(user1);
    }


    @Test
    void shouldCreateUser(){
        User user = new User(null, "username","password","lastname","name");
        userDAO.save(user);

        assertEquals("username",  userDAO.findUserByUsername("username").getUsername());
    }

    @Test
    void shouldFindUser(){

        assertEquals("username2", userDAO.findUserByUsername("username2").getUsername());
    }

    @Test
    void shouldNotFindUser(){
        assertNull(userDAO.findUserByUsername("username4"));
    }

}
