package fr.iplowplow.foodsearch.controllers;

import fr.iplowplow.foodsearch.dtos.AuthenticateDTO;
import fr.iplowplow.foodsearch.dtos.UserDTO;
import fr.iplowplow.foodsearch.exceptions.AuthenticateException;
import fr.iplowplow.foodsearch.services.authenticate.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

    @RestController
    @RequestMapping("/api/public")
    public class AuthenticateController {

        private final AuthenticateService authenticateService;

        @Autowired
        public AuthenticateController(AuthenticateService authenticateService) {
            this.authenticateService = authenticateService;
        }

        @PostMapping("/authenticate")
        public ResponseEntity<UserDTO> signUp(@Valid @RequestBody AuthenticateDTO authenticateDTO) {
            UserDTO userDTO;
        try{
            userDTO = this.authenticateService.authenticate(authenticateDTO);
        }catch(AuthenticateException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }
}
