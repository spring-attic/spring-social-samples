package org.springframework.social.showcase.linkedin;

import javax.inject.Inject;

import org.springframework.social.linkedin.api.LinkedInApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinkedInConnectionsController {

	private final LinkedInApi linkedInApi;

	@Inject
	public LinkedInConnectionsController(LinkedInApi linkedInApi) {
		this.linkedInApi = linkedInApi;
	}

	@RequestMapping(value="/linkedin/connections", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("connections", linkedInApi.getConnections());
		return "linkedin/connections";
	}
	
}
