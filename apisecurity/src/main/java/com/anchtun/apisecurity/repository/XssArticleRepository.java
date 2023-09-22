package com.anchtun.apisecurity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anchtun.apisecurity.entity.XssArticle;

public interface XssArticleRepository extends CrudRepository<XssArticle, Long> {

	public List<XssArticle> findByArticleContainsIgnoreCase(String article);

}
