package com.anchtun.apisecurity.api.server.auth.basic;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.apisecurity.api.request.auth.basic.BasicAuthCreateUserRequest;
import com.anchtun.apisecurity.entity.BasicAuthUser;
import com.anchtun.apisecurity.repository.BasicAuthUserRepository;
import com.anchtun.apisecurity.util.EncryptDecryptUtil;
import com.anchtun.apisecurity.util.HashUtil;
import com.anchtun.apisecurity.util.SecureStringUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth/basic/v1")
public class BasicAuthApi {

	public static final String SECRET_KEY = "TheSecretKey2468";
	private final BasicAuthUserRepository repository;

	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createUser(@RequestBody BasicAuthCreateUserRequest userRequest) throws Exception {
		var user = new BasicAuthUser();

		var encryptedUsername = EncryptDecryptUtil.encryptAes(userRequest.getUsername().toLowerCase(), SECRET_KEY);
		user.setUsername(encryptedUsername);

		var salt = SecureStringUtil.randomString(16);
		user.setSalt(salt);

		var passwordHash = HashUtil.bcrypt(userRequest.getPassword(), salt);
		user.setPasswordHash(passwordHash);

		user.setDisplayName(userRequest.getDisplayName());

		var saved = repository.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body("New user created : " + saved.getDisplayName());
	}

	@GetMapping(value = "/time", produces = MediaType.TEXT_PLAIN_VALUE)
	public String time() {
		return "Now is " + LocalTime.now();
	}

}
