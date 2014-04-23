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
package org.springframework.social.showcase.twitter;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.Twitter;

@Controller
public class TweetController {

	private final Provider<ConnectionRepository> connectionRepositoryProvider;

	@Inject
	public TweetController(Provider<ConnectionRepository> connectionRepositoryProvider) {
		this.connectionRepositoryProvider = connectionRepositoryProvider;
	}

	@RequestMapping(value="/twitter/tweet", method=RequestMethod.POST)
	public String postTweet(Principal currentUser, @RequestParam("message") String message) {
		List<Connection<Twitter>> connections = connectionRepositoryProvider.get().findConnections(Twitter.class); 
		for (Connection<Twitter> connection : connections) {
			connection.updateStatus(message);				
		}
		return "redirect:/connect/twitter";
	}

}
