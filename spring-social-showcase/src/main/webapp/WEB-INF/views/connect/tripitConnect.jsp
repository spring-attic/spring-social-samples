<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Connect to TripIt</h3>

<form action="<c:url value="/connect/tripit" />" method="POST">
	<div class="formInfo">
		<p>You haven't created any connections with TripIt yet. Click the button to connect Spring Social Showcase with your TripIt account. 
		(You'll be redirected to TripIt where you'll be asked to authorize the connection.)</p>
	</div>
	<p><button type="submit">Connect to TripIt</button></p>
</form>
