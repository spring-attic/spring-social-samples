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
package org.springframework.social.showcase.signup;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.signin.web.ProviderSignInUtils;
import org.springframework.social.showcase.account.Account;
import org.springframework.social.showcase.account.AccountRepository;
import org.springframework.social.showcase.account.UsernameAlreadyInUseException;
import org.springframework.social.showcase.signin.SpringSecuritySigninService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignupController {

	private final AccountRepository accountRepository;

	private final SpringSecuritySigninService signinService;

	@Inject
	public SignupController(AccountRepository accountRepository, SpringSecuritySigninService signinService) {
		this.accountRepository = accountRepository;
		this.signinService = signinService;
	}

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {
		ServiceProviderConnection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			return new SignupForm();
		}
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		boolean accountCreated = createAccount(form, formBinding);
		if (accountCreated) {
			ProviderSignInUtils.handlePostSignUp(request);
			return "redirect:/";
		}
		return null;
	}

	// internal helpers
	
	private boolean createAccount(SignupForm form, BindingResult formBinding) {
		try {
			Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName());
			accountRepository.createAccount(account);
			signinService.signIn(account.getUsername());
			return true;
		} catch (UsernameAlreadyInUseException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return false;
		}
	}

}
