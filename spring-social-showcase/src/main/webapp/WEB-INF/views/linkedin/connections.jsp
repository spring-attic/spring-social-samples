<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your LinkedIn Connections</h3>

<p>First degree count: <c:out value="${firstDegreeCount}"/></p>
<p>Second degree count: <c:out value="${secondDegreeCount}"/></p>

<ul class="friends">
<c:forEach items="${connections}" var="connection">
	<li><img src="${connection.profilePictureUrl}" align="middle"/><c:out value="${connection.firstName}"/> <c:out value="${connection.lastName}"/></li>
</c:forEach>
</ul>
