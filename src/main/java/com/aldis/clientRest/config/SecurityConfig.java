package com.aldis.clientRest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors(cors -> cors.disable()) // Ajustar si es necesario
				.csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF
				.authorizeHttpRequests(auth -> auth
						.anyRequest().permitAll() // Permitir TODO sin autenticación
				);

		return http.build();
	}

}
