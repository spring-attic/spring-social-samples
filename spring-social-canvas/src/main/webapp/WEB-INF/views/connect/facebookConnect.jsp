<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/spring-social/facebook/tags" prefix="facebook" %>
<%@ page session="false" %>

<h3>Connect to Facebook</h3>

<form action="<c:url value="/connect/facebook" />" method="GET">
	<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
	<div class="formInfo">
		<p>You aren't connected to Facebook yet. Click the button to connect Spring Social Canvas with your Facebook account.</p>
	</div>
	<p><button type="submit"><img src="<c:url value="/resources/social/facebook/connect_light_medium_short.gif" />"/></button></p>
</form>


<a href="javascript:top.location.href='https://www.facebook.com/dialog/oauth?client_id=265379450157120&redirect_uri=http:%2F%2Fapps.facebook.com%2Fspringsocialcanvas%2F';">Connect to Facebook</a>

<br/>

<a href="javascript:top.location.href='https://www.facebook.com/dialog/oauth?client_id=265379450157120&redirect_uri=http:%2F%2Flocalhost:8080%2Fspring-social-canvas%2Fconnect%2Ffacebook';">Connect to Facebook</a>