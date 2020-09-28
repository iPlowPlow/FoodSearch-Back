package fr.iplowplow.foodsearch.daos;

import fr.iplowplow.foodsearch.entitys.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserDAO extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);
}
