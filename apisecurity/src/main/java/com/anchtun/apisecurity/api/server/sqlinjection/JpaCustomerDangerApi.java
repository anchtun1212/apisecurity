package com.anchtun.apisecurity.api.server.sqlinjection;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.anchtun.apisecurity.entity.JpaCustomer;
//import com.anchtun.apisecurity.repository.JpaCustomerCrudRepository;
//import com.anchtun.apisecurity.repository.JpaCustomerDangerDAO;
//
//@RestController
//@RequestMapping("/api/sqlinjection/danger/v2")
//public class JpaCustomerDangerApi {
//
//	@Autowired
//	private JpaCustomerCrudRepository repository;
//
//	@Autowired
//	private JpaCustomerDangerDAO dao;
//
//	@GetMapping(value = "/customer/{email}")
//	public JpaCustomer findCustomerByEmail(@PathVariable(required = true, name = "email") String email) {
//		var queryResult = repository.findByEmail(email);
//
//		if (queryResult != null && !queryResult.isEmpty()) {
//			return queryResult.get(0);
//		}
//
//		return null;
//	}
//
//	@GetMapping(value = "/customer")
//	public List<JpaCustomer> findCustomersByGender(@RequestParam(required = true, name = "genderCode") String genderCode) {
//		return dao.findByGender(genderCode);
//	}
//
//	@PostMapping(value = "/customer")
//	public void createCustomer(@RequestBody(required = true) JpaCustomer newCustomer) {
//		repository.save(newCustomer);
//	}
//
//}
