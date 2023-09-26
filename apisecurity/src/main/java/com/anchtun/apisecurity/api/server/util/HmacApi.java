package com.anchtun.apisecurity.api.server.util;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.apisecurity.api.request.util.HmacRequest;
import com.anchtun.apisecurity.util.HmacUtil;

@RestController
@RequestMapping("/api/hmac")
public class HmacApi {
    
	// Don’t put the secret key in the code. This is just for simplicity on example.
	public static final String SECRET_KEY = "The123HmacSecretKey";
	private static final String MESSAGE_DELIMITER = "\n";

	// The first endpoint is to calculate HMAC
	@GetMapping(value = "/calculate", produces = MediaType.TEXT_PLAIN_VALUE)
	public String hmac(
			// So in this endpoint, we will get the verb from custom header X-Verb-Calculate.
			@RequestHeader(name = "X-Verb-Calculate", required = true) String verbCalculate,
			@RequestHeader(name = "X-Uri-Calculate", required = true) String uriCalculate,
			@RequestHeader(name = "X-Register-Date", required = true) String registerDate,
			@RequestHeader(name = "X-Nonce", required = true) String nonce,
			@RequestBody(required = true) HmacRequest requestBody) throws Exception {
		var hmacMessage = constructHmacMessage(verbCalculate, uriCalculate, requestBody.getAmount(),
				requestBody.getFullName(), registerDate, nonce);

		return HmacUtil.hmac(hmacMessage, SECRET_KEY);
	}

	// Define the method to construct this message using specified rule
	public static String constructHmacMessage(String verb, String requestURI, int amount, String fullName,
			String xRegisterDate, String nonce) {
		var sb = new StringBuilder();

		sb.append(verb.toLowerCase());
		sb.append(MESSAGE_DELIMITER);
		sb.append(requestURI);
		sb.append(MESSAGE_DELIMITER);
		sb.append(amount);
		sb.append(MESSAGE_DELIMITER);
		sb.append(fullName);
		sb.append(MESSAGE_DELIMITER);
		sb.append(xRegisterDate);
		sb.append(nonce);

		return sb.toString();
	}

	// HMAC validation will be written after this on filter.
	@RequestMapping(value = "/info", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String info(@RequestBody(required = true) HmacRequest original) {
		return "The request body is " + original.toString();
	}

}
