package fr.iplowplow.foodsearch.daos;

import fr.iplowplow.foodsearch.entitys.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setup(){
        User user1 = new User(null, "username2","password","lastname","name");
        userDAO.save(user1);
    }


    @Test
    public void shouldCreateUser(){
        User user = new User(null, "username","password","lastname","name");
        userDAO.save(user);

        assertEquals("username",  userDAO.findUserByUsername("username").getUsername());
    }

    @Test
    public void shouldFindUser(){

        assertEquals("username2", userDAO.findUserByUsername("username2").getUsername());
    }

    @Test
    public void shouldNotFindUser(){
        assertNull(userDAO.findUserByUsername("username4"));
    }

}
