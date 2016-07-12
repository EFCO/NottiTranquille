<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="Login" scope="session"
             class="it.ispw.efco.nottitranquille.view.LoginBean"/>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <%@include file="header.html" %>

</head>
<body>

<%@include file="navbar.jsp" %>
<div class="alert alert-danger" role="alert" style="margin-top: 50px">
    <c:out value="${param['error']}"/>
    <a href="index.jsp" class="alert-link">Torna alla home page</a>
</div>

</body>
</html>