<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Social Showcase: NetFlix</title>
</head>
<body>
<h1>Spring Social Showcase: NetFlix</h1>

<p>Your NetFlix disc queue:</p>
<c:forEach items="${discQueue}" var="queueItem">
<li>${queueItem.title}</li>
</c:forEach>


<c:url value="/connect/netflix" var="disconnectUrl"/>
<form id="disconnect" action="${disconnectUrl}" method="post">
	<button type="submit">Disconnect from NetFlix</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

</body>
</html>
