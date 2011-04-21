package org.springframework.social.showcase.facebook;

import javax.inject.Inject;

import org.springframework.social.facebook.api.FacebookApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookFriendsController {

	private final FacebookApi facebookApi;

	@Inject
	public FacebookFriendsController(FacebookApi facebookApi) {
		this.facebookApi = facebookApi;
	}

	@RequestMapping(value="/facebook/friends", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("friends", facebookApi.friendOperations().getFriendProfiles());
		return "facebook/friends";
	}
	
}
