<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Social Showcase: LinkedIn</title>
	<style type="text/css">
		.evenrow {background-color:white;}
		.oddrow {background-color:lightgrey;}
	</style>
</head>
<body>
	<h1>Spring Social Showcase: LinkedIn</h1>
	<p>Hello, <c:out value="${linkedInApi.userProfile.firstName}"/> &nbsp; <c:out value="${linkedInApi.userProfile.lastName}"/>!  (<a href="<c:url value="/connect/linkedin"/>">Disconnect from Facebook</a>)</p>
	<p>Your LinkedIn profile:</p>
	<dl>
		<dt>LinkedIn ID:</dt>
		<dd><a href="${linkedInApi.profileUrl}" target="_blank"><c:out value="${linkedInApi.userProfile.id}"/></a></dd>
		<dt>Industry:</dt>
		<dd><c:out value="${linkedInApi.userProfile.industry}"/></dd>
		<dt>Headline:</dt>
		<dd><c:out value="${linkedInApi.userProfile.headline}"/></dd>
		<dt id="friendsIds">Connections:</dt>
		<dd><ol>
		<c:forEach items="${linkedInApi.connections}" var="connection" varStatus="status">
			<c:choose>
				<c:when test="${status.count%2 == 0}"><c:set var="altrow" value="evenrow"/></c:when>
				<c:otherwise><c:set var="altrow" value="oddrow"/></c:otherwise>
			</c:choose>
			<li class="${altrow}"><a href="${connection.standardProfileUrl}" target="_blank"><c:out value="${connection.firstName}"/>&nbsp;
			<c:out value="${connection.lastName}"/></a>&nbsp;<sub><c:out value="${connection.headline}"/></sub>&nbsp;@&nbsp;<c:out value="${connection.industry}"/></li>
		</c:forEach>
		</ol></dd>
	</dl>

	<p><a href="<s:url value="/" />">Return to home page</a></p>
</body>
</html>
