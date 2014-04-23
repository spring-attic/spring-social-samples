<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your Facebook Photo Albums</h3>
	
<ul class="albums">
<c:forEach items="${albums}" var="album">
	<li><a href="<c:url value="/facebook/album/${album.id}"/>"><c:out value="${album.name}"/></a></li>
</c:forEach>
</ul>
