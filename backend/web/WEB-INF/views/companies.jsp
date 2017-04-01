<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr >
        <td>ID</td>
        <td>Name</td>
    </tr>
    <c:forEach items="${companies}" var="company">
        <tr>
            <td><c:out value="${company.id}"/></td>
            <td><c:out value="${company.name}"/></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
