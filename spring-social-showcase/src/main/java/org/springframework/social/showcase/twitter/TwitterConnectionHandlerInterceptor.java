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
package org.springframework.social.showcase.twitter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionRepository;
import org.springframework.social.twitter.api.TwitterApi;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TwitterConnectionHandlerInterceptor extends HandlerInterceptorAdapter {

	private final ServiceProviderConnectionRepository connectionRepository;

	@Inject
	public TwitterConnectionHandlerInterceptor(ServiceProviderConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ServiceProviderConnection<TwitterApi> connection = connectionRepository.findPrimaryConnectionToServiceApi(TwitterApi.class);
		request.setAttribute("connectedToTwitter", connection != null);
		return true;
	}
	
}
