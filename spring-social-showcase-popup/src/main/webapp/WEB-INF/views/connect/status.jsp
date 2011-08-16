<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>

<script>
if(window.opener) {
	window.opener.location.reload(false);
	window.close();
}
</script>

<h3>Your Connections</h3>

<c:forEach var="providerId" items="${providerIds}">
	<c:set var="connections" value="${connectionMap[providerId]}" />
	<s:message code="${providerId}.displayName" var="providerDisplayName" />
	
	<div class="accountConnection">
		<s:message code="${providerId}.icon" var="iconUrl"/>
		<s:message code="${providerId}.button" var="buttonUrl" />
		<h4><img src="<c:url value="${iconUrl}" />" width="36" height="36" />${providerDisplayName}</h4>
		<c:url value="/connect/${providerId}" var="connectUrl" />
				
		<c:if test="${empty connections}">
		<form action="${connectUrl}" method="POST" class="connectForm">
			<div class="formInfo">
				You are not yet connected to ${providerDisplayName}.
			</div>
			<button class="connectButton" type="submit"><img src="<c:url value="${buttonUrl}" />"/></button>
			<tiles:insertTemplate template="${providerId}Options.jsp" />
		</form>
		</c:if>
		
		<c:if test="${not empty connections}">
		<form id="${providerId}Disconnect" method="post" action="${connectUrl}">
			<p>You are connected to <s:message code="${providerId}.displayName"/> 
		   	as <a href="${connectionMap[providerId][0].profileUrl}">${connectionMap[providerId][0].displayName}</a>.</p>
			<button class="disconnectButton" type="submit">Disconnect</button>	
			<input type="hidden" name="_method" value="delete" />
		</form>
		</c:if>
	</div>
</c:forEach>

<script>
$(document).ready(function() {
	$(".connectButton").click(function(event){
		event.preventDefault();
		window.open("", "connectWindow", "width=600,height=400");
		var cTP = $(event.currentTarget).parent();		
		cTP[0].setAttribute("target", "connectWindow");
		cTP[0].submit();
	});
});
</script>
