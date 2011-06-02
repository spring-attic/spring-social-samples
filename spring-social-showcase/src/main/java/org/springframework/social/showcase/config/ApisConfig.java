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
package org.springframework.social.showcase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;

@Configuration
public class ApisConfig {
	
	@Bean
	@Scope(value="request")
	public Facebook facebookApi(ConnectionRepository connectionRepository) {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnectionToApi(Facebook.class);
		return connection != null ? connection.getApi() : null;
	}
	
	@Bean
	@Scope(value="request")
	public Twitter twitterApi(ConnectionRepository connectionRepository) {
		Connection<Twitter> connection = connectionRepository.findPrimaryConnectionToApi(Twitter.class);
		return connection != null ? connection.getApi() : null;
	}

}
