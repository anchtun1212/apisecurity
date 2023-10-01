package com.anchtun.apisecurity.api.server.auth.redisjwt;

import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.apisecurity.constant.RedisConstant;
import com.anchtun.apisecurity.entity.RedisJwtData;
import com.anchtun.apisecurity.service.RedisJwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth/redis-jwt/v1")
public class RedisJwtApi {

	private final RedisJwtService service;

	@GetMapping("/time")
	public String time(HttpServletRequest request) {
		var encryptedUsername = request.getAttribute(RedisConstant.REQUEST_ATTRIBUTE_USERNAME);
		return "Now is " + LocalTime.now() + ", accessed by " + encryptedUsername;
	}

	@PostMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
	public String login(HttpServletRequest request) throws Exception {
		var encryptedUsername = (String) request.getAttribute(RedisConstant.REQUEST_ATTRIBUTE_USERNAME);

		var jwtData = new RedisJwtData();
		jwtData.setUsername(encryptedUsername);

		var jwtToken = service.store(jwtData);

		return jwtToken;
	}

	@DeleteMapping("/logout")
	public ResponseEntity<String> logout(
			@RequestHeader(required = true, name = "Authorization") String authorizationHeader) {
		var token = StringUtils.substring(authorizationHeader, "Bearer".length() + 1);

		service.delete(token);
		return ResponseEntity.ok().body("Logged out");
	}

}
