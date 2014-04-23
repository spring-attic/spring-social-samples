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
package org.springframework.social.movies.review;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReviewRepository implements ReviewRepository {
	
	private final JdbcTemplate jdbcTemplate;

	@Inject
	public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;}
	
	public void saveReview(Review review) {
		jdbcTemplate.update("insert into Review (movieTitle, author, submitted, text, netflixId) values (?, ?, ?, ?, ?)", 
				review.getMovieTitle(), review.getAuthor(), new Date(), review.getText(), review.getNetflixId());
	}
	
	public List<Review> getRecentReviews() {
		return jdbcTemplate.query(
			"select movieTitle, author, submitted, text, netflixId from Review order by submitted desc limit ?",
			new ReviewRowMapper(), RECENT_REVIEW_COUNT);
	}
	
	public List<Review> findReviewsForQueueItems(List<String> queueItemIds) {
		NamedParameterJdbcTemplate npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		Map<String, List> parameters = new HashMap<String, List>();
		parameters.put("netflixIds", queueItemIds);
		return npJdbcTemplate.query("select movieTitle, author, submitted, text, netflixId from Review where netflixId in (:netflixIds) order by submitted desc", 
			parameters, new ReviewRowMapper());
	}
	
	private static final int RECENT_REVIEW_COUNT = 20;

}
