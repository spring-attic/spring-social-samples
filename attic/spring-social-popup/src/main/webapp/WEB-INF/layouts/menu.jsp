<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/spring-social/social/tags" prefix="social" %>

<h4><a href="<c:url value="/connect"/>">Connections</a></h4>

<social:connected provider="twitter">
	<h4><a href="<c:url value="/twitter"/>">Twitter</a></h4>
</social:connected>

<social:connected provider="facebook">
	<h4><a href="<c:url value="/facebook"/>">Facebook</a></h4>
</social:connected>
