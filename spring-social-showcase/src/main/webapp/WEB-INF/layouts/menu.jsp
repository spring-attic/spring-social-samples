<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h4><a href="<c:url value="/twitter"/>">Twitter</a></h4>
<%-- TODO: Keep provider-menu items from appearing unless the user is connected to that provider --%>
<ul class="menu">
	<li><a href="<c:url value="/twitter"/>">User Profile</a></li>
	<li><a href="<c:url value="/twitter/timeline"/>">Timeline</a></li>
</ul>

<h4><a href="<c:url value="/facebook"/>">Facebook</a></h4>
<%-- TODO: Keep provider-menu items from appearing unless the user is connected to that provider --%>
<ul class="menu">
	<li><a href="<c:url value="/facebook"/>">User Profile</a></li>
	<li><a href="<c:url value="/facebook/feed"/>">Feed</a></li>
	<li><a href="<c:url value="/facebook/friends"/>">Friends</a></li>
	<li><a href="<c:url value="/facebook/albums"/>">Albums</a></li>
</ul>

<h4><a href="<c:url value="/tripit"/>">TripIt</a></h4>
