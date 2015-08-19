<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/spring-social/facebook/tags" prefix="facebook" %>
<%@ page session="false" %>

<h3>Connect to Facebook</h3>

<c:url value="/connect/facebook" var="connectUrl" />
<sf:form action="${connectUrl}" method="POST">
	<input type="hidden" name="scope" value="read_stream,user_posts,user_photos" />
	<div class="formInfo">
		<p>You aren't connected to Facebook yet. Click the button to connect Spring Social Showcase with your Facebook account.</p>
	</div>
	<p><button type="submit"><img src="<c:url value="/resources/social/facebook/connect_light_medium_short.gif" />"/></button></p>
	<label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about Spring Social Showcase on your Facebook wall</label>
</sf:form>
