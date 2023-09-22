package com.anchtun.apisecurity.api.server.sqlinjection;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.apisecurity.api.request.sqlinjection.JdbcCustomerPatchRequest;
import com.anchtun.apisecurity.entity.JdbcCustomer;
import com.anchtun.apisecurity.repository.JdbcCustomerDangerRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sqlinjection/danger/v1")
@Validated
public class JdbcCustomerDangerApi {

	private final JdbcCustomerDangerRepository repository;
	
//	@Autowired
//	private JdbcCustomerDangerRepository repository;
	
	@GetMapping(value = "/customer/{email}")
	public JdbcCustomer findCustomerByEmail(@PathVariable(required = true, name = "email") String email) {
		return repository.findCustomerByEmail(email);
	}
	
	@GetMapping(value = "/customer")
	public List<JdbcCustomer> findCustomersByGender(
			@Pattern(regexp = "^[MF]$", message = "Invalid gender") @RequestParam(required = true, name = "genderCode") String genderCode) {
		return repository.findCustomersByGender(genderCode);
	}
	
	@PostMapping(value = "/customer")
	public void createCustomer(@Valid @RequestBody(required = true) JdbcCustomer newCustomer) {
		repository.createCustomer(newCustomer);
	}

	@PatchMapping(value = "/customer/{customerId}")
	public void updateCustomerFullName(@PathVariable(required = true, name = "customerId") int customerId, @RequestBody(required = true) JdbcCustomerPatchRequest request) {
		repository.updateCustomerFullName(customerId, request.getNewFullName());
	}
}
