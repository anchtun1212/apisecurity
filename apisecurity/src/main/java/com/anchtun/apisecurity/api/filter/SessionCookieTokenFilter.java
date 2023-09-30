package com.anchtun.apisecurity.api.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anchtun.apisecurity.constant.SessionCookieConstant;
import com.anchtun.apisecurity.service.SessionCookieTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SessionCookieTokenFilter extends OncePerRequestFilter {

	private SessionCookieTokenService tokenService;

	public SessionCookieTokenFilter(SessionCookieTokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			// In the filter, we need to validate whether there is a valid cookie
			if (isValidSessionCookie(request)) {
				chain.doFilter(request, response);
			} else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				PrintWriter writer = response.getWriter();
				writer.print("{\"message\":\"Invalid token\"}");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isValidSessionCookie(HttpServletRequest request) throws NoSuchAlgorithmException {
		// CSRF
		// we need to provide token ID, which is taken from custom header X-CSRF.
		var csrfToken = request.getHeader("X-CSRF");

		if (StringUtils.isBlank(csrfToken)) {
			return false;
		}
		// CSRF

		var token = tokenService.read(request, csrfToken);

		// We will return true, and set valid username at request attribute, if the token is present
		if (token.isPresent()) {
			request.setAttribute(SessionCookieConstant.REQUEST_ATTRIBUTE_USERNAME, token.get().getUsername());
			return true;
		} else {
			return false;
		}
	}
}
