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
package org.springframework.social.popup.facebook;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * Adds a "display=popup" parameter so that the Facebook authorization is displayed with minimal decoration.
 * @author Craig Walls
 */
public class PopupDialogConnectInterceptor implements ConnectInterceptor<Facebook> {

	@Override
	public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
		parameters.set("display", "popup");
	}

	@Override
	public void postConnect(Connection<Facebook> connection, WebRequest request) {
		// Nothing to do
	}

}
