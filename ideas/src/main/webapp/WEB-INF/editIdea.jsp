<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Edit ${idea.name}</h1>
	
	<form:form method="post" action="/ideas/${idea.id}/edit" modelAttribute="idea">
		<form:label path="name"> Name:
			<form:errors path="name"></form:errors>
			<form:input path="name"></form:input>
		</form:label><br>
		
		<form:label path="creator"> Creator:
			<form:errors path="creator"></form:errors>
			<form:input path="creator"></form:input>
		</form:label><br>
		
		
		<input type="submit" value="Update"/><br><br>
	</form:form><br>
	
	<a href ="/">Back to Ideas</a>
</body>
</html>