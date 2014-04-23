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
package org.springframework.social.movies.netflix.connect;

import org.springframework.social.movies.netflix.api.NetFlixApi;
import org.springframework.social.movies.netflix.api.impl.NetFlixTemplate;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;

public final class NetFlixServiceProvider extends AbstractOAuth1ServiceProvider<NetFlixApi> {

	public NetFlixServiceProvider(String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret, 
			new NetFlixOAuth1Template(consumerKey, consumerSecret, 
				"http://api.netflix.com/oauth/request_token",
				"https://api-user.netflix.com/oauth/login",
				"http://api.netflix.com/oauth/access_token"));
	}

	@Override
	public NetFlixApi getApi(String accessToken, String secret) {
		return new NetFlixTemplate(getConsumerKey(), getConsumerSecret(), accessToken, secret);
	}
	
}
