package org.springframework.social.movies.review;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReviewRowMapper implements RowMapper<Review> {
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Review(rs.getString("movieTitle"), rs.getString("author"), rs.getDate("submitted"), rs.getString("text"), rs.getString("netflixId"));
	}
}
