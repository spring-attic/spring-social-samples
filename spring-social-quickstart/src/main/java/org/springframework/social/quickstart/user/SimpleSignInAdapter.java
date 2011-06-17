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

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Signs the user in by setting the currentUser property on the {@link SecurityContext}.
 * Remembers the sign-in after the current request completes by storing the user's id in a cookie.
 * This is cookie is read in {@link SignedInInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)} on subsequent requests.
 * @author Keith Donald
 * @see SignedInInterceptor
 */
public final class SimpleSignInAdapter implements SignInAdapter {

	private final UserCookieGenerator userCookieGenerator;
	
	public SimpleSignInAdapter() {
		this.userCookieGenerator = new UserCookieGenerator();
	}
	
	public void signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		User user = new User(userId);
		SecurityContext.setCurrentUser(user);		
		userCookieGenerator.addCookie(user, request.getNativeResponse(HttpServletResponse.class));
	}

}