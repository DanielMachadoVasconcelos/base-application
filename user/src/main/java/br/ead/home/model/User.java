package br.ead.home.model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private UserAccount account;
}
