<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
      
<h3>Lists</h3>

<div class="feed">
<ul class="imagedList">
<c:forEach items="${lists}" var="list">
	<li>
	<span class="postTime"><c:out value="${list.fullName}"/></span>
	<div class="content">
	<a href="https://twitter.com<c:out value="${list.uriPath}"/>">
	<c:out value="${list.name}"/></a><br />
	<c:if test="${! empty list.description}">
		<c:out value="${list.description}"/><br />
	</c:if>
	<span class="postTime"><c:out value="${list.memberCount}"/> Member, 
	<c:out value="${list.subscriberCount}"/> Subscriber</span>
	</div>
	</li>
</c:forEach>
</ul>
</div>