package com.anchtun.apisecurity.api.server.dos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/dos/v1")
public class RedGreenBlueApi {

	@GetMapping("/green")
	public String green() {
		return "green";
	}

	@GetMapping("/blue")
	public String blue() {
		log.info("blue");
		return "blue";
	}

	@GetMapping("/red")
	public String red() {
		log.info("red");

		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
		}

		return "red";
	}

}
