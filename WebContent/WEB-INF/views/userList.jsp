<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			var href = $(this).attr("href");
			$("form").attr("action", href).submit();			
			return false;
		});
	})
</script>
</head>
<body>
	<form action="" method="POST">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	
	<c:if test="${ empty userList }">
		it's none!
	</c:if>
	
	<c:if test="${ not empty userList }">
		<table>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>password</th>
			</tr>
			
			<c:forEach items="${ userList }" var="user">
				<tr>
					<td>${ user.id }</td>
					<td>${ user.name }</td> 
					<td>${ user.password }</td>
					<td><a href="${ pageContext.servletContext.contextPath }/user/${user.id}">update</a></td>
					<td><a href="${ pageContext.servletContext.contextPath }/user/${user.id}" class="delete">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<a href="${ pageContext.servletContext.contextPath }/user/inputUser">saveUser</a>
</body>
</html>