package br.ead.home.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "user")
public class User {

    @Id
    private String personId;

    private String firstName;
    private String lastName;

    private String email;
    private String username;
    private String password;

    private Set<Role> roles;
}
