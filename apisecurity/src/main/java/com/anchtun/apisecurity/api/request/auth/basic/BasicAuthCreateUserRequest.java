package com.anchtun.apisecurity.api.request.auth.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicAuthCreateUserRequest {
	private String username;
	private String password;
	private String displayName;
}
