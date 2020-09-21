package fr.iplowplow.foodsearch.mappers;
import fr.iplowplow.foodsearch.dtos.signup.SignupDTO;
import fr.iplowplow.foodsearch.entitys.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    /**
     * convert SignupDTO to userDAO
     *
     * @return User
     */
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "firstName", target = "firstName")
    User signupDTOToUser(SignupDTO signupDTO);

}
