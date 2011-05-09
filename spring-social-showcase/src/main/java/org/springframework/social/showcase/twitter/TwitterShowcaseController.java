/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.showcase.twitter;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterShowcaseController {

	private final Provider<Twitter> twitterApiProvider;
	
	@Inject
	public TwitterShowcaseController(Provider<Twitter> twitterApiProvider) {
		this.twitterApiProvider = twitterApiProvider;
	}

	@RequestMapping(value="/twitter", method=RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		if (getTwitter() == null) {
			return "redirect:/connect/twitter";
		}
		model.addAttribute("profile", getTwitter().userOperations().getUserProfile());
		return "twitter/profile";
	}
	
	private Twitter getTwitter() {
		return twitterApiProvider.get();
	}
	
}