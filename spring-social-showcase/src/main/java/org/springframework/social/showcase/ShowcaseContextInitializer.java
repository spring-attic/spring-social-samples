package org.springframework.social.showcase;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class ShowcaseContextInitializer extends PropertiesLoaderSupport implements
		ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext context) {
		ConfigurableEnvironment environment = context.getEnvironment();
		Properties properties = null;
		
		// ideally, the else would also be able to check acceptsProfiles("embedded"), but it seems that spring.profiles.default
		// set in web.xml isn't available here.
		if (environment.acceptsProfiles("standard")) {
			properties = loadProperties(context, "classpath:application.properties");
		} else {
			properties = buildDefaultProperties();
		}
		environment.getPropertySources().addFirst(new PropertiesPropertySource("showcaseProperties", properties));
	}

	private Properties buildDefaultProperties() {
		Properties defaultProperties = new Properties();
		defaultProperties.setProperty("application.url", "http://localhost:8080/spring-social-showcase");
		defaultProperties.setProperty("twitter.consumerKey", "YR571S2JiVBOFyJS5MEg");
		defaultProperties.setProperty("twitter.consumerSecret", "Kb8hS0luftwCJX3qVoyiLUMfZDtK1EozFoUkjNLUMx4");
		defaultProperties.setProperty("facebook.apiKey", "0b754d95f9c9899b0d6c4454b6f2dde7");
		defaultProperties.setProperty("facebook.appSecret", "fa8a9825f555a7a8949ec48fb93bda58");
		defaultProperties.setProperty("tripit.consumerKey", "ee68437fbf8f42315a1b1ebe5a2a3009bd3f5cf9");
		defaultProperties.setProperty("tripit.consumerSecret", "6b3cf4d183cc02076d068eea91080ca0bf666336");
		return defaultProperties;
	}

	private Properties loadProperties(ConfigurableApplicationContext context, String location) {
		Properties properties = new Properties();
		try {
			setLocation(context.getResource(location));
			loadProperties(properties);
		} catch (IOException e) {
		}
		return properties;
	}

}
