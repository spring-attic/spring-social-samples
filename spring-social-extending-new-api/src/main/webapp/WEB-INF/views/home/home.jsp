<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<p>Welcome, <c:out value="${account.firstName}"/>! (<a href="<c:url value="/signout" />">Sign Out</a>)</p>

<div id="discQueue">
	<h3>In your Netflix queue</h3>
	<c:if test="${empty netflix_connected}">
		<p>You are not connected to NetFlix.<br/><a href="connect/netflix">Connect</a> to see what movies are in your disc queue and write reviews.</p>
	</c:if>
	
	<c:if test="${not empty netflix_connected}">
		<p>These movies are in your NetFlix Disc Queue:<br/>(<a href="connect/netflix">Disconnect from NetFlix</a>)</p>
		<c:forEach items="${discQueue}" var="item">
			<div class="queueItem">
			<p>
			<img src="${item.boxArtUrl}" align="middle"/>${item.title} (${item.releaseYear})
			</p>
			<s:eval expression="queueReviews.?[netflixId == '${item.id}']" var="queueItemReviews" />
			<c:forEach items="${queueItemReviews}" var="queueItemReview">
				<div class="queueReview"><b>${queueItemReview.author}</b> says "${queueItemReview.text}"</div>
			</c:forEach>
			</div>
		</c:forEach>
	</c:if>
</div>

<div id="recentReviews">
	<h3>Recent reviews</h3>
	<c:if test="${not empty netflix_connected}">
		<p><a href="reviews/new">Write a review</a></p>
	</c:if>
	<c:if test="${not empty recentReviews}">
		<c:forEach items="${recentReviews}" var="review">
			<p><span class="queueItem"><b>${review.movieTitle}</b></span><br/>
			<span class="queueReview"><b>${review.author}</b> says "${review.text}"</span></p>
		</c:forEach>
	</c:if>
</div>
