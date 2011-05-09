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
package org.springframework.social.showcase.tripit;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.tripit.api.TripIt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TripItShowcaseController {
	
	private final Provider<TripIt> tripItApiProvider;

	@Inject
	public TripItShowcaseController(Provider<TripIt> tripItApiProvider) {
		this.tripItApiProvider = tripItApiProvider;
	}

	@RequestMapping(value="/tripit", method=RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		if (getTripIt() == null) {
			return "redirect:/connect/tripit";
		}
		model.addAttribute("tripItUser", getTripIt().getUserProfile());
		model.addAttribute("trips", getTripIt().getUpcomingTrips());
		return "tripit/tripit";
	}
	
	private TripIt getTripIt() {
		return tripItApiProvider.get();
	}

}
