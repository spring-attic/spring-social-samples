<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
      
<h3>Daily Twitter Trends</h3>

<c:forEach items="${trends.trends}" var="trend">
	<s:url value="/twitter/search" var="searchUrl">
		<s:param name="query"><c:out value="${trend.query}" escapeXml="false"/></s:param>
	</s:url>
	<a href="${searchUrl}"><c:out value="${trend.query}" /></a><br/>
</c:forEach>