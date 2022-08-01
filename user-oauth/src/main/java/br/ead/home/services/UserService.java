package br.ead.home.services;

import br.ead.home.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
public record UserService(UserRepository userRepository) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(user -> User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getRoles())
                        .accountExpired(false)
                        .accountLocked(false)
                        .disabled(false)
                        .credentialsExpired(false)
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Incorrect Username or Password provided"));
    }
}
