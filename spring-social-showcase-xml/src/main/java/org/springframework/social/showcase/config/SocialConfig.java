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
package org.springframework.social.showcase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring Social Configuration.
 * @author Craig Walls
 */
@Configuration
@ImportResource(value="classpath:/org/springframework/social/showcase/config/social.xml")
public class SocialConfig {

//	@Inject
//	private Environment environment;

//	@Inject
//	private DataSource dataSource;
//
//	@Bean
//	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES) 
//	public ConnectionFactoryLocator connectionFactoryLocator() {
//		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//		registry.addConnectionFactory(new TwitterConnectionFactory(environment.getProperty("twitter.consumerKey"),
//				environment.getProperty("twitter.consumerSecret")));
//		registry.addConnectionFactory(new FacebookConnectionFactory(environment.getProperty("facebook.clientId"),
//				environment.getProperty("facebook.clientSecret")));
//		registry.addConnectionFactory(new LinkedInConnectionFactory(environment.getProperty("linkedin.consumerKey"),
//				environment.getProperty("linkedin.consumerSecret")));
//		return registry;
//	}
//
//	@Bean
//	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES) 
//	public UsersConnectionRepository usersConnectionRepository() {
//		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), Encryptors.noOpText());
//	}
//
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public ConnectionRepository connectionRepository() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null) {
//			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
//		}
//		return usersConnectionRepository().createConnectionRepository(authentication.getName());
//	}
//
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public Facebook facebook() {
//		Connection<Facebook> facebook = connectionRepository().findPrimaryConnection(Facebook.class);
//		return facebook != null ? facebook.getApi() : new FacebookTemplate();
//	}
//	
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public Twitter twitter() {
//		Connection<Twitter> twitter = connectionRepository().findPrimaryConnection(Twitter.class);
//		return twitter != null ? twitter.getApi() : new TwitterTemplate();
//	}
//
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public LinkedIn linkedin() {
//		Connection<LinkedIn> linkedin = connectionRepository().findPrimaryConnection(LinkedIn.class);
//		return linkedin != null ? linkedin.getApi() : null;
//	}

//	@Inject
//	private ConnectionFactoryLocator connectionFactoryLocator;
//	
//	@Inject
//	private ConnectionRepository connectionRepository;
//	
//	@Inject
//	private UsersConnectionRepository usersConnectionRepository;
//	
//	@Bean
//	public ConnectController connectController() {
//		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
//		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
//		connectController.addInterceptor(new TweetAfterConnectInterceptor());
//		return connectController;
//	}
//
//	@Bean
//	public ProviderSignInController providerSignInController(RequestCache requestCache) {
//		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(requestCache));
//	}
//	
//	@Bean
//	public DisconnectController disconnectController() {
//		return new DisconnectController(usersConnectionRepository, environment.getProperty("facebook.clientSecret"));
//	}

}