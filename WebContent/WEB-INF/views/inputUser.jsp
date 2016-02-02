<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>input</title>
</head>
<body>
	<!-- 
		进入页面之前域里必须有一个user对象!
		每一个path必须是该对象的属性! 
	-->
	<form:form modelAttribute="user" action="${ pageContext.servletContext.contextPath }/user" method="POST">
		<form:label path="name">
			name<form:input path="name"/>
				<form:errors path="name"></form:errors>
		</form:label>
		<br />
		
		<!-- 如果域里没有id,则是save,有password,发送POST请求 -->
		<c:if test="${ empty user.id }">
			<form:label path="password">
				password<form:password path="password"/>
						<form:errors path="password"></form:errors>
			</form:label>
		</c:if>
		
		<!-- 
			如果域里有id,则是update,没有password,发送PUT请求.
			此时bean中没有password,因此Controller要使用ModelAttribute
		-->
		<c:if test="${ not empty user.id }">
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		<br />
		<input type="submit" value="submit"/>
	</form:form>
</body>
</html>