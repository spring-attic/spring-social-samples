<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h4><a href="<c:url value="/connect"/>">Connections</a></h4>

<h4><a href="<c:url value="/twitter"/>">Twitter</a></h4>
<c:if test="${connectedToTwitter}">
<ul class="menu">
	<li><a href="<c:url value="/twitter"/>">User Profile</a></li>
	<li><a href="<c:url value="/twitter/timeline"/>">Timeline</a></li>
	<li><a href="<c:url value="/twitter/friends"/>">Friends</a></li>
	<li><a href="<c:url value="/twitter/followers"/>">Followers</a></li>
	<li><a href="<c:url value="/twitter/messages"/>">Messages</a></li>
	<li><a href="<c:url value="/twitter/trends/daily"/>">Daily Trends</a></li>
</ul>
</c:if>

<h4><a href="<c:url value="/facebook"/>">Facebook</a></h4>
<c:if test="${connectedToFacebook}">
<ul class="menu">
	<li><a href="<c:url value="/facebook"/>">User Profile</a></li>
	<li><a href="<c:url value="/facebook/feed"/>">Feed</a></li>
	<li><a href="<c:url value="/facebook/friends"/>">Friends</a></li>
	<li><a href="<c:url value="/facebook/albums"/>">Albums</a></li>
</ul>
</c:if>
