package fr.iplowplow.foodsearch.services.user;

import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.signup.SignupDTO;
import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.exceptions.UserAlreadyExistException;
import fr.iplowplow.foodsearch.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implent UserService.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(SignupDTO signupDTO) {

        User user= USER_MAPPER.signupDTOToUser(signupDTO);

        List<User> userList = userDAO.findByUsername(user.getUsername());

        if(userList.size()!=0){
           throw new UserAlreadyExistException("User Already exit");
        }

        userDAO.save(user);

    }
}
