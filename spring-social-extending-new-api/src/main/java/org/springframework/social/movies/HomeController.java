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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.social.movies.account.AccountRepository;
import org.springframework.social.movies.netflix.NetFlixApi;
import org.springframework.social.movies.netflix.NetFlixServiceProvider;
import org.springframework.social.movies.netflix.QueueItem;
import org.springframework.social.movies.review.Review;
import org.springframework.social.movies.review.ReviewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private final NetFlixServiceProvider netflixProvider;
	
	private final AccountRepository accountRepository;

	private final ReviewRepository reviewRepository;


	@Inject
	public HomeController(NetFlixServiceProvider netflixProvider, AccountRepository userRepository, ReviewRepository reviewRepository) {
		this.netflixProvider = netflixProvider;
		this.accountRepository = userRepository;
		this.reviewRepository = reviewRepository;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {
		if (netflixProvider.isConnected(currentUser.getName())) {
			NetFlixApi netflix = netflixProvider.getConnections(currentUser.getName()).get(0).getServiceApi();
			List<QueueItem> discQueue = netflix.getDiscQueue();
			
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
	
}
