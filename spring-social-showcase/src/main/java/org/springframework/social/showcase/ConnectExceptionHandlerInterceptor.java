/*
 * Copyright 2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.springframework.social.showcase;

import static org.springframework.util.StringUtils.*;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class ConnectExceptionHandlerInterceptor implements HandlerInterceptor {

	private final static Log logger = LogFactory.getLog(ConnectExceptionHandlerInterceptor.class);

	private final ConnectionRepository connectionRepository;

	@Inject
	public ConnectExceptionHandlerInterceptor(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			RequiresConnection requiresConnection = handlerMethod.getMethodAnnotation(RequiresConnection.class);
			if (requiresConnection != null) {
				processRequiresConnection(requiresConnection);
			}
		}
		
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}

	private void processRequiresConnection(RequiresConnection requiresConnection) {
		String providerId = requiresConnection.value();
		String[] scope = requiresConnection.scope();
		String scopeString = arrayToCommaDelimitedString(scope);
		
		logger.info("Request requires a connection to " + providerId + (scope.length > 0 ? " with the following scope: " + scopeString : ""));
		
		List<Connection<?>> connections = connectionRepository.findConnections(providerId);

		if (connections.size() > 0 && scope.length == 0) {
			logger.info("Connection requirements satisfied");
			return;
		} else if (connections.size() == 0) { // scope doesn't matter
			logger.info("No connection to " + providerId + " available for user.");
			throw new NotAuthorizedException(providerId, "Not authorized for " + providerId);
		} else if (connections.size() > 0) {
			// partially satisfied; gotta check for scope
			// how to do that, though...
			logger.info("A connection to " + providerId + " is available, but we still need to check for scope.");
		}		
	}
	
}
