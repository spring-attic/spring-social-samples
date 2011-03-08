<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Social Showcase: Connect to NetFlix</title>
</head>
<body>
<h1>Spring Social Showcase: Connect to NetFlix</h1>

<form action="<c:url value="/connect/netflix" />" method="POST">
	<div class="formInfo">
		<p>You haven't created any connections with NetFlix yet. Click the button to connect Spring Social Showcase with your NetFlix account. 
		(You'll be redirected to NetFlix where you'll be asked to authorize the connection.)</p>
	</div>
	<p><button type="submit">Connect to NetFlix</button></p>
</form>


</body>
</html>
