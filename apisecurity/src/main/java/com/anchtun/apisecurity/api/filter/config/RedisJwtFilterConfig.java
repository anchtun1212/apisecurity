package com.anchtun.apisecurity.api.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anchtun.apisecurity.api.filter.RedisJwtAuthFilter;
import com.anchtun.apisecurity.api.filter.RedisJwtFilter;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;
import com.anchtun.apisecurity.service.RedisJwtService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class RedisJwtFilterConfig {

	private final BasicAuthUserRepository basicAuthUserRepository;
	private final RedisJwtService jwtService;

	@Bean
	public FilterRegistrationBean<RedisJwtAuthFilter> redisJwtAuthFilter() {
		var registrationBean = new FilterRegistrationBean<RedisJwtAuthFilter>();

		registrationBean.setFilter(new RedisJwtAuthFilter(basicAuthUserRepository));
		registrationBean.addUrlPatterns("/api/auth/redis-jwt/v1/login");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<RedisJwtFilter> redisJwtFilter() {
		var registrationBean = new FilterRegistrationBean<RedisJwtFilter>();

		registrationBean.setFilter(new RedisJwtFilter(jwtService));
		registrationBean.addUrlPatterns("/api/auth/redis-jwt/v1/time");
		registrationBean.addUrlPatterns("/api/auth/redis-jwt/v1/logout");

		return registrationBean;
	}

}
