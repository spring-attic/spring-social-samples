<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

<h3>Connected to LinkedIn</h3>

<form id="disconnect" method="post">
	<div class="formInfo">
		<p>
			Spring Social Showcase is connected to your LinkedIn account.
			Click the button if you wish to disconnect.
		</p>		
	</div>
	
	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<p><a href="<c:url value="/linkedin" />">View your LinkedIn profile</a></p>