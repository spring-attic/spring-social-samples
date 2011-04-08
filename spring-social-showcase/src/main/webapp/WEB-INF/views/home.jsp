<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Social Showcase</title>
</head>
<body>
<h1>Spring Social Showcase</h1>

<p>Welcome, <c:out value="${account.firstName}"/>!</p>

<a href="<c:url value="/signout" />">Sign Out</a>

</body>
</html>
