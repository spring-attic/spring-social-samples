/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tr.edu.boun.secretary.twitter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.edu.boun.secretary.service.TwitterService;

import javax.inject.Inject;

@Controller
public class TwitterFriendsController {


	private final TwitterService twitterService;
	
	@Inject
	public TwitterFriendsController(TwitterService twitterService) {
		this.twitterService = twitterService;
	}
	
	@RequestMapping(value="/twitter/friends", method=RequestMethod.GET)
	public String friends(Model model) {
		model.addAttribute("profiles", twitterService.getAllFriends());
		return "twitter/friends";
	}

	@RequestMapping(value="/twitter/followers", method=RequestMethod.GET)
	public String followers(Model model) {
		model.addAttribute("profiles", twitterService.getAllFollowers());
		return "twitter/friends";
	}

    @RequestMapping(value="/twitter/unfollowers", method=RequestMethod.GET)
    public String unfollowers(Model model) {
        model.addAttribute("profiles", twitterService.getUnfollowers());
        return "twitter/friends";
    }

}
