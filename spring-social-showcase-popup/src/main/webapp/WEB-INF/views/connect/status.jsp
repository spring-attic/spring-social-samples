<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

<h3>Your Connections</h3>

<c:forEach var="providerId" items="${providerIds}">
	<c:set var="connections" value="${connectionMap[providerId]}" />
	<s:message code="${providerId}.displayName" var="providerDisplayName" />
	<div class="accountConnection">
		<s:message code="${providerId}.icon" var="iconUrl"/>
		<h4><img src="<c:url value="${iconUrl}" />" width="36" height="36" />${providerDisplayName}</h4>
		
		<p>
		<c:if test="${not empty connections}">
			You are connected to ${providerDisplayName} as ${connections[0].displayName}.
		</c:if>
		<c:if test="${empty connections}">
			You are not yet connected to ${providerDisplayName}.
		</c:if>
		Click <a href="<c:url value="/connect/${providerId}" />" class="connectLink">here</a> to manage your ${providerDisplayName} connection.
		</p>
	</div>
</c:forEach>

<script>
$(document).ready(function() {
	$(".connectLink").click(function(event){
		event.preventDefault();
		// TODO: Determine the best width or resize after load to fit content
		// TODO: HootSuite loads up an initial "Please wait" page while the provider auth page loads
		var cTP = $(event.currentTarget)[0];		
		window.open(cTP.href, "connectWindow", "width=1000,height=575");
	});
});
</script>
