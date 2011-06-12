<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Connect to Facebook</title>
	</head>
	<body>
		<form action="<c:url value="/connect/facebook" />" method="POST">
		    <input type="hidden" name="scope" value="email,publish_stream,offline_access" />
		    <button type="submit">Connect</button>
		</form>	
	</body>
</html>