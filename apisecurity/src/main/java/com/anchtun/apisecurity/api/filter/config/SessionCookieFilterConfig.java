package com.anchtun.apisecurity.api.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anchtun.apisecurity.api.filter.SessionCookieAuthFilter;
import com.anchtun.apisecurity.api.filter.SessionCookieTokenFilter;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;
import com.anchtun.apisecurity.service.SessionCookieTokenService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class SessionCookieFilterConfig {

	private final BasicAuthUserRepository basicAuthUserRepository;
	private final SessionCookieTokenService tokenService;

	@Bean
	public FilterRegistrationBean<SessionCookieAuthFilter> sessionCookieAuthFilter() {
		var registrationBean = new FilterRegistrationBean<SessionCookieAuthFilter>();

		registrationBean.setFilter(new SessionCookieAuthFilter(basicAuthUserRepository));
		registrationBean.addUrlPatterns("/api/auth/session-cookie/v1/login");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<SessionCookieTokenFilter> sessionCookieTokenFilter() {
		var registrationBean = new FilterRegistrationBean<SessionCookieTokenFilter>();

		registrationBean.setFilter(new SessionCookieTokenFilter(tokenService));
		registrationBean.addUrlPatterns("/api/auth/session-cookie/v1/time");
		registrationBean.addUrlPatterns("/api/auth/session-cookie/v1/logout");

		return registrationBean;
	}

}
