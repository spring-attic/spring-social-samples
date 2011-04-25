package org.springframework.social.showcase.facebook;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookFriendsController {

	private final Provider<FacebookApi> facebookApiProvider;

	@Inject
	public FacebookFriendsController(Provider<FacebookApi> facebookApiProvider) {
		this.facebookApiProvider = facebookApiProvider;
	}

	@RequestMapping(value="/facebook/friends", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("friends", getFacebookApi().friendOperations().getFriendProfiles());
		return "facebook/friends";
	}
	
	private FacebookApi getFacebookApi() {
		return facebookApiProvider.get();
	}
	
}
