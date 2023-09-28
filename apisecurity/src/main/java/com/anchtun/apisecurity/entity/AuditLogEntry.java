package com.anchtun.apisecurity.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditLogEntry {
	private String timestamp;
	private String method;
	private String path;
	private String query;
	private String headers;
	private String requestBody;
	private int responseStatusCode;
}
