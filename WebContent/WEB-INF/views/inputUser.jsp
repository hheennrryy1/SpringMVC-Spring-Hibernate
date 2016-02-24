<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<title>List</title>
</head>
<body>
  	<!-- 
  		进入页面之前域里必须有一个user对象!
  		每一个path必须是该对象的属性! 
  	-->
  	<form:form action="${ pageContext.servletContext.contextPath }/user" method="POST" modelAttribute="user">
	  	<div class="form-group">
	  		<form:label path="name">
	  			name<form:input path="name" cssClass="form-control"/>
	  				<form:errors path="name"></form:errors>
	  		</form:label>
	  	</div>
  		
  		<!-- 如果域里没有id,则是save,有password,发送POST请求 -->
  		<c:if test="${ empty user.id }">
  			<div class="form-group">
	  			<form:label path="password">
	  				password<form:password path="password" cssClass="form-control"/>
	  						<form:errors path="password"></form:errors>
	  			</form:label>
  			</div>
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