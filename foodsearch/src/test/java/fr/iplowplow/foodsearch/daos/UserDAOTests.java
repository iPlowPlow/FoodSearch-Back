package fr.iplowplow.foodsearch.daos;

import fr.iplowplow.foodsearch.entitys.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setup(){
        User user1 = new User(new Long(1),"username2","passsword","lastname","name");
        userDAO.save(user1);
    }


    @Test
    public void shouldCreateUser(){
        User user = new User(new Long(1), "username","passsword","lastname","name");
        userDAO.save(user);
        assertEquals(1,  userDAO.findByUsername("username").size());
    }

    @Test
    public void shouldFindUser(){
        assertEquals(1, userDAO.findByUsername("username2").size());
    }

    @Test
    public void shouldNotFindUser(){
        assertEquals(0,userDAO.findByUsername("username4").size());
    }

}
