package com.anchtun.apisecurity.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

	// Spring 5
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and()
				.oauth2Login();
		
//		http.antMatcher("/**").authorizeRequests().antMatchers("/api/math/random").permitAll().anyRequest()
//		.authenticated().and().oauth2Login();
	}*/
	
	// Spring 6
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/**").permitAll();
				//.anyRequest().authenticated();
				})
				//.oauth2Login(withDefaults())
				.build();

	}
	
}
