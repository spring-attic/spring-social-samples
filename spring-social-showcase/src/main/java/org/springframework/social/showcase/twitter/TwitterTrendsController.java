package org.springframework.social.showcase.twitter;

import javax.inject.Inject;

import org.springframework.social.twitter.api.TwitterApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterTrendsController {

	private final TwitterApi twitterApi;
	
	@Inject
	public TwitterTrendsController(TwitterApi twitterApi) {
		this.twitterApi = twitterApi;
	}

	@RequestMapping(value="/twitter/trends/current", method=RequestMethod.GET)
	public String showTrends(Model model) {
		model.addAttribute("trends", twitterApi.searchOperations().getCurrentTrends());
		return "twitter/currentTrends";
	}
}
