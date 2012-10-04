package org.springframework.social.connect.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation indicating that a controller handler method requires a connection to a specified provider.
 * Optionally, may also specify scope(s) that are required for the request to complete successfully. 
 * @author habuma
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresConnection {
	
	/**
	 * @return the ID of the provider for which a connection is required.
	 */
	String value();

	/**
	 * @return the connection scope that is required.
	 */
	String[] scope() default {};

}
