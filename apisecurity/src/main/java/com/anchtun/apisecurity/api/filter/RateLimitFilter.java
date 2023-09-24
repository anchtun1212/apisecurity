package com.anchtun.apisecurity.api.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.util.concurrent.RateLimiter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RateLimitFilter extends OncePerRequestFilter {

	private RateLimiter rateLimiter;

	// This double parameter is the transaction per second, which is number of hit allowed within one second. 
	public RateLimitFilter(double tps) {
		this.rateLimiter = RateLimiter.create(tps);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (!rateLimiter.tryAcquire()) {
			response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			response.setHeader(HttpHeaders.RETRY_AFTER, "5");

			return;
		}

		chain.doFilter(request, response);
	}

}
