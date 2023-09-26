package com.anchtun.apisecurity.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicAuthUser {

	@Id
	private int userId;
	private String username;
	private String passwordHash;
	private String salt;
	private String displayName;

	@MappedCollection(idColumn = "user_id")
	private Set<BasicAclUserUriRef> allowedUris;
}