package org.springframework.social.showcase.twitter;

import javax.inject.Inject;

import org.springframework.social.twitter.api.TwitterApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TwitterSearchController {

	private final TwitterApi twitterApi;
	
	@Inject
	public TwitterSearchController(TwitterApi twitterApi) {
		this.twitterApi = twitterApi;
	}

	@RequestMapping(value="/twitter/search", method=RequestMethod.GET)
	public String showTrends(@RequestParam("query") String query, Model model) {
		model.addAttribute("timeline", twitterApi.searchOperations().search(query).getTweets());
		return "twitter/timeline";
	}
}
