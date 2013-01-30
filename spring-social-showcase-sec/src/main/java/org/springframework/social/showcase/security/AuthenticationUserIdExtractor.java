package org.springframework.social.showcase.security;

import org.springframework.security.core.Authentication;
import org.springframework.social.security.UserIdExtractor;

public class AuthenticationUserIdExtractor implements UserIdExtractor {
	@Override
	public String extractUserId(Authentication authentication) {
		return authentication.getName();
	}
}
