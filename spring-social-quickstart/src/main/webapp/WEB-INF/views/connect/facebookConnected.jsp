<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Facebook Connections</title>
	</head>
	<body>
		<form action="<c:url value="/connect/facebook" />" method="post">
		    <button type="submit">Disconnect</button>	
		    <input type="hidden" name="_method" value="DELETE" />
		</form>
	</body>
</html>