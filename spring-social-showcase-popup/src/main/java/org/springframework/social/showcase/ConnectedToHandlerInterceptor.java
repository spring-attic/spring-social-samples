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
package org.springframework.social.showcase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ConnectedToHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private ConnectionRepository connectionRepository;
	
	public ConnectedToHandlerInterceptor(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getUserPrincipal() != null) {
			request.setAttribute("connectedToTwitter", connectionRepository.findConnections("twitter").size() > 0);
			request.setAttribute("connectedToFacebook", connectionRepository.findConnections("facebook").size() > 0);
		}
		return true;
	}

}
