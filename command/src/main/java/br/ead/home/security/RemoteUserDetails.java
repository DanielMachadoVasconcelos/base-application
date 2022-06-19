package br.ead.home.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
public class RemoteUserDetails {
    String username;
    String password;
    List<GrantedAuthority> authorities;
}
