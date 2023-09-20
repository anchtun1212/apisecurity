package com.anchtun.apisecurity.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JdbcCustomer {

	@Id
	private int customerId;
	private String fullName;
	private String email;
	private LocalDate birthDate;
	private String gender;
}
