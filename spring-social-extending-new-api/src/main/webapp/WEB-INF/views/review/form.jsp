<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Write a review</h3>

<form method="POST">
	<input type="hidden" name="netflixId" value="${review.netflixId}"/>
	<input type="hidden" name="movieTitle" value="${review.movieTitle}"/>
	<p>What did you think about <b>${review.movieTitle}</b> ?</p>
	<textarea name="text" cols="60" rows="5"></textarea>
	<input type="submit" value="Submit Review"/>
</form>

