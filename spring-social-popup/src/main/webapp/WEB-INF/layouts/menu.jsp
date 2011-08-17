<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h4><a href="<c:url value="/connect"/>">Connections</a></h4>

<c:if test="${connectedToTwitter}">
	<h4><a href="<c:url value="/twitter"/>">Twitter</a></h4>
</c:if>

<c:if test="${connectedToFacebook}">
	<h4><a href="<c:url value="/facebook"/>">Facebook</a></h4>
</c:if>
