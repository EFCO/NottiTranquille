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

<%@include file="navbar.jsp" %>

<div class="container" style="margin-top: 50px">
    <h4>GESTIONE PROFILO</h4>

    <%
        if (request.getParameter("modify") == null) {
    %>
    <form class="form-horizontal" role="form" action="manageProfile.jsp" method="POST">
        <div class="row">
            <label class="col-lg-3 control-label">First name:</label>
            <c:out value="${loginBean.user.firstName}"/>
            <button type="submit" class="btn btn-default" name="modify" value="firstName">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Last name:</label>
            <c:out value="${loginBean.user.lastName}"/>
            <button type="submit" class="btn btn-default" name="modify" value="lastName">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Email:</label>
            <c:out value="${loginBean.user.email}"/>
            <button type="submit" class="btn btn-default" name="modify" value="email">Modifica</button>
        </div>
        <label class="col-lg-3 control-label">Password:</label>
        <button type="submit" class="btn btn-default" name="modify" value="password">Modifica Password</button>
        <div class="row">
            <label class="col-lg-3 control-label">Address:</label>
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
    <c:catch var="notsuchauth">
        <c:if test="${loginBean.user.getRole('Manager') != null}">
            <form class="form-horizontal" role="form" action="manageStructures.jsp" method="POST">
                <button type="submit" class="btn btn-default">Gestisci le tue locazioni</button>
            </form>
        </c:if>
    </c:catch>
    <c:if test="${notsuchauth != null}">
        <div class="row">
            <form action="manageProfile.jsp" method="POST">
                <label class="col-lg-3 control-label">Hai delle locazioni da mettere in affitto?</label>
                <input name="value" value="true" type="text" hidden>
                <button type="submit" name="modify" value="manager" class="btn btn-primary">Si</button>
            </form>
        </div>
    </c:if>
    <%
    } else if (request.getParameter("modify") != null && request.getParameter("value") != null) {
        String modify = request.getParameter("modify");
        String[] value = request.getParameterValues("value");
        int result = loginBean.modifyField(modify, value);
    %>
    <form action="manageProfile.jsp" method="POST">
        <div class="row">
            <label class="col-lg-3 control-label">First name:</label>
            <c:out value="${loginBean.user.firstName}"/>
            <button type="submit" class="btn btn-default" name="modify" value="firstName">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Last name:</label>
            <c:out value="${loginBean.user.lastName}"/>
            <button type="submit" class="btn btn-default" name="modify" value="lastName">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Email:</label>
            <c:out value="${loginBean.user.email}"/>
            <button type="submit" class="btn btn-default" name="modify" value="email">Modifica</button>
        </div>
        <label class="col-lg-3 control-label">Password:</label>
        <button type="submit" class="btn btn-default" name="modify" value="password">Modifica Password</button>
        <div class="row">
            <label class="col-lg-3 control-label">Address:</label>
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
    <c:catch var="notsuchauth">
        <c:if test="${loginBean.user.getRole('Manager') != null}">
            <div class="row">
                <form class="form-horizontal" role="form" action="manageStructures.jsp" method="POST">
                    <button type="submit" class="btn btn-primary">Gestisci le tue locazioni</button>
                </form>
            </div>
        </c:if>
    </c:catch>
    <c:if test="${notsuchauth != null}">
        <div class="row">
            <form action="manageProfile.jsp" method="GET">
                <label class="col-lg-3 control-label">Hai delle locazioni da mettere in affitto?</label>
                <input name="value" value="true" type="text" hidden>
                <button type="submit" name="modify" value="manager" class="btn btn-primary">Si</button>
            </form>
        </div>
    </c:if>
    <%
        if (result == 1) {
    %>
    <div class="alert alert-success">Campo modificato correttamente!</div>
    <%
    } else {
    %>
    <div class="alert alert-danger">Errore nella modifica del campo!</div>
    <%
        }
    } else {
        if (request.getParameter("modify").equals("address")) {
    %>
    <form action="manageProfile.jsp" method="POST">
        <div class="form-group row">
            <div class="col-md-4">
                <label for="address_address">Modifica Indirizzo</label>
                <input name="value" id="address_address" type="text" class="form-control"/>
                <label for="address_city">Modifica Citta</label>
                <input name="value" id="address_city" type="text" class="form-control"/>
                <label for="address_nation">Modifica Nazione</label>
                <input name="value" id="address_nation" type="text" class="form-control"/>
                <label for="address_postalcode">Modifica Codice Postale</label>
                <input name="value" id="address_postalcode" type="number" class="form-control"/>
                <button type="submit" class="btn btn-default" name="modify" value="${param['modify']}">Modifica</button>
            </div>
        </div>
    </form>

    <%
    } else if (request.getParameter("modify").equals("password")) {
    %>
    <form action="manageProfile.jsp" method="POST">
        <div class="form-group row">
            <div class="col-md-4">
                <label for="old_password">Inserisci la tua password precedente</label>
                <input name="value" id="old_password" type="text" class="form-control"/>
                <label for="new_password">Inserisci la nuova password</label>
                <input name="value" id="new_password" type="text" class="form-control"/>
                <label for="check_password">Inserisci di nuovo la nuova password</label>
                <input name="check_password" id="check_password" type="text" class="form-control"/>
                <div class="registrationFormAlert" id="divCheckPasswordMatch">
                </div>
                <button type="submit" id="chg_pass_button" class="btn btn-default" name="modify"
                        value="${param['modify']}">Modifica
                </button>
            </div>
        </div>
    </form>
    <script>
        $(document).ready(function () {
            $("#check_password").keyup(checkPasswordMatch);
            function checkPasswordMatch() {
                var password = $("#new_password").val();
                var confirmPassword = $("#check_password").val();

                if (password != confirmPassword) {
                    $("#divCheckPasswordMatch").html("Le password non coincidono");
                    $('#chg_pass_button').prop("disabled", true);
                }
                else {
                    $("#divCheckPasswordMatch").html("Password coincidenti");
                    $('#chg_pass_button').prop("disabled", false);
                }
            }
        });
    </script>
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
        }
    %>

</div>
<%@include file="bootstrap_core_js.html" %>
</body>
</html>
