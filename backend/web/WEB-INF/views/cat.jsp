<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Cats list</h3>
<ul>
    <c:forEach items="${cats}" var="cat">
        <li>${cat.id}: ${cat.additionalInfo} ${cat.description}</li>
    </c:forEach>
</ul>
</body>
</html>