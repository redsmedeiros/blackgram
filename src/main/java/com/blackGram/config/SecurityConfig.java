package com.blackGram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    //retorna um objeto SecurityFilterChain que representa uma cadeia de filtros de segurança que serão aplicados pelo Spring Security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{ //usada para configurar a segurança do aplicativo e permite que você defina como as solicitações HTTP serão tratadas pelo Spring Security

        http.csrf().disable()//desabilita a proteção contra CSRF (Cross-Site Request Forgery) na aplicação
            .authorizeHttpRequests((authorize)-> authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .anyRequest().authenticated()
            ).httpBasic(Customizer.withDefaults()); //onfigura o tipo de autenticação que será utilizada, que nesse caso é a autenticação básica via HTTP.

        return http.build();
    }

    
}
