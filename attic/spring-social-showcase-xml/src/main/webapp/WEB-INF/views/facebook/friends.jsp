<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your Facebook Friends</h3>
	
<ul class="friends">
<c:forEach items="${friends}" var="friend">
	<li><img src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture" align="middle"/><c:out value="${friend.name}"/></li>
</c:forEach>
</ul>
