<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="userBean" scope="session" class="it.ispw.efco.nottitranquille.view.UserBean"/>

<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <%@include file="header.html" %>

</head>
<body>
<%@include file="navbar.html"%>

<div class="alert alert-danger" role="alert" style="padding-top: 50px">
    Sembra che tu sia già loggato su un altro dispositivo
    <a href="/" class="alert-link">Torna alla home page</a>
</div>

</body>
</html>