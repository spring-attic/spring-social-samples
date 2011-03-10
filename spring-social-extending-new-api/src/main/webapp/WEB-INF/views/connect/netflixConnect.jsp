<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h3>Connect to NetFlix</h3>

<form action="<c:url value="/connect/netflix" />" method="POST">
	<div class="formInfo">
		<p>You haven't created any connections with NetFlix yet. Click the button to connect Spring Social NetFlix Sample with your NetFlix account. 
		(You'll be redirected to NetFlix where you'll be asked to authorize the connection.)</p>
	</div>
	<p><button type="submit">Connect to NetFlix</button></p>
</form>

<p><a href="<s:url value="/" />">Return to home page</a></p>

