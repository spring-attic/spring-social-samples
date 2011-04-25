package org.springframework.social.showcase.twitter;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.twitter.api.TwitterApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TwitterSearchController {

	private final Provider<TwitterApi> twitterApiProvider;
	
	@Inject
	public TwitterSearchController(Provider<TwitterApi> twitterApiProvider) {
		this.twitterApiProvider = twitterApiProvider;
	}

	@RequestMapping(value="/twitter/search", method=RequestMethod.GET)
	public String showTrends(@RequestParam("query") String query, Model model) {
		model.addAttribute("timeline", getTwitterApi().searchOperations().search(query).getTweets());
		return "twitter/timeline";
	}
	
	private TwitterApi getTwitterApi() {
		return twitterApiProvider.get();
	}
	
}
