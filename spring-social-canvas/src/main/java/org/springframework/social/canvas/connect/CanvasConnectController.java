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
package org.springframework.social.canvas.connect;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Subclass of ConnectController to customize the flow to fit Facebook's Canvas application connection flow.
 * @author Craig Walls
 */
public class CanvasConnectController extends ConnectController {

	public CanvasConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	/*
	 * Overrides ConnectController.oauth2Callback() to always redirect back to the application's canvas page. 
	 */
	@Override
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
		super.oauth2Callback(providerId, request);		
		return new RedirectView("http://apps.facebook.com/springsocialcanvas/");
	}
	
	/*
	 * Since this is a Facebook-only application, the home page will serve as the connection status page.
	 */
	@Override
	protected RedirectView connectionStatusRedirect(String providerId) {
		return new RedirectView("/", true);
	}
}
