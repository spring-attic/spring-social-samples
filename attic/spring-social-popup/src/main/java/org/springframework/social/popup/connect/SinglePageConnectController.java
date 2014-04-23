/*
 * Copyright 2014 the original author or authors.
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
package org.springframework.social.popup.connect;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.ui.Model;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Overrides ConnectController to demonstrate how to customize the default flow.
 * In this case, there will be no per-provider status page--only a master status page.
 * @author Craig Walls
 */
public class SinglePageConnectController extends ConnectController {

	public SinglePageConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	/**
	 * Override the per-provider status page handler to simply redirect to the master status page.
	 */
	@Override
	public String connectionStatus(String providerId, NativeWebRequest request, Model model) {
		return "redirect:/connect";
	}
}
