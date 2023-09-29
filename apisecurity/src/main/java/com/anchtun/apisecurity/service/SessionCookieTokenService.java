package com.anchtun.apisecurity.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.anchtun.apisecurity.constant.SessionCookieConstant;
import com.anchtun.apisecurity.entity.SessionCookieToken;
import com.anchtun.apisecurity.util.HashUtil;
import com.anchtun.apisecurity.util.SecureStringUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SessionCookieTokenService {

	public String store(HttpServletRequest request, SessionCookieToken token) throws NoSuchAlgorithmException {
		// session fixation
		var session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}
		// session fixation

		session = request.getSession(true);

		session.setAttribute(SessionCookieConstant.SESSION_ATTRIBUTE_USERNAME, token.getUsername());

		// CSRF
		return HashUtil.sha256(session.getId(), token.getUsername());
		// CSRF
	}

	// We need to get the session if any, but dont create one.
    // For this, pass false as parameter. If there is no session, we will return empty token.
	public Optional<SessionCookieToken> read(HttpServletRequest request, String tokenId)
			throws NoSuchAlgorithmException {
		var session = request.getSession(false);

		if (session == null || StringUtils.isBlank(tokenId)) {
			return Optional.empty();
		}

		var username = (String) session.getAttribute(SessionCookieConstant.SESSION_ATTRIBUTE_USERNAME);

		var computedTokenId = HashUtil.sha256(session.getId(), username);

		// prevent timing attack
		if (!SecureStringUtil.equals(tokenId, computedTokenId)) {
			return Optional.empty();
		}

		var token = new SessionCookieToken();
		token.setUsername(username);

		return Optional.of(token);
	}

	public void delete(HttpServletRequest request) {
		var session = request.getSession(false);
		session.invalidate();
	}

}
