package com.anchtun.apisecurity.api.response.xss;

import java.util.List;

import com.anchtun.apisecurity.entity.XssArticle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XssArticleSearchResponse {
	private String queryCount;
	private List<XssArticle> result;

}
