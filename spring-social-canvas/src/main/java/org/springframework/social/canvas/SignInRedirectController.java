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
package org.springframework.social.canvas;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Displays the signin redirection page, placing the authorization URL in the model so that the signin page can use a JS redirection for the top-level browser window.
 * Due to the iframe-based nature of Canvas apps, you can't rely on the normal redirection that ProviderSignInController does.
 * @author Craig Walls
 */
@Controller
public class SignInRedirectController {

	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final ConnectSupport connectSupport;

	@Inject
	public SignInRedirectController(ConnectionFactoryLocator connectionFactoryLocator) {
		this.connectionFactoryLocator = connectionFactoryLocator;
		connectSupport = new ConnectSupport();
	}
	
	@RequestMapping(value = "/signin")
	public String signin(NativeWebRequest request, Model model) {
		model.addAttribute("authorizationUrl", getAuthorizationUrl(request));
		return "signin";
	}

	private String getAuthorizationUrl(NativeWebRequest request) {
		ConnectionFactory<Facebook> connectionFactory = connectionFactoryLocator.getConnectionFactory(Facebook.class);
		String authUrl = connectSupport.buildOAuthUrl(connectionFactory, request) + "%2Ffacebook";
		return authUrl;
	}

}
