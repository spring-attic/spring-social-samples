package org.springframework.social.connect.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.web.filter.GenericFilterBean;

public class ApiExceptionHandlingFilter extends GenericFilterBean {

	private final static Log logger = LogFactory.getLog(ApiExceptionHandlingFilter.class);

	private ThrowableAnalyzer throwableAnalyzer = new ThrowableAnalyzer();

	private UsersConnectionRepository usersConnectionRepository;
	
	private String filterProcessesUrl = "/refresh";

	private UserIdSource userIdSource;

	public ApiExceptionHandlingFilter(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
		this.usersConnectionRepository = usersConnectionRepository;
		this.userIdSource = userIdSource;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (shouldPerformRefreshPostRequest(httpRequest)) {
			logger.info("Removing stale/revoked connection.");			
			String providerId = getProviderIdFromRequest(httpRequest);
			String currentUserId = userIdSource.getUserId();
			usersConnectionRepository.createConnectionRepository(currentUserId).removeConnections(providerId); 
			logger.info("Initiating refresh request.");
			HttpServletRequest newRequest = createRefreshRequest(httpRequest);
			chain.doFilter(newRequest, httpResponse);
		} else {
			try {
				logger.info("Processing request");
				chain.doFilter(httpRequest, httpResponse);
			} catch (IOException e) {
				logger.info("IOException: " + e.getMessage());
				throw e;
			} catch (Exception e) {
				handleExceptionFromFilterChain(e, httpRequest, httpResponse);
			}
		}
		
	}

	private String getProviderIdFromRequest(HttpServletRequest httpRequest) {
		return httpRequest.getServletPath().substring(filterProcessesUrl.length()+1);  // TODO: Seems hackish
	}
	
	private boolean shouldPerformRefreshPostRequest(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		return servletPath != null && servletPath.startsWith(filterProcessesUrl) && request.getMethod().equalsIgnoreCase("GET");
	}

	private HttpServletRequest createRefreshRequest(HttpServletRequest httpRequest) {		
		return new HttpServletRequestWrapper(httpRequest) {
			@Override
			public String getMethod() {
				return "POST";
			}
			
			@Override
			public String getRequestURI() {
				return getRequestURL().toString();
			}
			
			@Override
			public StringBuffer getRequestURL() {
				return new StringBuffer("http://localhost:8080/spring-social-showcase/connect/facebook");
			}
			
			@Override
			public String getServletPath() {
				return "/connect/facebook";
			}
		};
	}

	private void handleExceptionFromFilterChain(Exception e, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		logger.info("Exception: " + e.getMessage());

		// Try to extract a SpringSecurityException from the stacktrace
		Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
		RuntimeException ase = (AuthenticationException)
				throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);

		if (ase == null) {
			ase = (ApiException) throwableAnalyzer.getFirstThrowableOfType(ApiException.class, causeChain);
		}

		if (ase != null && ase instanceof ApiException) {
			ApiException apiException = (ApiException) ase;

			logger.info("API Exception: " + e.getMessage());

			if (apiException instanceof NotAuthorizedException || apiException instanceof OperationNotPermittedException) {
				String scopeNeeded = null;
				if (apiException instanceof InsufficientPermissionException) {
					InsufficientPermissionException ipe = (InsufficientPermissionException) apiException;
					scopeNeeded = ipe.getRequiredPermission();
				}
				
				// This needs to POST, not redirect as a GET
				String redirectUrl = httpRequest.getContextPath() + "/refresh/" + apiException.getProviderId() + "?go=1" + (scopeNeeded != null ? "&scope=" + scopeNeeded : "");
				logger.info("Redirecting for refresh of " + apiException.getProviderId() + " connection.");
				httpResponse.sendRedirect(redirectUrl);
				return;
			}
		}

		if (e instanceof ServletException) {
			throw (ServletException) e;
		}
		else if (e instanceof RuntimeException) {
			throw (RuntimeException) e;
		}

		// Wrap other Exceptions. This shouldn't actually happen
		// as we've already covered all the possibilities for doFilter
		throw new RuntimeException(e);
	}
}
