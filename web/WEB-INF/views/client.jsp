<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--Перенесено из index.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true"%>
<!DOCTYPE HTML>
<html>
<head>
<title>CarWash</title>
</head>
<body>
	<%-- comment
<h1>Здравствуй, ${name}!</h1>--%>
	<form action="<c:url value="/client/add"/>" method="POST"
		enctype="text/plain">
		<label for="name">Имя</label> <input type="text" name="name" id="name"><br>
		<br> <label for="lastname">Фамилия</label> <input type="text"
			name="lastname" id="lastname"><br>
		<br> <label for="phone">Телефон</label> <input type="text"
			name="phone" id="phone"><br>
		<br>
		<button type="submit">Отправить</button>
		<br>
		<br>
	</form>
	<c:if test="${client!=null}">
		<table border="0">
			<tr>
				<td>Name</td>
				<td>${client.name}</td>
			</tr>
			<tr>
				<td>LastName</td>
				<td>${client.lastname}</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td>${client.phone}</td>
			</tr>
		</table>
	</c:if>
</body>
</html>