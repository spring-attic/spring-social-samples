<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Social Showcase: Send a Tweet</title>
	<link rel="stylesheet" href="<c:url value="/resources/form.css" />" type="text/css" media="screen" />
</head>
<body>
<h1>Spring Social Showcase: Send a Tweet</h1>

<c:if test="${_duplicateConnectionException}">
   <p class="error">That connection already exists.</p>
</c:if>


<p>Your Spring Social Showcase account is connected to the following Twitter users:</p>

<c:url value="/connect/twitter" var="disconnectUrl"/>
<c:forEach items="${connections}" var="connection">
<div>
	<a href="${connection.profileUrl}" target="_blank"><img src="${connection.imageUrl}" border="0"/></a>
	<a href="${connection.profileUrl}"><c:out value="${connection.displayName}"/></a>
	<form id="disconnect${connection.key.providerUserId}" action="${disconnectUrl}/${connection.key.providerUserId}" method="post">
		<button type="submit">Disconnect</button>	
		<input type="hidden" name="_method" value="delete" />
	</form>
</div>
</c:forEach>

<form id="disconnect" action="${disconnectUrl}" method="post">
	<button type="submit">Disconnect from all</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<form action="<c:url value="/connect/twitter" />" method="POST">
	<p>You may connect multiple Twitter users to a single Spring Social Showcase account. To connect to another Twitter user, click the button.</p>
	<button type="submit">Connect to another Twitter user</button> <label for="postTweet"><input id="postTweet" type="checkbox" name="postTweet" /> Post a tweet about connecting with Spring Social Showcase</label>
	<p>(Note: If you are still logged into Twitter as any one of the profiles that are already connected, you'll need to
	first sign out when Twitter prompts you to allow access to Spring Social Showcase and then login as a
	different Twitter user.)</p>
</form>

<h3>Post a Tweet to your connected Twitter accounts</h3>
<c:url var="tweetUrl" value="/twitter/tweet" />
<form action="${tweetUrl}" method="post">
	<textarea name="message" rows="5" cols="80"></textarea> 	
	<p><input type="submit" value="Tweet" /></p>
</form>

</body>
</html>