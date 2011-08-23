<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>

<p>Welcome, <c:out value="${userName}"/>!</p>

<p><a href="<c:url value='/facebook'/>">View your Facebook profile</a></p>
