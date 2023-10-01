package com.anchtun.apisecurity.api.server.auth.jwe;

import java.time.LocalTime;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.apisecurity.constant.JweConstant;
import com.anchtun.apisecurity.entity.JweData;
import com.anchtun.apisecurity.service.JweService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth/jwe/v1")
public class JweApi {

	private final JweService service;

	@GetMapping("/time")
	public String time(HttpServletRequest request) {
		var encryptedUsername = request.getAttribute(JweConstant.REQUEST_ATTRIBUTE_USERNAME);
		return "Now is " + LocalTime.now() + ", accessed by " + encryptedUsername;
	}

	@PostMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
	public String login(HttpServletRequest request) throws Exception {
		var encryptedUsername = (String) request.getAttribute(JweConstant.REQUEST_ATTRIBUTE_USERNAME);

		var jweData = new JweData();
		jweData.setUsername(encryptedUsername);

		var jweToken = service.store(jweData);

		return jweToken;
	}

}
