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
package org.springframework.social.quickstart.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.signin.web.ProviderSignInController;
import org.springframework.social.connect.signin.web.SignInAdapter;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.quickstart.user.SecurityContext;
import org.springframework.social.quickstart.user.User;

@Configuration
public class SocialConfig {

	@Inject
	private Environment environment;

	@Inject
	private DataSource dataSource;

	/**
	 * When a new provider is added to the app, register its {@link ConnectionFactory} here.
	 */
	@Bean
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(environment.getProperty("facebook.clientId"), environment.getProperty("facebook.clientSecret")));
		return registry;
	}

	/**
	 * The data store for connections across all users.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), Encryptors.noOpText());
	}

	/**
	 * A request-scoped bean that provides access to the current user's connections.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		User user = SecurityContext.getCurrentUser();
		return usersConnectionRepository().createConnectionRepository(user.getId());
	}

	/**
	 * A proxy to a request-scoped bean representing the current user's primary Facebook account.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
		return connectionRepository().findPrimaryConnection(Facebook.class).getApi();
	}

	/**
	 * The Spring MVC Controller that establishes connections to providers on behalf of users.
	 */
	@Bean
	public ConnectController connectController() {
		return new ConnectController(environment.getProperty("application.secureUrl"), connectionFactoryLocator(), connectionRepository());
	}

	/**
	 * The Spring MVC Controller that allows users to sign-in with their provider accounts.
	 */
	@Bean
	public ProviderSignInController providerSignInController() {
		return new ProviderSignInController(environment.getProperty("application.secureUrl"), connectionFactoryLocator(), usersConnectionRepository(),
				connectionRepository(), new SimpleSignInAdapter());
	}

	private static class SimpleSignInAdapter implements SignInAdapter {

		public void signIn(String userId) {
			SecurityContext.setCurrentUser(new User(userId));
		}

	}

}