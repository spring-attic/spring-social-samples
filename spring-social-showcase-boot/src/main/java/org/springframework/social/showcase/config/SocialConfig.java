package org.springframework.social.showcase.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

@Configuration
public class SocialConfig extends SocialConfigurerAdapter {

	@Inject
	private DataSource dataSource;
	
	/**
	 * Singleton data access object providing access to connections across all users.
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setConnectionSignUp(new ImplicitConnectionSignUp(dataSource));
		return repository;
	}

}
