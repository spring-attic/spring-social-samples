package org.springframework.social.showcase;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.ServiceProviderConnectionRepository;
import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.social.linkedin.api.LinkedInApi;
import org.springframework.social.twitter.api.TwitterApi;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ConnectedToHandlerInterceptor extends HandlerInterceptorAdapter {

	@Inject
	private ServiceProviderConnectionRepository connectionRepository;
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getUserPrincipal() != null) {
			request.setAttribute("connectedToTwitter", connectionRepository.findPrimaryConnectionToServiceApi(TwitterApi.class) != null);
			request.setAttribute("connectedToFacebook", connectionRepository.findPrimaryConnectionToServiceApi(FacebookApi.class) != null);
			request.setAttribute("connectedToLinkedIn", connectionRepository.findPrimaryConnectionToServiceApi(LinkedInApi.class) != null);
		}
		return true;
	}


}
