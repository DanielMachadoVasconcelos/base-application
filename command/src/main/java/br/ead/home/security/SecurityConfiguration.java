package br.ead.home.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                .exceptionHandling()
                .accessDeniedHandler(SecurityConfiguration::handle)
                .and()
                .authorizeHttpRequests()
                .regexMatchers(HttpMethod.POST, "/api/v1/bank-accounts").hasRole("ADMIN")
                .regexMatchers(HttpMethod.POST, "/api/v1/bank-accounts/*/close").hasRole("ADMIN")
                .regexMatchers(HttpMethod.POST, "/api/v1/bank-accounts/*/deposits").hasAnyRole("USER", "ADMIN")
                .regexMatchers(HttpMethod.POST, "/api/v1/bank-accounts/*/withdraws").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .build();
    }

    private static void handle(HttpServletRequest request,
                                     HttpServletResponse response,
                                     AccessDeniedException accessDeniedException) throws IOException {
        log.info("Error = {}, Request = {}, Response = {}",
                accessDeniedException.getMessage(), request.getUserPrincipal(), response.getStatus());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendError(HttpStatus.UNAUTHORIZED.value(), accessDeniedException.getMessage());
        log.error("Access Denied.", accessDeniedException);
    }

    @Bean
    public UserDetailsManager userDetailsService(PasswordEncoder encoder) {

        var user = User
                .withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .authorities("ROLE_USER")
                .build();

        var admin = User
                .withUsername("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .authorities("ROLE_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/resources/css/**")
                .antMatchers("/resources/html/**");
    }
}
