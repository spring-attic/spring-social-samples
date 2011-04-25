package org.springframework.social.showcase.twitter;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.twitter.api.TwitterApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterTrendsController {

	private final Provider<TwitterApi> twitterApiProvider;
	
	@Inject
	public TwitterTrendsController(Provider<TwitterApi> twitterApiProvider) {
		this.twitterApiProvider = twitterApiProvider;
	}

	@RequestMapping(value="/twitter/trends/current", method=RequestMethod.GET)
	public String showTrends(Model model) {
		model.addAttribute("trends", getTwitterApi().searchOperations().getCurrentTrends());
		return "twitter/currentTrends";
	}
	
	private TwitterApi getTwitterApi() {
		return twitterApiProvider.get();
	}
	
}
