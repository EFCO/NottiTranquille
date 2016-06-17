<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <%@include file="header.html" %>

</head>
<body>
<%@include file="navbar.html"%>
<div class="alert alert-danger" role="alert" style="padding-top: 50px">
    <c:out value="${param['error']}"></c:out>
    <a href="index.jsp" class="alert-link">Torna alla home page</a>
</div>

</body>
</html>