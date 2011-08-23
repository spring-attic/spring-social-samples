<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<p>Welcome, <c:out value="${account.firstName}"/>!</p>

<c:if test="${not connectedToFacebook}">
<form>
	<div class="formInfo">
		<p>You aren't connected to Facebook yet. Click the button to connect Spring Social Canvas with your Facebook account.</p>
	</div>
	<button type="submit" onclick="top.location.href='https://www.facebook.com/dialog/oauth?client_id=265379450157120&redirect_uri=http:%2F%2Flocalhost:8080%2Fspring-social-canvas%2Fconnect%2Ffacebook';"><img src="<c:url value="/resources/social/facebook/connect_light_medium_short.gif" />"/></button>
</form>
</c:if>

<c:if test="${connectedToFacebook}">
<form action="<c:url value='/connect/facebook'/>" id="disconnect" method="post">
	<div class="formInfo">
		<p>
			Spring Social Canvas is connected to your Facebook account.
			Click the button if you wish to disconnect.
		</p>		
	</div>
	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>
<p><a href="<c:url value='/facebook'/>">View your Facebook profile</a></p>
</c:if>

<a href="<c:url value="/signout" />">Sign Out</a>

