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
package tr.edu.boun.secretary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.edu.boun.secretary.repository.AccountRepository;

import java.security.Principal;

@Controller
public class HomeController {

	private final UsersConnectionRepository usersConnectionRepository;

	private final AccountRepository accountRepository;

	@Autowired
	public HomeController(UsersConnectionRepository usersConnectionRepository, AccountRepository accountRepository) {
		this.usersConnectionRepository = usersConnectionRepository;
		this.accountRepository = accountRepository;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {
		//model.addAttribute("connectionsToProviders", usersConnectionRepository.findUserIdsWithConnection());
		if (currentUser != null) {
			model.addAttribute(accountRepository.findByUsername(currentUser.getName()));
		}
		return "home";
	}

}
