package com.anchtun.apisecurity.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// We need something to keep the nonce. In real life, we should use something more
// proper like Redis for storage. For simplicity, let’s just create HmacNonceStorage
@Component
public class HmacNonceStorage {

	private static final Map<String, LocalDateTime> NONCE_MAP = new HashMap<>();

	public static boolean addWhenNotExists(String nonce) {
		if (NONCE_MAP.containsKey(nonce) || StringUtils.isBlank(nonce)) {
			return false;
		}

		NONCE_MAP.put(nonce, LocalDateTime.now());

		return true;
	}

	// We will run a scheduler with 2 seconds delay in between each run.
	@Scheduled(fixedDelay = 2000)
	private static void cleanExpiredNonces() {
		var nonceTimeLimit = LocalDateTime.now().minusMinutes(5);

		var expiredKeys = NONCE_MAP.keySet().stream().filter(k -> NONCE_MAP.get(k).isBefore(nonceTimeLimit))
				.collect(Collectors.toList());
		expiredKeys.forEach(exp -> NONCE_MAP.remove(exp));
	}

}
