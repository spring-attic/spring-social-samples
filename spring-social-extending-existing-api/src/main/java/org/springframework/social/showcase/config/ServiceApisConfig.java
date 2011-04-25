package org.springframework.social.showcase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionRepository;

import twitter4j.Twitter;

@Configuration
public class ServiceApisConfig {
	
	@Bean
	@Scope(value="request")
	public Twitter twitterApi(ServiceProviderConnectionRepository connectionRepository) {
		ServiceProviderConnection<Twitter> connection = connectionRepository.findPrimaryConnectionToServiceApi(Twitter.class);
		return connection != null ? connection.getServiceApi() : null;
	}

}
