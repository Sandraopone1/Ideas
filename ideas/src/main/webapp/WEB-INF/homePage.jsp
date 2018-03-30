<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
    <h1>Welcome <c:out value="${currentUser.firstName}"></c:out>!</h1>
    
    <h2>Courses:</h2>
    
    <table border=1px>
    
    		<tr>
    			<th>Ideas</th>
    			<th>Created By:</th>
    			<th>likes</th>
    			<th>Action</th>
    			<th>Action</th>
    		</tr>
    		
    		<c:forEach items="${allIdeas}" var="idea">
    		
    		<tr>
    			<td><a href="/ideas/${idea.id}">${idea.name}</a></td>
    			<td>${idea.creator}</td>
    			<td>${idea.likes} </td>
    			<c:set var = "hasStudent" value= "${false}"/>
    			
    			<c:forEach items="${idea.getUsers()}" var="user">
    				<c:if test="${user.firstName == currentUser.firstName}">
    					<td>Already Liked</td>
    					<c:set var = "hasStudent" value= "${true}"/>
    				</c:if>
    			</c:forEach>
    			
  			<c:if test="${hasStudent == false}">
  				<c:if test="${idea.likes != idea.studentLimit}">
  					<td><a href="/ideas/${idea.id}/like">Like</a></td>
  				</c:if>
  				<c:if test="${idea.likes == idea.studentLimit}">
   					<td>Full</td>
    				</c:if>
    				<c:set var = "hasStudent" value= "${false}"/>
    			</c:if>
    				<td><a href="/ideas/${idea.id}/quit">Unlike</a></td>
    			
    		</c:forEach>
    </table><br><br>
    
    <a href = "/ideas/new">Create an Idea</a><br><br>
    
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    
</body>
</html>
