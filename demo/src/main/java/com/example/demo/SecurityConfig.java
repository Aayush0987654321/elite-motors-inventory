package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
   @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // 1. Allow the webpage and static files
            // Add "/css/**", "/js/**", and "/*.html" to the permitAll list
.requestMatchers("/", "/index.html", "/api/me", "/api/cars", "/static/**", "/css/**", "/js/**").permitAll()
            // 2. Allow everyone to see the list of cars (GET request)
            .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/cars").permitAll()
            // 3. Allow everyone to send an inquiry (POST request)
            .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/inquiries").permitAll()
            // 4. Everything else (Delete, Edit, View Inbox) requires login
            .anyRequest().authenticated()
        )
        .formLogin(login -> login.defaultSuccessUrl("/index.html", true))
        .logout(withDefaults());
    return http.build();
}

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("password123").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(admin);
    }
}