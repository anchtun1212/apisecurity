package com.anchtun.apisecurity.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.anchtun.apisecurity.entity.BasicAuthUser;

public interface BasicAuthUserRepository extends CrudRepository<BasicAuthUser, Integer> {

	Optional<BasicAuthUser> findByUsername(String encryptedUsername);

}