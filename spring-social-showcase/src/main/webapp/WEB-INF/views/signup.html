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
			<h3>Sign Up</h3>
			
			<div th:if="${!#strings.isEmpty(message)}"
				 th:class="${message.type.cssClass}" 
				 th:text="${message.text}">error message text</div>
			
			<form id="signup" th:action="@{/signup}" method="post" th:object="${signupForm}">
				<input type="hidden" name="_csrf" th:value="${_csrf.token}" />
				<div class="formInfo">
					<div class="error" th:if="${#fields.hasErrors('*')}">Unable to sign up. Please fix the errors below and resubmit.</div>
				</div>
				
				<fieldset>
					<label for="firstName">First Name <span th:if="${#fields.hasErrors('firstName')}" class="error" th:text="${#fields.errors('firstName')[0]}">Error</span></label>
					<input type="text" th:field="*{firstName}" />
					<label for="lastName">Last Name <span th:if="${#fields.hasErrors('lastName')}" class="error" th:text="${#fields.errors('lastName')[0]}">Error</span></label>
					<input type="text" th:field="*{lastName}" />
					<label for="username">Username <span th:if="${#fields.hasErrors('username')}" class="error" th:text="${#fields.errors('username')[0]}">Error</span></label>
					<input type="text" th:field="*{username}" />
					<label for="password">Password (at least 6 characters) <span th:if="${#fields.hasErrors('password')}" class="error" th:text="${#fields.errors('password')[0]}">Error</span></label>
					<input type="password" th:field="*{password}" />
				</fieldset>
				<p><button type="submit">Sign Up</button></p>
			</form>
		</div>
	</body>
</html>

