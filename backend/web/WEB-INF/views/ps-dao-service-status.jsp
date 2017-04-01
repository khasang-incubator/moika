<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Service type status</title>
</head>
<body>
<p>Текущее время ${currentTime}</p>
<table>
    <tr >
        <td>ID статуса</td>
        <td>Символьный Код</td>
        <td>Наименование</td>
    </tr>
    <c:forEach items="${retList}" var="retList">
        <tr>
            <td><c:out value="${retList.id}"/></td>
            <td><c:out value="${retList.statusCode}"/></td>
            <td><c:out value="${retList.statusName}"/></td>

        </tr>
    </c:forEach>
</table>
<p>${nrows}</p>
</body>
</html>
