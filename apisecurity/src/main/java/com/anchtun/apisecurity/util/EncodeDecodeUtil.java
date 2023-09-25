package com.anchtun.apisecurity.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

public class EncodeDecodeUtil {

	public static String encodeBase64(String original) {
		// Deprecated
		// return Base64Utils.encodeToString(original.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(original.getBytes());
	}

	public static String decodeBase64(String encoded) {
		// Deprecated
		// return new String(Base64Utils.decodeFromString(encoded), StandardCharsets.UTF_8);
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
		return new String(decodedBytes);
	}

	public static String encodeUrl(String original) {
		return UriUtils.encode(original, StandardCharsets.UTF_8);
	}

	public static String decodeUrl(String encoded) {
		return UriUtils.decode(encoded, StandardCharsets.UTF_8);
	}

	public static String encodeBase64(byte[] bytes) {
		// Deprecated
		// return Base64Utils.encodeToString(bytes);
		return Base64.getEncoder().encodeToString(bytes);
	}

}
