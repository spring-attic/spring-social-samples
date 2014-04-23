<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Connect to LinkedIn</h3>

<form action="<c:url value="/connect/linkedin" />" method="POST">
	<div class="formInfo">
		<p>
			You haven't created any connections with LinkedIn yet. Click the button to connect Spring Social Showcase with your LinkedIn account. 
			(You'll be redirected to LinkedIn where you'll be asked to authorize the connection.)
		</p>
	</div>
	<p><button type="submit">Connect with LinkedIn</button></p>
</form>
