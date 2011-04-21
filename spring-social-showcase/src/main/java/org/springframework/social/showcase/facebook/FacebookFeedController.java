package org.springframework.social.showcase.facebook;

import javax.inject.Inject;

import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookFeedController {

	private final FacebookApi facebookApi;

	@Inject
	public FacebookFeedController(FacebookApi facebookApi) {
		this.facebookApi = facebookApi;
	}

	@RequestMapping(value="/facebook/feed", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("feed", facebookApi.feedOperations().getFeed());
		return "facebook/feed";
	}
	
	@RequestMapping(value="/facebook/feed", method=RequestMethod.POST)
	public String postUpdate(String message) {
		facebookApi.feedOperations().updateStatus(message);
		return "redirect:/facebook/feed";
	}
}
