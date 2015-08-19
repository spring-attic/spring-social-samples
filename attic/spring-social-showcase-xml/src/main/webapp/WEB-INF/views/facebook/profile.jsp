<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your Facebook Profile</h3>
<p>Hello, <c:out value="${profile.firstName}"/>!</p>
<dl>
	<dt>Facebook ID:</dt>
	<dd><c:out value="${profile.id}"/></dd>
	<dt>Name:</dt>
	<dd><c:out value="${profile.name}"/></dd>
	<dt>Email:</dt>
	<dd><c:out value="${email}"/></dd>
</dl>

<c:url value="/connect/facebook" var="disconnectUrl"/>
<sf:form id="disconnect" action="${disconnectUrl}" method="post">
	<button type="submit">Disconnect from Facebook</button>	
	<input type="hidden" name="_method" value="delete" />
</sf:form>
