package com.anchtun.apisecurity.api.request.sqlinjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JdbcCustomerPatchRequest {
	private String newFullName;
}
