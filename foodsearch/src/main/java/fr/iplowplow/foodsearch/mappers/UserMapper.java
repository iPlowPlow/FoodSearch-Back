package fr.iplowplow.foodsearch.mappers;
import fr.iplowplow.foodsearch.daos.UserDAO;
import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.dtos.SignupDTO;
import fr.iplowplow.foodsearch.dtos.UserDTO;
import fr.iplowplow.foodsearch.entitys.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    /**
     * convert SignupDTO to UserDAO
     *
     * @return User
     */
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    User signupDTOToUser(SignupDTO signupDTO);

    /**
     * Convert AuthenticateDTO to UserDAO
     */
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    User authenticateDTOToUser(AuthenticateDTO authenticateDTO);

    /**
     * Convert AuthenticateDTO to UserDAO
     */
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "token", target = "token")
    UserDTO userAndTokenToUserDTO(User user, String token);


}
