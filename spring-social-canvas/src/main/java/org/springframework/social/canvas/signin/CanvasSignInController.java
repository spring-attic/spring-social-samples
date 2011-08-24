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
package org.springframework.social.canvas.signin;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Subclass of ProviderSignInController to support signin in a Facebook Canvas application.
 * @author Craig Walls
 */
public class CanvasSignInController extends ProviderSignInController {

	private final String canvasPageUrl;

	public CanvasSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SignInAdapter signInAdapter, String canvasPageUrl) {
		super(connectionFactoryLocator, usersConnectionRepository, signInAdapter);
		this.canvasPageUrl = canvasPageUrl;
	}

	/*
	 * Performs the default handling of OAuth 2 callback, but returns a redirect to http://apps.facebook.com/{canvasPageUrl}/ so that the
	 * browser ends up back on the canvas page. 
	 */
	public RedirectView oauth2Callback(@PathVariable String providerId, @RequestParam("code") String code, NativeWebRequest request) {
		super.oauth2Callback(providerId, code, request);
		return new RedirectView(canvasPageUrl);
	}
}
