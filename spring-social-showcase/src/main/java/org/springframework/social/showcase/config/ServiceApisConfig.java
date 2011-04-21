package org.springframework.social.showcase.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionRepository;
import org.springframework.social.facebook.api.FacebookApi;

@Configuration
public class ServiceApisConfig {
	
	@Inject
	private ServiceProviderConnectionRepository connectionRepository;
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public FacebookApi facebookApi() {
		ServiceProviderConnection<FacebookApi> connection = connectionRepository.findConnectionByServiceApi(FacebookApi.class);
		return connection != null ? connection.getServiceApi() : null;
	}
}
