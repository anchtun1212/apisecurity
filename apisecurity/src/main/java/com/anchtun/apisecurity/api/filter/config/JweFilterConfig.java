package com.anchtun.apisecurity.api.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anchtun.apisecurity.api.filter.JweAuthFilter;
import com.anchtun.apisecurity.api.filter.JweFilter;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;
import com.anchtun.apisecurity.service.JweService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
//@Configuration
public class JweFilterConfig {

	private final BasicAuthUserRepository basicAuthUserRepository;
	private final JweService jweService;

	@Bean
	public FilterRegistrationBean<JweAuthFilter> jweAuthFilter() {
		var registrationBean = new FilterRegistrationBean<JweAuthFilter>();

		registrationBean.setFilter(new JweAuthFilter(basicAuthUserRepository));
		registrationBean.addUrlPatterns("/api/auth/jwe/v1/login");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<JweFilter> jweFilter() {
		var registrationBean = new FilterRegistrationBean<JweFilter>();

		registrationBean.setFilter(new JweFilter(jweService));
		registrationBean.addUrlPatterns("/api/auth/jwe/v1/time");
		registrationBean.addUrlPatterns("/api/auth/jwe/v1/logout");

		return registrationBean;
	}

}
