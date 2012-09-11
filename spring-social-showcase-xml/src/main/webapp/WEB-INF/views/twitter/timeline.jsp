<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<script src="http://platform.twitter.com/anywhere.js?id=7yWLgCOuQhIpPyffm0o2Vg&v=1" type="text/javascript"></script>
<script type="text/javascript">
  twttr.anywhere(function (T) {
    T(".feed").linkifyUsers();
  });    
</script>
      
<h3>Your Twitter <c:out value="${timelineName}"/> Timeline</h3>

<h4>Post a tweet</h4>
<c:url var="tweetUrl" value="/twitter/tweet" />
<form action="${tweetUrl}" method="post">
	<textarea name="message" rows="2" cols="80"></textarea><br/>
	<input type="submit" value="Post Tweet"/>
</form>

<c:url var="searchUrl" value="/twitter/search" />
<form action="${searchUrl}" method="get">
	<p><input type="text" name="query" value="<c:out value="${param.query}"/>" /> <input type="submit" value="Search"/></p>
</form>

<c:url var="timelineBaseUrl" value="/twitter/timeline" />
<ul class="choices">
<li><a href="<c:out value="${timelineBaseUrl}"/>/Home">Home Timeline</a></li>
<li><a href="<c:out value="${timelineBaseUrl}"/>/User">User Timeline</a></li>
<li><a href="<c:out value="${timelineBaseUrl}"/>/Public">Public Timeline</a></li>
<li><a href="<c:out value="${timelineBaseUrl}"/>/Mentions">Mentions</a></li>
<li><a href="<c:out value="${timelineBaseUrl}"/>/Favorites">Favorites</a></li>
</ul>

<div class="feed">
<ul class="imagedList">
<c:forEach items="${timeline}" var="tweet">
	<li class="imagedItem">
		<div class="image">
			<c:if test="${not empty tweet.profileImageUrl}"><img src="<c:out value="${tweet.profileImageUrl}"/>" align="left"/></c:if>
		</div>
		<div class="content">
		<strong><a href="http://twitter.com/<c:out value="${tweet.fromUser}" />"><c:out value="${tweet.fromUser}" /></a></strong><br/>
		<c:out value="${tweet.text}" /><br/>
		<span class="postTime"><c:out value="${tweet.createdAt}"/></span>
		</div>
	</li>
</c:forEach>
</ul>
</div>