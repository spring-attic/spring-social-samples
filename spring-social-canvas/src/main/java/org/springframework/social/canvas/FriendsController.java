package org.springframework.social.canvas;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FriendsController {

	private Facebook facebook;

	@Inject
	public FriendsController(Facebook facebook) {
		this.facebook = facebook;
	}
	
	@RequestMapping(value="/friends", method=RequestMethod.GET)
	public String homePage(Model model) {		
		model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());
		return "friends";
	}
}
