package com.anchtun.apisecurity.api.server.auth.sessioncookie;

import java.time.LocalTime;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.apisecurity.constant.SessionCookieConstant;
import com.anchtun.apisecurity.entity.SessionCookieToken;
import com.anchtun.apisecurity.service.SessionCookieTokenService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth/session-cookie/v1")
//@CrossOrigin(value = "http://127.0.0.1:8080", allowCredentials = "true")
public class SessionCookieApi {

	private final SessionCookieTokenService tokenService;

	@GetMapping("/time")
	public String time(HttpServletRequest request) {
		var encryptedUsername = request.getAttribute(SessionCookieConstant.REQUEST_ATTRIBUTE_USERNAME);
		return "Now is " + LocalTime.now() + ", accessed by " + encryptedUsername;
	}

	@PostMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
	public String login(HttpServletRequest request) throws Exception {
		var encryptedUsername = (String) request.getAttribute(SessionCookieConstant.REQUEST_ATTRIBUTE_USERNAME);

		var token = new SessionCookieToken();
		token.setUsername(encryptedUsername);

		var tokenId = tokenService.store(request, token);

		return tokenId;
	}

	@DeleteMapping("/logout")
	public String logout(HttpServletRequest request) {
		tokenService.delete(request);
		return "Logged out";
	}

}
