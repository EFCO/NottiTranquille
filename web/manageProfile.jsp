<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 29/06/2016
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ elvariable id="location" type="it.ispw.efco.nottitranquille.model.Location.java"--%>
<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<html>
<head>

    <%@include file="header.html" %>
    <title>Profilo personale</title>

</head>

<body>

<%@include file="navbar.html" %>

<div class="container" style="margin-top: 50px">
    <h4>GESTIONE PROFILO</h4>

    <%
        if (request.getParameter("modify") == null) {
    %>
    <form action="manageProfile.jsp" method="POST">
        <div class="row">
            <c:out value="${loginBean.user.firstName}"/>
            <button type="submit" class="btn btn-default" name="modify" value="firstName">Modifica</button>
        </div>
        <div class="row">
            <c:out value="${loginBean.user.lastName}"/>
            <button type="submit" class="btn btn-default" name="modify" value="lastName">Modifica</button>
        </div>
        <div class="row">
            <c:out value="${loginBean.user.email}"/>
            <button type="submit" class="btn btn-default" name="modify" value="email">Modifica</button>
        </div>
        <button type="submit" class="btn btn-default" name="modify" value="password">Modifica Password</button>
        <div class="row">
            <div class="col-md-2">
                <c:out value="${loginBean.user.address.address}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${loginBean.user.address.city}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${loginBean.user.address.nation}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${loginBean.user.address.postalcode}"/>
            </div>
            <button type="submit" class="btn btn-default" name="modify" value="address">Modifica</button>
        </div>
    </form>
</div>
<%
} else {
%>
<form action="manageProfile.jsp" method="POST">
    <div class="form-group row">
        <div class="col-md-4">
            <label for="value">Modifica <c:out value="${param['modify'].toUpperCase()}"/> </label>
            <input name="value" id="value" type="text" class="form-control"/>
            <button type="submit" class="btn btn-default" name="modify" value="${param['modify']}">Modifica</button>
        </div>
    </div>
</form>

<%
    }
    if (request.getParameter("modify") != null && request.getParameter("value") != null) {
        if (loginBean.modifyField(request.getParameter("modify"), request.getParameter("value")) == 1) {
%>
<div class="alert alert-success">Campo modificato correttamente! <a href="manageProfile.jsp" class="alert-link">Torna al pannello</a> </div>
<%
        }
    }
%>
<%@include file="bootstrap_core_js.html" %>
</body>
</html>
