package org.springframework.social.showcase.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionRepository;
import org.springframework.social.facebook.FacebookApi;
import org.springframework.social.tripit.TripItApi;

@Configuration
public class ServiceProviderConnectionsConfig {

	@Inject
	private ServiceProviderConnectionRepository serviceProviderConnectionRepository;

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public ServiceProviderConnection<FacebookApi> facebookConnection() {
		return serviceProviderConnectionRepository.findConnectionByServiceApi(FacebookApi.class);
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public ServiceProviderConnection<TripItApi> tripItConnection() {
		return serviceProviderConnectionRepository.findConnectionByServiceApi(TripItApi.class);
	}
}
