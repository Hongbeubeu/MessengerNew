<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="chat" items="${chatsentence}">
<h1>${user.id }</h1>
<c:if test="${chat.userId == user.id }">
	<h1>${chat.context }</h1>
	<h1>${chat.userId }</h1>
	<h1>${chat.avatar }</h1>
	<h1>${chat.imageUrl }</h1>
	<h1>${chat.createAt }</h1>
	</c:if>
</c:forEach>

</body>
</html>