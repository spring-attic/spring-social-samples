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
package org.springframework.social.showcase.facebook;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookFeedController {

	private final Provider<Facebook> facebookApiProvider;

	@Inject
	public FacebookFeedController(Provider<Facebook> facebookApiProvider) {
		this.facebookApiProvider = facebookApiProvider;
	}

	@RequestMapping(value="/facebook/feed", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("feed", getFacebook().feedOperations().getFeed());
		return "facebook/feed";
	}
	
	@RequestMapping(value="/facebook/feed", method=RequestMethod.POST)
	public String postUpdate(String message) {
		getFacebook().feedOperations().updateStatus(message);
		return "redirect:/facebook/feed";
	}
	
	private Facebook getFacebook() {
		return facebookApiProvider.get();
	}
	
}
