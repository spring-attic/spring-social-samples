/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.movies.netflix;

import org.springframework.social.connect.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.connect.support.ConnectionRepository;
import org.springframework.social.oauth1.OAuth1Template;
import org.springframework.social.oauth1.OAuth1Version;

public final class NetFlixServiceProvider extends AbstractOAuth1ServiceProvider<NetFlixApi> {

	public NetFlixServiceProvider(String consumerKey, String consumerSecret, ConnectionRepository connectionRepository) {
		super("netflix", connectionRepository, consumerKey, consumerSecret, 
			new OAuth1Template(consumerKey, consumerSecret, 
				"http://api.netflix.com/oauth/request_token",
				"https://api-user.netflix.com/oauth/login?oauth_token={requestToken}&" +
					"oauth_callback={redirectUri}&oauth_consumer_key=" + consumerKey,
				"http://api.netflix.com/oauth/access_token", OAuth1Version.CORE_10));
	}

	@Override
	protected NetFlixApi getApi(String consumerKey, String consumerSecret, String accessToken, String secret) {
		return new NetFlixTemplate(consumerKey, consumerSecret, accessToken, secret);
	}
	
}