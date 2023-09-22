package com.anchtun.apisecurity.repository;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.anchtun.apisecurity.entity.JpaCustomer;
//
//import jakarta.persistence.EntityManager;
//
//@Component
//public class JpaCustomerSafeDAO {
//
//	@Autowired
//	private EntityManager entityManager;
//
//	public List<JpaCustomer> findByGender(String gender) {
//		var jpql = "FROM JpaCustomer WHERE gender = :gender";
//		var query = entityManager.createQuery(jpql, JpaCustomer.class).setParameter("gender", gender);
//
//		return query.getResultList();
//	}
//
//}
