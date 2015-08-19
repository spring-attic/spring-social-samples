<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Connected to Facebook</h3>
<c:url value="/connect/facebook" var="disconnectUrl"/>
<sf:form id="disconnect" action="${disconnectUrl}" method="post">
	<div class="formInfo">
		<p>
			Spring Social Showcase is connected to your Facebook account.
			Click the button if you wish to disconnect.
		</p>		
	</div>
	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</sf:form>

<a href="<c:url value="/facebook"/>">View your Facebook profile</a>
