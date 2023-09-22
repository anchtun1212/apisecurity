package com.anchtun.apisecurity.api.server.xss;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.apisecurity.api.response.xss.XssArticleSearchResponse;
import com.anchtun.apisecurity.entity.XssArticle;
import com.anchtun.apisecurity.repository.XssArticleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/xss/danger/v1/article")
@CrossOrigin(origins = "http://localhost:3000")
public class XssArticleDangerApi {

	private final XssArticleRepository repository;

	@PostMapping(value = "")
	public String create(@RequestBody(required = true) XssArticle article) {
		var savedArticleId = repository.save(article).getArticleId();
		return Long.toString(savedArticleId);
	}

	@GetMapping(value = "")
	public XssArticleSearchResponse search(@RequestParam(required = true) String query) {
		var articles = repository.findByArticleContainsIgnoreCase(query);

		var response = new XssArticleSearchResponse();
		response.setResult(articles);

		if (articles.size() < 100) {
			response.setQueryCount("Search for " + query + " returns <strong>" + articles.size() + "</strong> results.");
		} else {
			response.setQueryCount("Search for " + query + " returns too many results. <em>Use more specific keyword.</em>");
		}

		return response;
	}

}
