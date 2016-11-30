<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	  xmlns:social="http://spring.io/springsocial"
	  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	  layout:decorator="layout">
	<head>
		<title>Spring Social Showcase</title>
		<link rel="stylesheet" th:href="@{/resources/page.css}" type="text/css" media="screen"></link>
		<link rel="stylesheet" th:href="@{/resources/form.css}" type="text/css" media="screen"></link>
		<link rel="stylesheet" th:href="@{/resources/messages/messages.css}" type="text/css" media="screen"></link>
	</head>
	<body>
		<div id="header">
			<h1><a th:href="@{/}">Spring Social Showcase</a></h1>
		</div>
		
		<div id="leftNav">
			Left nav menu
		</div>
		
		<div id="content" layout:fragment="content">
			<form id="signin" th:action="@{/signin/authenticate}" method="post">
				<input type="hidden" name="_csrf" th:value="${_csrf.token}"></input>
				<div class="formInfo">
			  		<div class="error" th:if="${param.error eq 'bad_credentials'}">
			  			Your sign in information was incorrect.
			  			Please try again or <a th:href="@{/signup}">sign up</a>.
			  		</div>
			  		<div class="error" th:if="${param.error eq 'multiple_users'}">
			  			Multiple local accounts are connected to the provider account.
			  			Try again with a different provider or with your username and password.
			  		</div>
				</div>
				<fieldset>
					<label for="login">Username</label>
					<input id="login" name="username" type="text" size="25"></input>
					<label for="password">Password</label>
					<input id="password" name="password" type="password" size="25"></input>
				</fieldset>
				<button type="submit">Sign In</button>
				
				<p>Some test user/password pairs you may use are:</p>
				<ul>
					<li>habuma/freebirds</li>
					<li>kdonald/melbourne</li>
					<li>rclarkson/atlanta</li>
				</ul>
				
				<p>Or you can <a th:href="@{/signup}">signup</a> with a new account.</p>
			</form>

			<!-- TWITTER SIGNIN -->
			<form id="tw_signin" th:action="@{/signin/twitter}" method="POST">
				<input type="hidden" name="_csrf" th:value="${_csrf.token}"></input>
				<button type="submit"><img th:src="@{/resources/social/twitter/sign-in-with-twitter-d.png}"></img></button>
			</form>
		
			<!-- FACEBOOK SIGNIN -->
			<form name="fb_signin" id="fb_signin" th:action="@{/signin/facebook}" method="POST">
				<input type="hidden" name="_csrf" th:value="${_csrf.token}"></input>
				<input type="hidden" name="scope" value="read_stream,user_posts,user_photos"></input>
				<button type="submit"><img th:src="@{/resources/social/facebook/sign-in-with-facebook.png}"></img></button>
			</form>
		
			<!-- LINKEDIN SIGNIN -->
			<form name="li_signin" id="li_signin" th:action="@{/signin/linkedin}" method="POST">
				<input type="hidden" name="_csrf" th:value="${_csrf.token}"></input>
				<button type="submit">Sign In with LinkedIn</button>
			</form>
		</div>		
	</body>
</html>
