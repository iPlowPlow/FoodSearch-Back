package fr.iplowplow.foodsearch.services.authenticate;

import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.dtos.UserDTO;
import fr.iplowplow.foodsearch.entitys.User;
import fr.iplowplow.foodsearch.exceptions.AuthenticateException;
import fr.iplowplow.foodsearch.mappers.UserMapper;
import fr.iplowplow.foodsearch.services.token.JWTTokenService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implement AuthenticateService.
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {


    private static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    private final UserDAO userDAO;

    private final JWTTokenService jwtTokenService;

    @Autowired
    public AuthenticateServiceImpl(UserDAO userDAO, JWTTokenService jwtTokenService) {
        this.userDAO = userDAO;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public UserDTO authenticate(AuthenticateDTO authenticateDTO) {

        User user = USER_MAPPER.authenticateDTOToUser(authenticateDTO);
        User userFind = userDAO.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        if(userFind == null){
            throw new AuthenticateException("Wrong Username/Password");
        }

        String token = jwtTokenService.createJWT(userFind);
        return USER_MAPPER.userAndTokenToUserDTO(userFind, token);

    }

}
