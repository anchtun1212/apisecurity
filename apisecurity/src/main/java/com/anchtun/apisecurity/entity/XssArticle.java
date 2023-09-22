package com.anchtun.apisecurity.entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XssArticle {
	@Id
	private long articleId;
	private String article;
}
