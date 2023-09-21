package com.anchtun.apisecurity.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JdbcCustomer {

	@Id
	private int customerId;
	private String fullName;
	@Email
	private String email;
	private LocalDate birthDate;
	@Pattern(regexp = "^[MF]$", message = "Invalid gender")
	private String gender;
}
