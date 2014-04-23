<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Spring Social Movies Sample</title>
		<link rel="stylesheet" href="<c:url value="/resources/page.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/form.css" />" type="text/css" media="screen" />
	</head>
	<body>
		<div id="header"">
			<h1>Spring Social Movies Sample</h1>
		</div>
		
		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>		
	</body>
</html>
