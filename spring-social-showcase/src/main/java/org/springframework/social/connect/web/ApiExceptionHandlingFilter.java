package org.springframework.social.connect.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.web.filter.GenericFilterBean;

public class ApiExceptionHandlingFilter extends GenericFilterBean {

	private ThrowableAnalyzer throwableAnalyzer = new ThrowableAnalyzer();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			chain.doFilter(request, response);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			
			// Try to extract a SpringSecurityException from the stacktrace
			Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
			RuntimeException ase = (AuthenticationException)
					throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);

			if (ase == null) {
				ase = (ApiException) throwableAnalyzer.getFirstThrowableOfType(ApiException.class, causeChain);
			}

			if (ase != null && ase instanceof ApiException) {
				ApiException apiException = (ApiException) ase;
				
				if (apiException instanceof NotAuthorizedException || apiException instanceof OperationNotPermittedException) {
					HttpServletRequest httpRequest = (HttpServletRequest) request;
					HttpServletResponse httpResponse = (HttpServletResponse) response;
					String scopeNeeded = null;
					if (apiException instanceof InsufficientPermissionException) {
						InsufficientPermissionException ipe = (InsufficientPermissionException) apiException;
						scopeNeeded = ipe.getRequiredPermission();
					}
					
					String redirectUrl = httpRequest.getContextPath() + "/connect/" + apiException.getProviderId() + "?go=1" + (scopeNeeded != null ? "&scope=" + scopeNeeded : "");
					
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

}
