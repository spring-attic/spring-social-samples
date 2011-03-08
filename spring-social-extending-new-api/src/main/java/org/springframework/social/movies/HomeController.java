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
package org.springframework.social.movies;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.social.movies.account.AccountRepository;
import org.springframework.social.movies.netflix.NetFlixApi;
import org.springframework.social.movies.netflix.NetFlixServiceProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private final NetFlixServiceProvider netflixProvider;
	
	private final AccountRepository accountRepository;

	@Inject
	public HomeController(NetFlixServiceProvider netflixProvider, AccountRepository userRepository) {
		this.netflixProvider = netflixProvider;
		this.accountRepository = userRepository;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {
		if (netflixProvider.isConnected(currentUser.getName())) {
			NetFlixApi netflix = netflixProvider.getConnections(currentUser.getName()).get(0).getServiceApi();
			model.addAttribute("discQueue", netflix.getDiscQueue());
			model.addAttribute("netflix_connected", true);
		}
		model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
		return "home";
	}
}
