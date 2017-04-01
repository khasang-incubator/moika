<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Car service entitiess</title>
</head>
<body>
<p>Текущее время ${currentTime}</p>
<table>
    <tr >
        <td>ID сервиса</td>
        <td>Наименование мойки</td>
        <td>Наименование сервиса</td>
        <td>Тип сервиса</td>
        <td>Статус сервиса</td>
        <td>Описание</td>
    </tr>
    <c:forEach items="${servicelist}" var="servicelist">
        <tr>
            <td><c:out value="${servicelist.id}"/></td>
            <td><c:out value="${servicelist.washFacility.name}"/></td>
            <td><c:out value="${servicelist.serviceName}"/></td>
            <td><c:out value="${servicelist.serviceTypeEntity.typeName}"/></td>
            <td><c:out value="${servicelist.serviceStatusEntity.statusName}"/></td>
            <td><c:out value="${servicelist.description}"/></td
        </tr>
    </c:forEach>
</table>
<p>${nrows}</p>
</body>
</html>
