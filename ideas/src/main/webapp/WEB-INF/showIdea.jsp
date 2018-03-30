<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Idea</title>
</head>
<body>

	<h2>${idea.name}</h2>
	<p>Created By: ${idea.creator}</p>
	
	<h1>Users who liked your idea</h1>
	
	<table border=3px, width=5px>
    		<tr>
    			<th>Name</th>
    			<th>Date and Time Liked</th>
    		</tr>
    		<c:forEach items="${idea.users}" var="user">
    		<tr>
    			<td>${user.firstName}</td>
    			<td>${user.createdAt}</td>
    		</tr>
    		</c:forEach>
    </table><br><br>
    
    <a href="/ideas/${id}/edit">Edit</a><br><br>
   
    <a href="/">Back to Ideas</a>
</body>
</html>