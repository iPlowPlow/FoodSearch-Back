package fr.iplowplow.foodsearch.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank ;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    @NotBlank (message="Username cannot be missing or empty")
    private String username;

    @NotBlank (message="Password cannot be missing or empty")
    @Size(min=6, max=16, message="Password must be equal to or greater than 8 characters and less than 16 characters")
    private String password;

    @NotBlank (message="Last  name cannot be missing or empty")
    private String lastName;

    @NotBlank (message="First name cannot be missing or empty")
    private String firstName;


}
