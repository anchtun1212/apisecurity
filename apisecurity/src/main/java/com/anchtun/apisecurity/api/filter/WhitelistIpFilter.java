package com.anchtun.apisecurity.api.filter;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class WhitelistIpFilter extends OncePerRequestFilter {
	
	// By default, Java will get IP version 6 address format for localhost, so we must define it.
    // The second is dummy IP, in IP version 4 format.
	private static final String[] AUTHORIZED_IP = { "0:0:0:0:0:0:0:1", "10.182.36.77" };

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (!ArrayUtils.contains(AUTHORIZED_IP, request.getRemoteAddr())) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.TEXT_PLAIN_VALUE);
			PrintWriter writer = response.getWriter();
			writer.print("Forbidden IP : " + request.getRemoteAddr());
			return;
		}
		chain.doFilter(request, response);
	}

}
