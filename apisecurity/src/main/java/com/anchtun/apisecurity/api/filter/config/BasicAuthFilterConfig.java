package com.anchtun.apisecurity.api.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anchtun.apisecurity.api.filter.BasicAuthFilter;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;

import lombok.AllArgsConstructor;

//@Configuration
@AllArgsConstructor
public class BasicAuthFilterConfig {

	private final BasicAuthUserRepository basicAuthUserRepository;

	@Bean
	public FilterRegistrationBean<BasicAuthFilter> basicAuthFilter() {
		var registrationBean = new FilterRegistrationBean<BasicAuthFilter>();

		registrationBean.setFilter(new BasicAuthFilter(basicAuthUserRepository));
		registrationBean.addUrlPatterns("/api/auth/basic/v1/time");

		return registrationBean;
	}

}
