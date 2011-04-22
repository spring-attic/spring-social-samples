<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>

<script src="http://platform.twitter.com/anywhere.js?id=7yWLgCOuQhIpPyffm0o2Vg&v=1" type="text/javascript"></script>
<script type="text/javascript">
  twttr.anywhere(function (T) {
    T(".feed").linkifyUsers();
  });    
</script>
      
<h3>Your Twitter <c:out value="${dmListType}" /> Messages</h3>

<c:url var="sendMessageUrl" value="/twitter/messages" />
<sf:form action="${sendMessageUrl}" method="post" modelAttribute="messageForm">
	<p>Send a message:</p>
	<sf:label path="to">To: </sf:label><sf:input path="to"/><br/>
	<sf:textarea path="text" rows="2" cols="80"/><br/>
	<input type="submit" value="Send Message"/>
</sf:form>

<c:url var="messageBaseUrl" value="/twitter/messages" />
<ul class="choices">
<li><a href="<c:out value="${messageBaseUrl}"/>">Inbox</a></li>
<li><a href="<c:out value="${messageBaseUrl}"/>/sent">Sent</a></li>
</ul>


<div class="feed">
<ul class="imagedList">
<c:forEach items="${directMessages}" var="dm">
	<li class="imagedItem">
		<div class="image">
			<c:if test="${not empty dm.sender.profileImageUrl}"><img src="<c:out value="${dm.sender.profileImageUrl}"/>" align="left"/></c:if>
		</div>
		<div class="content">
		<strong><a href="http://twitter.com/<c:out value="${dm.sender.screenName}" />"><c:out value="${dm.sender.screenName}" /></a></strong><br/>
		<span class="dmRecipient">to <c:out value="${dm.recipient.screenName}"/></span><br/>
		<c:out value="${dm.text}" /><br/>
		<span class="postTime"><c:out value="${dm.createdAt}"/></span>
		</div>
	</li>
</c:forEach>
</ul>
</div>