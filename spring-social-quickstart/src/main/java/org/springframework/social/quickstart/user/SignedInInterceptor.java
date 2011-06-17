/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.quickstart.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.NotConnectedException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Before a request is handled:
 * 1. sets the current User in the {@link SecurityContext} from a cookie, if present.
 * 2. requires that the user sign-in if he or she hasn't already.
 * @author Keith Donald
 */
public final class SignedInInterceptor extends HandlerInterceptorAdapter {

	private final UserCookieGenerator userCookieGenerator;

	public SignedInInterceptor() {
		userCookieGenerator = new UserCookieGenerator();
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (signedIn(request) || requestForSignIn(request)) {
			return true;
		} else {
			return requireSignIn(request, response);
		}
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		SecurityContext.remove();
		if (ex.getCause().getCause() instanceof NotConnectedException) { // SPR-8466
			userCookieGenerator.removeCookie(response);
			requireSignIn(request, response);
		}
	}

	// internal helpers

	private boolean signedIn(HttpServletRequest request) {
		User user = userCookieGenerator.readCookieValue(request);
		if (user == null) {
			return false;
		}
		SecurityContext.setCurrentUser(user);
		return true;
	}
	
	private boolean requestForSignIn(HttpServletRequest request) {
		return request.getServletPath().startsWith("/signin");
	}
	
	private boolean requireSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new RedirectView("/signin", true).render(null, request, response);
		return false;
	}

}