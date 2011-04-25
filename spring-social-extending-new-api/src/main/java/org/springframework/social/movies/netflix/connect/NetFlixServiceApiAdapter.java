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
package org.springframework.social.movies.netflix.connect;

import org.springframework.social.connect.ServiceApiAdapter;
import org.springframework.social.connect.ServiceProviderConnectionValues;
import org.springframework.social.connect.ServiceProviderUserProfile;
import org.springframework.social.connect.ServiceProviderUserProfileBuilder;
import org.springframework.social.movies.netflix.api.NetFlixApi;
import org.springframework.social.movies.netflix.api.NetFlixUserProfile;
import org.springframework.web.client.HttpClientErrorException;

public class NetFlixServiceApiAdapter implements ServiceApiAdapter<NetFlixApi> {

	@Override
	public ServiceProviderUserProfile fetchUserProfile(NetFlixApi netflixApi) {
		NetFlixUserProfile profile = netflixApi.getUserProfile();		
		String firstName = profile.getFirstName();
		String lastName = profile.getLastName();
		String fullName = firstName + " " + lastName;
		return new ServiceProviderUserProfileBuilder().setFirstName(firstName).setLastName(lastName).setName(fullName).build();
	}

	@Override
	public boolean test(NetFlixApi netflixApi) {
		try {
			netflixApi.getUserProfile();
			return true;
		} catch (HttpClientErrorException e) {
			return false;
		}
	}

	@Override
	public void updateStatus(NetFlixApi netflixApi, String message) {
		// not supported		
	}

	@Override
	public void setConnectionValues(NetFlixApi netflixApi, ServiceProviderConnectionValues values) {
		NetFlixUserProfile profile = netflixApi.getUserProfile();
		values.setProviderUserId(profile.getId());
		values.setDisplayName(profile.getFirstName() + " " + profile.getLastName());
	}

}
