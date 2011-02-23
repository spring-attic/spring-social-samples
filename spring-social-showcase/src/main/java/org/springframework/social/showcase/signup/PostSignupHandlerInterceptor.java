package org.springframework.social.showcase.signup;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.web.connect.ConnectController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PostSignupHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		BindingResult bindingResult = BindingResultUtils.getBindingResult(modelAndView.getModelMap(), "signupForm");
		if (!bindingResult.hasErrors() && request.getMethod().equals("POST")) {
			Properties deferredConnectionDetails = (Properties) request.getSession().getAttribute(ConnectController.DEFERRED_CONNECTION_DETAILS_ATTRIBUTE);
			if (deferredConnectionDetails != null) {
				deferredConnectionDetails.setProperty("targetView", modelAndView.getViewName());
				modelAndView.setViewName("redirect:/connect/" + deferredConnectionDetails.getProperty("providerId") + "?deferred");
			}
		}

		super.postHandle(request, response, handler, modelAndView);
	}

}
