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

<c:if test="${empty netflix_connected}">
	| <a href="connect/netflix">Connect to NetFlix</a>
	
	<p>You are not connected to NetFlix. Connect to see what movies are in your disc queue.</p>
</c:if>

<c:if test="${not empty netflix_connected}">
	<p>These movies are in your NetFlix Disc Queue:</p>
	<ul>
		<c:forEach items="${discQueue}" var="item">
		<li>${item.title} (${item.releaseYear})</li>
		</c:forEach>
	</ul>
</c:if>

</body>
</html>
