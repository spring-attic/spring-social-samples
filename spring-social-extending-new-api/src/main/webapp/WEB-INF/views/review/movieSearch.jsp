<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Pick a Movie to review</h3>

<form method="GET">
	<input type="text" name="searchTerm" value="${param.searchTerm}"/>
	<input type="submit" value="Search" />
</form>

<c:if test="${not empty titles}">
<form method="GET">
	<p>Select a movie:</p>
	<c:forEach items="${titles}" var="title">
		<p><input class="movieChoice" type="radio" name="title" value="${title.title} (${title.releaseYear})|${title.id}">${title.title} (${title.releaseYear})</input></p>
	</c:forEach>
	<input type="submit" value="Next" />
</form>
</c:if>

</body>
</html>
