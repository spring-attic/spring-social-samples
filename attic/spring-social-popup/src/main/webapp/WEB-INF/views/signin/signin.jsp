<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<form id="signin" action="<c:url value="/signin/authenticate" />" method="post">
	<div class="formInfo">
  		<c:if test="${param.error eq 'bad_credentials'}">
  		<div class="error">
  			Your sign in information was incorrect.
  			Please try again or <a href="<c:url value="/signup" />">sign up</a>.
  		</div>
 	 	</c:if>
  		<c:if test="${param.error eq 'multiple_users'}">
  		<div class="error">
  			Multiple local accounts are connected to the provider account.
  			Try again with a different provider or with your username and password.
  		</div>
 	 	</c:if>
	</div>
	<fieldset>
		<label for="login">Username</label>
		<input id="login" name="j_username" type="text" size="25" <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if> />
		<label for="password">Password</label>
		<input id="password" name="j_password" type="password" size="25" />	
	</fieldset>
	<button type="submit">Sign In</button>
	
	<p>Some test user/password pairs you may use are:</p>
	<ul>
		<li>habuma/freebirds</li>
		<li>kdonald/melbourne</li>
		<li>rclarkson/atlanta</li>
	</ul>
	
	<p>Or you can <a href="<c:url value="/signup"/>">signup</a> with a new account.</p>
</form>
