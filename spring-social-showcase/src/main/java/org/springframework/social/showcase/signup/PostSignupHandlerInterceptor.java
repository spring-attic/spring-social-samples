package org.springframework.social.showcase.signup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.web.connect.ConnectController;
import org.springframework.social.web.connect.DeferredConnectionDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PostSignupHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		BindingResult bindingResult = BindingResultUtils.getBindingResult(modelAndView.getModelMap(), "signupForm");
		if (!bindingResult.hasErrors() && request.getMethod().equals("POST")) {
			DeferredConnectionDetails deferredConnectionDetails = (DeferredConnectionDetails) request.getSession().getAttribute(ConnectController.DEFERRED_CONNECTION_DETAILS_ATTRIBUTE);
			if (deferredConnectionDetails != null) {
				modelAndView.setViewName("redirect:/connect/" + deferredConnectionDetails.getProviderId()
						+ "?deferred&targetView=" + modelAndView.getViewName());
			}
		}

		super.postHandle(request, response, handler, modelAndView);
	}

}
