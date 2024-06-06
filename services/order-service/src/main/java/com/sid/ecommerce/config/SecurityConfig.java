package com.sid.ecommerce.config;

/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;*/

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/api/v1/orders").permitAll())
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                //.oauth2ResourceServer(o2->o2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                //.headers(h->h.frameOptions(fo->fo.disable()))
                //.csrf(csrf->csrf.ignoringRequestMatchers("/h2-console/**"))
                .build();
    }*/

}
