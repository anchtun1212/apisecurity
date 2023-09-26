package com.anchtun.apisecurity.api.request.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HmacRequest {
	private String fullName;
	private String city;
	private String gender;
	private int amount;

	@Override
	public String toString() {
		return "HmacRequest [fullName=" + fullName + ", city=" + city + ", gender=" + gender + ", amount=" + amount
				+ "]";
	}

}
