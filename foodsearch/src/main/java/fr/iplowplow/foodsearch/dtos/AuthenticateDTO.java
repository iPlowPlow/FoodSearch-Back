package fr.iplowplow.foodsearch.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDTO {

    @NotBlank(message="Username cannot be missing or empty")
    private String username;

    @NotBlank (message="Password cannot be missing or empty")
    private String password;
}
