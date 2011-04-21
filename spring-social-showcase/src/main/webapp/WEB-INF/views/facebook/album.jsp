<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your Facebook Photo Album: <c:out value="${album.name}"/></h3>
	
<ul class="photos">
<c:forEach items="${photos}" var="photo">
	<li><img src="${photo.albumImage.source}"/></li>
</c:forEach>
</ul>
