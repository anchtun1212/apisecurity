package com.anchtun.apisecurity.api.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anchtun.apisecurity.api.filter.HmacFilter;

@Configuration
public class HmacFilterConfig {

	@Bean
	public FilterRegistrationBean<HmacFilter> hmacFilter() {
		var registrationBean = new FilterRegistrationBean<HmacFilter>();

		registrationBean.setFilter(new HmacFilter());
		registrationBean.addUrlPatterns("/api/hmac/info");

		return registrationBean;
	}

}
