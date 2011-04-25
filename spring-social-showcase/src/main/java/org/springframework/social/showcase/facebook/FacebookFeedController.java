package org.springframework.social.showcase.facebook;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookFeedController {

	private final Provider<FacebookApi> facebookApiProvider;

	@Inject
	public FacebookFeedController(Provider<FacebookApi> facebookApiProvider) {
		this.facebookApiProvider = facebookApiProvider;
	}

	@RequestMapping(value="/facebook/feed", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("feed", getFacebookApi().feedOperations().getFeed());
		return "facebook/feed";
	}
	
	@RequestMapping(value="/facebook/feed", method=RequestMethod.POST)
	public String postUpdate(String message) {
		getFacebookApi().feedOperations().updateStatus(message);
		return "redirect:/facebook/feed";
	}
	
	private FacebookApi getFacebookApi() {
		return facebookApiProvider.get();
	}
	
}
