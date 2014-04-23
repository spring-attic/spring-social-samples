package org.springframework.social.showcase;

import org.springframework.social.RateLimitExceededException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

	@ExceptionHandler(RateLimitExceededException.class)
	public String rateLimitExceeded(RateLimitExceededException e, Model model) {
		model.addAttribute("providerId", e.getProviderId());
		return "ratelimit";
	}
	
}
