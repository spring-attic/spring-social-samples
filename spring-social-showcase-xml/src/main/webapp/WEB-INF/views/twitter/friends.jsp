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
      
<h3>Your Twitter Friends</h3>

<ul class="imagedList">
<c:forEach items="${profiles}" var="profile">
	<li class="imagedItem">
		<div class="image">
		<c:if test="${not empty profile.profileImageUrl}"><img src="<c:out value="${profile.profileImageUrl}"/>" width="48" height="48" align="left"/></c:if>
		</div>
		<div class="content">
		<p><a href="http://twitter.com/<c:out value="${profile.screenName}" />"><c:out value="${profile.screenName}"/></a></p>
		</div>
	</li>
</c:forEach>
</ul>