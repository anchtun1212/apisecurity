package com.anchtun.apisecurity.api.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anchtun.apisecurity.api.filter.RedisAuthFilter;
import com.anchtun.apisecurity.api.filter.RedisTokenFilter;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;
import com.anchtun.apisecurity.service.RedisTokenService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
//@Configuration
public class RedisFilterConfig {

	private final BasicAuthUserRepository basicAuthUserRepository;
	private final RedisTokenService tokenService;

	@Bean
	public FilterRegistrationBean<RedisAuthFilter> redisAuthFilter() {
		var registrationBean = new FilterRegistrationBean<RedisAuthFilter>();

		registrationBean.setFilter(new RedisAuthFilter(basicAuthUserRepository));
		registrationBean.addUrlPatterns("/api/auth/redis/v1/login");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<RedisTokenFilter> redisTokenFilter() {
		var registrationBean = new FilterRegistrationBean<RedisTokenFilter>();

		registrationBean.setFilter(new RedisTokenFilter(tokenService));
		registrationBean.addUrlPatterns("/api/auth/redis/v1/time");
		registrationBean.addUrlPatterns("/api/auth/redis/v1/logout");

		return registrationBean;
	}

}
