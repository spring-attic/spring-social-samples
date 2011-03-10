<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h3>Connected to NetFlix</h3>

<form id="disconnect" method="post">
	<div class="formInfo">
		<p>The Spring Social NetFlix Sample application is already connected to your NetFlix account.
			Click the button if you wish to disconnect.			
	</div>

	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<p><a href="<s:url value="/" />">Return to home page</a></p>

</body>
</html>
