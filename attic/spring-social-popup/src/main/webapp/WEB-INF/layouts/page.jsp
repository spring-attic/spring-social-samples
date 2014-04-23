<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Spring Social Popup</title>
		<link rel="stylesheet" href="<c:url value="/resources/page.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/form.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/messages/messages.css" />" type="text/css" media="screen" />
	</head>
	<body>
		<script type="text/javascript" src="<c:url value="/resources/jquery/1.6.2/jquery.js" />"></script>
		
		<div id="header">
			<h1><a href="<c:url value="/"/>">Spring Social Popup</a></h1>
		</div>
		
		<div id="leftNav">
			<tiles:insertTemplate template="menu.jsp" />
		</div>
		
		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>
		
		<script>
		$(document).ready(function() {
			if(window.opener) {
				window.opener.location.reload(false);
				$('#leftNav').hide();
				$('#header').hide();
				$('#closer').show();
				$('#content').css('margin-left', '0px');
			}
		});
		</script>
	</body>
</html>
