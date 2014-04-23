<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<p>Welcome, <c:out value="${account.firstName}"/>!</p>

<p>Manage your connections by clicking on the "Connections" link to the left.</p>

<a href="<c:url value="/signout" />">Sign Out</a>

