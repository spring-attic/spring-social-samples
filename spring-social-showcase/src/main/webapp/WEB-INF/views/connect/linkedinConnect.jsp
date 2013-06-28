<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Connect to LinkedIn</h3>

<c:if test="${not empty social_authorization_error}">
	<div class="authorizationError">
		There was an error during authorization: <c:out value="${social_authorization_error['error']}" /> 
		<c:if test="${not empty social_authorization_error['errorDescription']}">
		(<c:out value="${social_authorization_error['errorDescription']}" />)
		</c:if>
		<c:if test="${not empty social_authorization_error['errorUri']}">
		Click <a href="<c:out value="${social_authorization_error['errorUri']}"/>">here</a> for more details.
		</c:if>
	</div>
</c:if>

<form action="<c:url value="/connect/linkedin" />" method="POST">
	<div class="formInfo">
		<p>
			You haven't created any connections with LinkedIn yet. Click the button to connect Spring Social Showcase with your LinkedIn account. 
			(You'll be redirected to LinkedIn where you'll be asked to authorize the connection.)
		</p>
	</div>

	<p><button type="submit">Connect with LinkedIn</button></p>
</form>
