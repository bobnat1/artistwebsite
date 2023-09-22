package com.project.artistwebsite.security;

import com.project.artistwebsite.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder(11);
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/", "/public-message-dj", "/messages", "/confirm-message", "/process-user", "/dj-contact", "/dj-mix", "/styles/**", "/js/**", "/video/**", "/audio/**", "/images/**", "/register-user").permitAll()
                                .requestMatchers("/student-info","/main-account", "/message-dj", "/download-mix/**", "/change-password")
                                .hasRole("USER")
                                .requestMatchers("/page-admin", "/post-mix", "/delete-mix", "/send-mix", "/message-dj", "/messages", "/confirm-message", "/user-edit", "/change-role", "/message-edit", "/message-delete", "/user-delete", "/posts-edit", "/posts-delete", "/send-posts", "/streams-edit", "/streams-send", "/streams-delete")
                                .hasRole("ADMIN").anyRequest().authenticated())
                .formLogin(
                        form -> form
                                .loginPage("/login-user")
                                .loginProcessingUrl("/login-user")
                                .failureUrl("/error-login")
                                .successForwardUrl("/user-home")
                                .permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );


        return http.build();

    }

}
