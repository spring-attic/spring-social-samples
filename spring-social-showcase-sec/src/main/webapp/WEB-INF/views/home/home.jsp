<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<p>Welcome, <c:out value="${account.firstName}"/>!</p>

<form method="POST" action="<c:url value="/signout" />">
	<input type="hidden" name="_csrf" value="<c:out value="${_csrf.token}" />" />
	<button>Sign Out</button>
</form>
