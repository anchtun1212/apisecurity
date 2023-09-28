package com.anchtun.apisecurity.api.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anchtun.apisecurity.entity.AuditLogEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuditLogFilter extends OncePerRequestFilter {

	private ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger LOG = LoggerFactory.getLogger(AuditLogFilter.class);

	private static ExecutorService executor = Executors.newSingleThreadExecutor();

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var cachedHttpRequest = new CachedBodyHttpServletRequest(request);
		var requestBody = IOUtils.toString(cachedHttpRequest.getReader());
		var httpResponse = response;

		chain.doFilter(cachedHttpRequest, response);

		var now = LocalDateTime.now();

		executor.execute(() -> {
			var auditLogEntry = new AuditLogEntry();
			auditLogEntry.setTimestamp(now.toString());
			auditLogEntry.setHeaders(request.getHeader("Authorization"));
			auditLogEntry.setPath(request.getRequestURI());
			auditLogEntry.setQuery(request.getQueryString());
			auditLogEntry.setMethod(request.getMethod());
			auditLogEntry.setRequestBody(requestBody);
			auditLogEntry.setResponseStatusCode(httpResponse.getStatus());

			try {
				var logString = objectMapper.writeValueAsString(auditLogEntry);
				LOG.info(logString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
	}

}
