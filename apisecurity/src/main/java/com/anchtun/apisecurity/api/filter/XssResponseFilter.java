package com.anchtun.apisecurity.api.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class XssResponseFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		chain.doFilter(request, response);

		response.setHeader("X-XSS-Protection", "0");
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setHeader("Content-Security-Policy", "script-src 'self' 'nonce-someRand0mNonc3';");
	}

}
