package br.ead.home.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
 
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        if (shouldAuthenticateAgainstThirdPartySystem()) {
            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(name, password, List.of());
        } else {
            return null;
        }
    }

    private boolean shouldAuthenticateAgainstThirdPartySystem() {
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}