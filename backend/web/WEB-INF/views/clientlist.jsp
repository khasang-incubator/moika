<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Clients list</h3>
<ul>
    <c:forEach items="${clients}" var="client">
        <li>${client.id}: ${client.lastname} ${client.name}</li>
    </c:forEach>
</ul>
<div>Client: ${contClientId}</div>
</body>
</html>