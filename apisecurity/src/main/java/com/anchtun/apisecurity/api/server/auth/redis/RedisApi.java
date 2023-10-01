package com.anchtun.apisecurity.api.server.auth.redis;

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
import com.anchtun.apisecurity.entity.RedisToken;
import com.anchtun.apisecurity.service.RedisTokenService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth/redis/v1")
public class RedisApi {

	private final RedisTokenService tokenService;

	@GetMapping("/time")
	public String time(HttpServletRequest request) {
		var encryptedUsername = request.getAttribute(RedisConstant.REQUEST_ATTRIBUTE_USERNAME);
		return "Now is " + LocalTime.now() + ", accessed by " + encryptedUsername;
	}

	@PostMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
	public String login(HttpServletRequest request) throws Exception {
		var encryptedUsername = (String) request.getAttribute(RedisConstant.REQUEST_ATTRIBUTE_USERNAME);

		var token = new RedisToken();
		token.setUsername(encryptedUsername);

		var tokenId = tokenService.store(token);

		return tokenId;
	}

	@DeleteMapping("/logout")
	public ResponseEntity<String> logout(
			@RequestHeader(required = true, name = "Authorization") String authorizationHeader) {
		var token = StringUtils.substring(authorizationHeader, "Bearer".length() + 1);

		tokenService.delete(token);
		return ResponseEntity.ok().body("Logged out");
	}

}
