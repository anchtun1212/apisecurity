package com.anchtun.apisecurity.api.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class SqlInjectionFilter extends OncePerRequestFilter {

	// So we need to specify the dangerous regular expression.
    // This regex is just sample regex for simplicity. It will block “select from” and “drop” statement
	// In real world, you might need to fine-tune the filter.
    // For example, filtering only certain endpoints, or tuning the regular expression.
	private static final String[] SQL_REGEX = { "(?i)(.*)(\\b)+SELECT(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
			"(?i)(.*)(\\b)+DROP(\\b)+\\s.*(.*)" };

	private List<Pattern> sqlValidationPatterns;

	public SqlInjectionFilter() {
		sqlValidationPatterns = new ArrayList<Pattern>();

		for (String sqlStatement : SQL_REGEX) {
			var pattern = Pattern.compile(sqlStatement, Pattern.CASE_INSENSITIVE);
			sqlValidationPatterns.add(pattern);
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		var cachedHttpRequest = new CachedBodyHttpServletRequest(request);

		var queryString = URLDecoder.decode(Optional.ofNullable(cachedHttpRequest.getQueryString()).orElse(StringUtils.EMPTY), StandardCharsets.UTF_8);
		var pathVariables = URLDecoder.decode(Optional.ofNullable(cachedHttpRequest.getRequestURI()).orElse(StringUtils.EMPTY), StandardCharsets.UTF_8);
		var requestBody = IOUtils.toString(cachedHttpRequest.getReader()).replaceAll("\\r\\n|\\r|\\n", "");

		if (isSqlInjectionSafe(queryString) && isSqlInjectionSafe(pathVariables) && isSqlInjectionSafe(requestBody)) {
			chain.doFilter(cachedHttpRequest, response);
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			PrintWriter writer = response.getWriter();
			writer.print("{\"message\":\"SQL injection detected\"}");
			return;
		}
	}

	private boolean isSqlInjectionSafe(String stringToValidate) {
		if (StringUtils.isBlank(stringToValidate)) {
			return true;
		}

		for (var pattern : sqlValidationPatterns) {
			if (pattern.matcher(stringToValidate).find()) {
				return false;
			}
		}

		return true;
	}

}
