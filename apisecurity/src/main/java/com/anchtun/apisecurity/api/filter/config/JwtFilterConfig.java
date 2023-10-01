package com.anchtun.apisecurity.api.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.anchtun.apisecurity.api.filter.JwtAuthFilter;
import com.anchtun.apisecurity.api.filter.JwtFilter;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;
import com.anchtun.apisecurity.service.JwtService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
//@Configuration
public class JwtFilterConfig {

	private final BasicAuthUserRepository basicAuthUserRepository;
	private final JwtService jwtService;

	@Bean
	public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilter() {
		var registrationBean = new FilterRegistrationBean<JwtAuthFilter>();

		registrationBean.setFilter(new JwtAuthFilter(basicAuthUserRepository));
		registrationBean.addUrlPatterns("/api/auth/jwt/v1/login");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		var registrationBean = new FilterRegistrationBean<JwtFilter>();

		registrationBean.setFilter(new JwtFilter(jwtService));
		registrationBean.addUrlPatterns("/api/auth/jwt/v1/time");
		registrationBean.addUrlPatterns("/api/auth/jwt/v1/logout");

		return registrationBean;
	}

}
