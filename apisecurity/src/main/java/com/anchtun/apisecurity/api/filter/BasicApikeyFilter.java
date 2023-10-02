package com.anchtun.apisecurity.api.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anchtun.apisecurity.constant.ApikeyConstant;
import com.anchtun.apisecurity.repository.BasicApikeyRepository;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class BasicApikeyFilter extends OncePerRequestFilter {

	private final BasicAuthUserRepository basicAuthUserRepository;
	private final BasicApikeyRepository basicApikeyRepository;

	private boolean isValidApikey(String apikey, HttpServletRequest request) throws Exception {
		// find valid api key
		var apikeyFromStorage = basicApikeyRepository.findByApikeyAndExpiredAtAfter(apikey, LocalDateTime.now());

		if (apikeyFromStorage.isPresent()) {
			var currentUser = basicAuthUserRepository.findById(apikeyFromStorage.get().getUserId());
			request.setAttribute(ApikeyConstant.REQUEST_ATTRIBUTE_USERNAME, currentUser.get().getUsername());

			return true;
		}

		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		var apikey = request.getHeader("X-Apikey");

		try {
			if (isValidApikey(apikey, request)) {
				chain.doFilter(request, response);
			} else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				PrintWriter writer = response.getWriter();
				writer.print("{\"message\":\"Invalid credential\"}");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
