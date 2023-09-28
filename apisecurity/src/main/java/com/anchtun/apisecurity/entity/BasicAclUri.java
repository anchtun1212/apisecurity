package com.anchtun.apisecurity.entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicAclUri {

	@Id
	private int uriId;
	private String uri;
	private String method;
}
