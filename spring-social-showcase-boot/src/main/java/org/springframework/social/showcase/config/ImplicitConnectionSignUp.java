package org.springframework.social.showcase.config;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class ImplicitConnectionSignUp implements ConnectionSignUp {

	private JdbcTemplate jdbcTemplate;

	public ImplicitConnectionSignUp(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public String execute(Connection<?> connection) {
		UserProfile userProfile = connection.fetchUserProfile();
		String username = connection.getKey().getProviderId() + ":" + userProfile.getUsername();
		String firstName = userProfile.getFirstName();
		firstName = firstName != null ? firstName : "";
		String lastName = userProfile.getLastName();
		lastName = lastName != null ? firstName : "";
		jdbcTemplate.update("insert into Account (username, firstName, lastName) values (?, ?, ?)", username, firstName, lastName);
		return username;
	}
	
}
