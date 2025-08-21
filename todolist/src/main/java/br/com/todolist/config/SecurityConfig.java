package br.com.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF (importante para APIs stateless)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/").permitAll() // Permite acesso sem autenticação a /users/
                        .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
                )
                .httpBasic(withDefaults()) // Habilita autenticação básica (por enquanto)
                .build();
    }
}