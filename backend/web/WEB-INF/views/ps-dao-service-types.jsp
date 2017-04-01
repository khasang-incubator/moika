<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Service type entity</title>
</head>
<body>
<p>Текущее время ${currentTime}</p>
<table>
    <tr >
        <td>ID типа</td>
        <td>Символьный Код</td>
        <td>Наименование</td>
        <td>Описание</td>
    </tr>
    <c:forEach items="${retList}" var="retList">
        <tr>
            <td><c:out value="${retList.id}"/></td>
            <td><c:out value="${retList.typeCode}"/></td>
            <td><c:out value="${retList.typeName}"/></td>
            <td><c:out value="${retList.description}"/></td>
        </tr>
    </c:forEach>
</table>
<p>${nrows}</p>
</body>
</html>
