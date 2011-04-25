<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your LinkedIn Connections</h3>
	
<ul class="friends">
<c:forEach items="${connections}" var="connection" varStatus="status">
	<c:choose>
		<c:when test="${status.count%2 == 0}"><c:set var="altrow" value="evenrow"/></c:when>
		<c:otherwise><c:set var="altrow" value="oddrow"/></c:otherwise>
	</c:choose>
	<li class="${altrow}"><a href="${connection.standardProfileUrl}" target="_blank"><c:out value="${connection.firstName}"/>&nbsp;
			<c:out value="${connection.lastName}"/></a>&nbsp;<sub><c:out value="${connection.headline}"/></sub>&nbsp;@&nbsp;<c:out value="${connection.industry}"/></li>
</c:forEach>
</ul>
