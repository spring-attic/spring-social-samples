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
			<h3>Your Facebook Feed</h3>
				
			<form method="POST" th:action="@{/facebook/feed}">
				<input type="hidden" name="_csrf" th:value="${_csrf.token}" />
				<p>Post to your Facebook wall:</p>
				<textarea id="message" name="message" rows="2" cols="60"></textarea><br/>
				<input type="submit" value="Post" />
			</form>
			
			<div class="feed">
			<ul class="feedList">
				<li class="post" th:each="post : ${feed}">
					<p>
						<img th:if="${!#strings.isEmpty(post.picture)}" th:src="${post.picture}" align="top"/>
						<span th:text="${post.message}">message</span> - <span th:text="${post.name}">name</span>
					</p>
				</li>
			</ul>
			</div>
		</div>
	</body>
</html>
