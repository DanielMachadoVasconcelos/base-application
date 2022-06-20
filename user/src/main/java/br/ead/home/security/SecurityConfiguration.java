package br.ead.home.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .regexMatchers(HttpMethod.POST, "/api/v1/bank-accounts").hasRole("SUPER_ROOT")
                .regexMatchers(HttpMethod.POST, "/api/v1/bank-accounts/*/close").hasRole("SUPER_ROOT")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .build();
    }

    @Bean
    public UserDetailsManager userDetailsService(PasswordEncoder encoder) {

        var admin = User
                .withUsername("admin")
                .password(encoder.encode("password"))
                .roles("SUPER_ROOT")
                .authorities("ROLE_SUPER_ROOT")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
