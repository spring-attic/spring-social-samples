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
package org.springframework.social.twitter4j.connect;

import java.util.Properties;

import org.springframework.social.AccountNotConnectedException;
import org.springframework.social.connect.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.connect.support.ConnectionRepository;
import org.springframework.social.oauth1.OAuth1Template;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.PropertyConfiguration;

/**
 * Twitter4J ServiceProvider implementation.
 * 
 * @author Craig Walls
 */
public final class Twitter4JServiceProvider extends AbstractOAuth1ServiceProvider<Twitter> {

	public Twitter4JServiceProvider(String consumerKey, String consumerSecret, ConnectionRepository connectionRepository) {
		super("twitter", connectionRepository, consumerKey, consumerSecret, new OAuth1Template(consumerKey, consumerSecret,
				"https://twitter.com/oauth/request_token",
				"https://twitter.com/oauth/authorize?oauth_token={requestToken}",
				"https://twitter.com/oauth/access_token"));
	}

	@Override
	protected Twitter getApi(String consumerKey, String consumerSecret, String accessToken, String secret) {
		Properties props = new Properties();
		props.setProperty(PropertyConfiguration.OAUTH_CONSUMER_KEY, consumerKey);
		props.setProperty(PropertyConfiguration.OAUTH_CONSUMER_SECRET, consumerSecret);
		props.setProperty(PropertyConfiguration.OAUTH_ACCESS_TOKEN, accessToken);
		props.setProperty(PropertyConfiguration.OAUTH_ACCESS_TOKEN_SECRET, secret);
		Configuration conf = new PropertyConfiguration(props);
		return new TwitterFactory(conf).getInstance();
	}

}