<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your Facebook Photo Album: <c:out value="${album.name}"/></h3>
	
<c:forEach items="${photos}" var="photo">
	<img src="${photo.albumImage.source}" align="middle"/>
</c:forEach>
