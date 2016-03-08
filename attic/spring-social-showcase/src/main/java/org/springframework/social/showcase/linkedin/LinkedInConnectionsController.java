package org.springframework.social.showcase.linkedin;

import javax.inject.Inject;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.NetworkStatistics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinkedInConnectionsController {

	private LinkedIn linkedIn;

	@Inject
	public LinkedInConnectionsController(LinkedIn linkedIn) {
		this.linkedIn = linkedIn;
	}
		
	@RequestMapping(value="/linkedin/connections", method=RequestMethod.GET)
	public String connections(Model model) {
		NetworkStatistics statistics = linkedIn.connectionOperations().getNetworkStatistics();
		model.addAttribute("firstDegreeCount", statistics.getFirstDegreeCount());
		model.addAttribute("secondDegreeCount", statistics.getSecondDegreeCount());
		model.addAttribute("connections", linkedIn.connectionOperations().getConnections());
		return "linkedin/connections";
	}

}
