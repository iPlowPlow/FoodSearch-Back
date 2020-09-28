package fr.iplowplow.foodsearch.services.user;

import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.SignupDTO;
import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.exceptions.UserAlreadyExistException;
import fr.iplowplow.foodsearch.mappers.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

/**
 * Implement UserService.
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

        User userFind = userDAO.findUserByUsername(user.getUsername());

        if(userFind != null){
           throw new UserAlreadyExistException("User Already exit");
        }

        userDAO.save(user);

    }
}
