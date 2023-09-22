package com.anchtun.apisecurity.api.server.sqlinjection;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.anchtun.apisecurity.entity.JpaCustomer;
//import com.anchtun.apisecurity.repository.JpaCustomerCrudRepository;
//import com.anchtun.apisecurity.repository.JpaCustomerSafeDAO;
//
//@RestController
//@RequestMapping("/api/sqlinjection/safe/v2")
//public class JpaCustomerSafeApi {
//
//	@Autowired
//	private JpaCustomerCrudRepository repository;
//
//	@Autowired
//	private JpaCustomerSafeDAO dao;
//
//	@GetMapping(value = "/customer/{email}")
//	public JpaCustomer findCustomerByEmail(@PathVariable(required = true, name = "email") String email) {
//		return repository.findByEmail(email).get(0);
//	}
//
//	@GetMapping(value = "/customer")
//	public List<JpaCustomer> findCustomersByGender(@RequestParam(required = true, name = "genderCode") String genderCode) {
//		return dao.findByGender(genderCode);
//	}
//
//}
