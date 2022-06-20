package br.ead.home.model;

import lombok.*;

import javax.persistence.Table;
import java.util.List;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_account")
public class UserAccount {

    private String username;
    private String password;
    private List<Role> roles;

}
