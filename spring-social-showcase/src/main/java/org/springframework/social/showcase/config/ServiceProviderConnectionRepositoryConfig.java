package org.springframework.social.showcase.config;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.MultiUserServiceProviderConnectionRepository;
import org.springframework.social.connect.ServiceProviderConnectionRepository;

@Configuration
public class ServiceProviderConnectionRepositoryConfig {

	@Inject
	private MultiUserServiceProviderConnectionRepository usersConnectionRepository;

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ServiceProviderConnectionRepository serviceProviderConnectionRepository(@Value("#{request.userPrincipal}") Principal principal) {
		if (principal == null) {
			throw new IllegalStateException("Unable to get a ServiceProviderConnectionRepository: no user logged in");
		}
		return usersConnectionRepository.createConnectionRepository(principal.getName());
	}
	
}
