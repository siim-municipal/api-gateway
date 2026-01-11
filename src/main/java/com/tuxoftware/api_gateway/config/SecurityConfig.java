package com.tuxoftware.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // Integración con el CORS del Gateway
                .cors(Customizer.withDefaults())
                .authorizeExchange(exchanges -> exchanges
                        // Permitir endpoints de monitoreo sin token
                        .pathMatchers("/actuator/**").permitAll()
                        // Validar token para lo demás
                        .anyExchange().authenticated()
                )
                // Configuración reactiva del Resource Server
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
