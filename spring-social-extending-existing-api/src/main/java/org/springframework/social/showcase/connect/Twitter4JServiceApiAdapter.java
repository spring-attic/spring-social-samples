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
package org.springframework.social.showcase.connect;

import org.springframework.social.connect.ServiceApiAdapter;
import org.springframework.social.connect.ServiceProviderConnectionValues;
import org.springframework.social.connect.ServiceProviderUserProfile;
import org.springframework.social.connect.ServiceProviderUserProfileBuilder;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class Twitter4JServiceApiAdapter implements ServiceApiAdapter<Twitter> {

	@Override
	public boolean test(Twitter serviceApi) {
		try {
			serviceApi.verifyCredentials();
			return true;
		} catch (TwitterException e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(Twitter serviceApi, ServiceProviderConnectionValues values) {
		try {
			User user = serviceApi.verifyCredentials();
			values.setProviderUserId(Long.toString(user.getId()));
			values.setDisplayName("@" + user.getScreenName());
			values.setProfileUrl("http://twitter.com/" + user.getScreenName());
			values.setImageUrl(user.getProfileImageURL().toExternalForm());
		} catch (TwitterException e) {
		}		
	}

	@Override
	public ServiceProviderUserProfile fetchUserProfile(Twitter serviceApi) {
		try {
			User user = serviceApi.verifyCredentials();
			return new ServiceProviderUserProfileBuilder().setName(user.getName()).setUsername(user.getScreenName()).build();
		} catch (TwitterException e) {
			return null;
		}		
	}

	@Override
	public void updateStatus(Twitter serviceApi, String message) {
		try {
			serviceApi.updateStatus(message);
		} catch (TwitterException e) {
		}
	}
	
}
