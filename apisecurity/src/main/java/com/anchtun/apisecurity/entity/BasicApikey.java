package com.anchtun.apisecurity.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicApikey {

	@Id
	private int apikeyId;
	private int userId;
	private String apikey;
	private LocalDateTime expiredAt;
}
