package fr.iplowplow.foodsearch.entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name = "username", length = 64, nullable = false)
    private String username;

    @Column(name = "password", length = 16, nullable = false)
    private String password;

    @Column(name = "last_name", length = 64, nullable = false)
    private String lastName;

    @Column(name = "first_name", length = 64, nullable = false)
    private String firstName;


}
