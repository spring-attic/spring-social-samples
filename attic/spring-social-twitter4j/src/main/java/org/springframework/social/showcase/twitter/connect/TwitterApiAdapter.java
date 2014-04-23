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
package org.springframework.social.showcase.twitter.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Twitter Api Adapter that uses the Twitter 4j API binding.
 * @author Craig Walls
 */
public class TwitterApiAdapter implements ApiAdapter<Twitter> {

	@Override
	public boolean test(Twitter api) {
		try {
			api.verifyCredentials();
			return true;
		} catch (TwitterException e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(Twitter api, ConnectionValues values) {
		try {
			User user = api.verifyCredentials();
			values.setProviderUserId(Long.toString(user.getId()));
			values.setDisplayName("@" + user.getScreenName());
			values.setProfileUrl("http://twitter.com/" + user.getScreenName());
			values.setImageUrl(user.getProfileImageURL());
		} catch (TwitterException e) {
		}		
	}

	@Override
	public UserProfile fetchUserProfile(Twitter api) {
		try {
			User user = api.verifyCredentials();
			return new UserProfileBuilder().setName(user.getName()).setUsername(user.getScreenName()).build();
		} catch (TwitterException e) {
			return null;
		}		
	}

	@Override
	public void updateStatus(Twitter api, String message) {
		try {
			api.updateStatus(message);
		} catch (TwitterException e) {
		}
	}
	
}
