/*
 * Copyright 2013 the original author or authors.
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

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.web.SignedRequest;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Simple little @Controller that invokes Facebook and renders the result.
 * The injected {@link Facebook} reference is configured with the required authorization credentials for the current user behind the scenes.
 * @author Craig Walls
 */
@Controller
public class CanvasSignInController {
	
	private final static Log logger = LogFactory.getLog(CanvasSignInController.class);

	private final String clientId;
	
	private final String canvasPage;

	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final UsersConnectionRepository usersConnectionRepository;

	private final SignInAdapter signInAdapter;

	@Inject
	public CanvasSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SignInAdapter signInAdapter, Environment environment) {
		this.usersConnectionRepository = usersConnectionRepository;
		this.signInAdapter = signInAdapter;
		this.clientId = environment.getProperty("facebook.clientId");
		this.canvasPage = environment.getProperty("facebook.canvasPage");
		this.connectionFactoryLocator = connectionFactoryLocator;
	}

	@RequestMapping(value = "/" /*, method = RequestMethod.GET*/) // FB wants the canvas page to take POST requests (?)
	public String signin(@SignedRequest SignedRequestHolder signedRequest, Model model, NativeWebRequest request) {
		String accessToken = signedRequest.getOauthToken();
		if (accessToken == null) {
			// redirect to authorization dialog
			model.addAttribute("clientId", clientId);
			model.addAttribute("canvasPage", canvasPage);
			return "authDialogRedirect";
		}

		OAuth2ConnectionFactory<Facebook> connectionFactory = (OAuth2ConnectionFactory<Facebook>) connectionFactoryLocator.getConnectionFactory(Facebook.class);
		AccessGrant accessGrant = new AccessGrant(accessToken);
		Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);
		handleSignIn(connection, request);
		return "redirect:/friends"; // TODO: Make this configurable
	}

	
	private void handleSignIn(Connection<Facebook> connection, NativeWebRequest request) {
		List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
		if (userIds.size() == 1) {
			usersConnectionRepository.createConnectionRepository(userIds.get(0)).updateConnection(connection);
			signInAdapter.signIn(userIds.get(0), connection, request);
		} else {
			logger.error("Expected exactly 1 matching user. Got " + userIds.size() + " metching users.");
		}
	}
	
}
