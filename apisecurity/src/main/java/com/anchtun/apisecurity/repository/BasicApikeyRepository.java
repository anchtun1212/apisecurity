package com.anchtun.apisecurity.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.anchtun.apisecurity.entity.BasicApikey;

public interface BasicApikeyRepository extends CrudRepository<BasicApikey, Integer> {

	Optional<BasicApikey> findByApikeyAndExpiredAtAfter(String apikey, LocalDateTime now);

}
