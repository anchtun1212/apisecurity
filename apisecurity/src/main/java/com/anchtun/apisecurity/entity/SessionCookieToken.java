package com.anchtun.apisecurity.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionCookieToken {
	private String username;
	private String dummyAttribute;
}
