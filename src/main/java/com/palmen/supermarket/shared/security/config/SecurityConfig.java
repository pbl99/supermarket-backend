package com.palmen.supermarket.shared.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.palmen.supermarket.shared.security.jwt.JwtAuthenticationEntryPoint;
import com.palmen.supermarket.shared.security.jwt.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	//Se inyectan a través del allargsconstructor los tres atributos 

	private UserDetailsService userDetailsService;

	//Maneja errores relacionados con la autenticación
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	//Filtro para validar los tokens JWT
	private JwtAuthenticationFilter authenticationFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> {
			authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "swagger-ui**", "/api/v1/auth/**", "/api/v1/**").permitAll();
			authorize.requestMatchers("/api/v1/purchase/**").hasRole("CUSTOMER");
			authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // EL Options se utiliza para saber que métodos estan permitidos en un endpoint concreto tipo options en api/product y devuelve el get o el post o ..
			authorize.anyRequest().authenticated();
		}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults());

		//Configura la clase que va a manejar los errores de tipo 401.
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));

		
		//Le dice a spring que utilice authenticationfilter que va a manejar con tokens la autenticación en vez de utilizar la autenticación tradicional de usuario y contraseña de esa clase ahí marcada.
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	//Configura la clase de passwordencoder para utilizar el tipo BCryptPasswordEncoder , existen más tipos distintos posibles para utilizar
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Es un manager como dice el propio nombre controla el flujo de la autenticación de que todo este correcto.
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
