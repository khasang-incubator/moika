<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Test for test entity</title>
</head>
<body>
	<p>Текущее время ${currentTime}</p>
	<p>CRUD type: ${CrudType}</p>
	<table>
		<tr>
			<td>ID</td>
			<td>Name1</td>
			<td>Name2</td>
		</tr>
		<c:forEach items="${testlist}" var="testlist">
			<tr>
				<td><c:out value="${testlist.id}" /></td>
				<td><c:out value="${testlist.name1}" /></td>
				<td><c:out value="${testlist.name2}" /></td>
			</tr>
		</c:forEach>
	</table>
	<p>${nrows}</p>
</body>
</html>
