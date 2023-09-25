package com.anchtun.apisecurity.util;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class EncryptDecryptUtil {

	private static final String ALGORITHM = "AES";
	private static final String AES_TRANSFORMATION = "AES/CBC/PKCS7Padding";

	// use unique value for 16 bytes initialization vector
	// which means that no IV may be reused under the same key.
	// Please note that for this example, I use hardcoded IV for simplicity.
    // In reality, don’t do this. Put your IV and secret credentials outside of your code.
	// Use HashiCorp Vault if possible
	// You can use any 16 bytes string, or 16 characters for IV
	private static final String IV = "LqV4g8gT1DqjSPB6";
	private static IvParameterSpec paramSpec;
	private static Cipher cipher;

	// Since all variables are static, let’s initialize them in static block;
	static {
		Security.addProvider(new BouncyCastleProvider());
		paramSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

		try {
			cipher = Cipher.getInstance(AES_TRANSFORMATION);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public static String encryptAes(String original, String secretKey) throws Exception {
		final var secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec);
		
		// use cipher.doFinal method, which will return array of bytes for encrypted string.
		var resultBytes = cipher.doFinal(original.getBytes(StandardCharsets.UTF_8));

		// AES support Base64 encode or hex encode. This time, we will use the string as hex encode.
		return new String(Hex.encode(resultBytes));
	}

	public static String decryptAes(String encrypted, String secretKey) throws Exception {
		final var secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramSpec);

		var encryptedBytes = Hex.decode(encrypted);
		var resultBytes = cipher.doFinal(encryptedBytes);

		return new String(resultBytes);
	}

}
