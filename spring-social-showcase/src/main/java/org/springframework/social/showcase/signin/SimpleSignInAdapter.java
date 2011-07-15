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
package org.springframework.social.showcase.signin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class SimpleSignInAdapter implements SignInAdapter {

	private final RequestCache requestCache;

	@Inject
	public SimpleSignInAdapter(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
		SignInUtils.signin(localUserId);
	    HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
		SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, null); 
		requestCache.removeRequest(httpServletRequest, null);
	    if (savedRequest != null) { 
	        return savedRequest.getRedirectUrl(); 
	    }	    
	    return null; 
	}

}
