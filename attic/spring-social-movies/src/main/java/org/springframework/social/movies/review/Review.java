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

public class Review {
	private final String movieTitle;
	private final String author;
	private final Date submitted;
	private final String text;
	private final String netflixId;

	public Review(String movieTitle, String author, Date submitted, String text, String netflixId) {
		this.movieTitle = movieTitle;
		this.author = author;
		this.submitted = submitted;
		this.text = text;
		this.netflixId = netflixId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public Date getSubmitted() {
		return submitted;
	}
	
	public String getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public String getNetflixId() {
		return netflixId;
	}
}
