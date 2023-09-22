package com.anchtun.apisecurity.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.anchtun.apisecurity.entity.JdbcCustomer;

// Just comment it because we commented: spring-boot-starter-data-jdbc because it will clash with spring-boot-starter-data-jpa
public interface JdbcCustomerCrudRepository extends CrudRepository<JdbcCustomer, Integer> {
//public interface JdbcCustomerCrudRepository {	

	List<JdbcCustomer> findByEmail(String email);

	List<JdbcCustomer> findByGender(String gender);

	// This is the common misconception : since we already use Spring data and @query at repository,
    // this is safe method. Wrong! The vulnerable code is on stored procedure.
	// Spring data will not filter your parameter that will be passed to stored procedure.
	@Query("CALL update_jdbc_customer(:customerId, :newFullName)")
	void updateCustomerFullName(@Param("customerId") int customerId, @Param("newFullName") String newFullName);

}