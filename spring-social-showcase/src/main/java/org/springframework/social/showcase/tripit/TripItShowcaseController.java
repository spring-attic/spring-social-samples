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

import org.springframework.social.connect.ServiceProviderConnection;
import org.springframework.social.connect.ServiceProviderConnectionRepository;
import org.springframework.social.tripit.api.TripItApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TripItShowcaseController {
	
	private final ServiceProviderConnectionRepository connectionRepository;

	private final TripItApi tripitApi;

	@Inject
	public TripItShowcaseController(ServiceProviderConnectionRepository connectionRepository, TripItApi tripitApi) {
		this.connectionRepository = connectionRepository;
		this.tripitApi = tripitApi;
	}

	@RequestMapping(value="/tripit", method=RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		ServiceProviderConnection<TripItApi> connection = connectionRepository.findPrimaryConnectionToServiceApi(TripItApi.class);
		if (connection == null) {
			return "redirect:/connect/tripit";
		}
		model.addAttribute("tripItUser", tripitApi.getUserProfile());
		model.addAttribute("trips", tripitApi.getUpcomingTrips());
		return "tripit/tripit";
	}

}
