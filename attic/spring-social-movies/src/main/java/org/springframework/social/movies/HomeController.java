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
package org.springframework.social.movies;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.movies.account.AccountRepository;
import org.springframework.social.movies.netflix.api.NetFlixApi;
import org.springframework.social.movies.netflix.api.QueueItem;
import org.springframework.social.movies.review.Review;
import org.springframework.social.movies.review.ReviewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
		
	private final AccountRepository accountRepository;

	private final ReviewRepository reviewRepository;

	private final ConnectionRepository connectionRepository;


	@Inject
	public HomeController(ConnectionRepository connectionRepository, AccountRepository userRepository, ReviewRepository reviewRepository) {
		this.connectionRepository = connectionRepository;
		this.accountRepository = userRepository;
		this.reviewRepository = reviewRepository;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {		
		if (getNetFlixApi() != null) {
			List<QueueItem> discQueue = getNetFlixApi().getDiscQueue();
			
			List<String> queueItemIds = new ArrayList<String>();
			for (QueueItem queueItem : discQueue) {
				queueItemIds.add(queueItem.getId());
			}
			
			List<Review> queueReviews = reviewRepository.findReviewsForQueueItems(queueItemIds);
			
			model.addAttribute("discQueue", discQueue);
			model.addAttribute("queueReviews", queueReviews);
			model.addAttribute("netflix_connected", true);
		}
		model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
		model.addAttribute("recentReviews", reviewRepository.getRecentReviews());
		return "home";
	}

	private NetFlixApi getNetFlixApi() {
		Connection<NetFlixApi> connection = connectionRepository.findPrimaryConnection(NetFlixApi.class);
		return connection != null ? connection.getApi() : null;
	}
	
}
