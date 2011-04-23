package org.springframework.social.showcase.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionRepository;
import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.social.tripit.api.TripItApi;
import org.springframework.social.twitter.api.TwitterApi;

public class ServiceProviderConnectionsConfig {

	@Inject
	private ServiceProviderConnectionRepository serviceProviderConnectionRepository;

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public ServiceProviderConnection<FacebookApi> facebookConnection() {
		return serviceProviderConnectionRepository.findPrimaryConnectionToServiceApi(FacebookApi.class);
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public ServiceProviderConnection<TripItApi> tripItConnection() {
		return serviceProviderConnectionRepository.findPrimaryConnectionToServiceApi(TripItApi.class);
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public ServiceProviderConnection<TwitterApi> twitterConnection() {
		return serviceProviderConnectionRepository.findPrimaryConnectionToServiceApi(TwitterApi.class);
	}

}
