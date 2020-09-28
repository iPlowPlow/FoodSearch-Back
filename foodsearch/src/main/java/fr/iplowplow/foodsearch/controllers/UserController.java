package fr.iplowplow.foodsearch.controllers;

import fr.iplowplow.foodsearch.dtos.SignupDTO;
import fr.iplowplow.foodsearch.exceptions.UserAlreadyExistException;
import fr.iplowplow.foodsearch.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param signupDto DTO send to signup
     * @return SignupErrorEnum or OK
     */
    @PostMapping("/user")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupDTO signupDto) {

        try{
            userService.createUser(signupDto);
        }catch( UserAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
