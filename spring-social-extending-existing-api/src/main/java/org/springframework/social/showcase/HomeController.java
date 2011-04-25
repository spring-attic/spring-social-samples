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
package org.springframework.social.showcase;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.showcase.account.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import twitter4j.Twitter;

@Controller
public class HomeController {
		
	private final AccountRepository accountRepository;

	private final Provider<Twitter> twitterApiProvider;

	@Inject
	public HomeController(Provider<Twitter> twitterApiProvider, AccountRepository userRepository) {
		this.twitterApiProvider = twitterApiProvider;
		this.accountRepository = userRepository;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {
		Twitter twitter = twitterApiProvider.get();
		model.addAttribute("twitter_status", twitter != null ? "Yes" : "No");
		model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
		return "home";
	}
}
