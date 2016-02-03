<%@ page import="it.ispw.efco.nottitranquille.model.dao.TenantDao" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Tenant" %>
<%@ page import="it.ispw.efco.nottitranquille.view.LoginBean" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Reservation" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: emanuele
  Date: 31/01/16
  Time: 17.24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/collapse.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


    <%@include file="header.html" %>

    <title>NottiTranquille</title>

</head>


<body>

<!-- NAVBAR
================================================== -->
<%@include file="navbar.html" %>

<br/><br/>


<div class="container"></div>

<%--User must be logged--%>
<c:if test="${ Login==null or Login.getRole()==null}">
    <c:redirect url="./index.jsp"></c:redirect>
</c:if>


<c:if test="${ Login!= null and Login.getRole()!= null}">
    <p>
    <h2> Benvenuto ${Login.getUsername()}!</h2></p>


    <p><h4> Ecco il resoconto delle tue prenotazioni!</h4></p>
    <br/>


    <%

        LoginBean login = (LoginBean) session.getAttribute("Login");
        if (login.getRole() == "Tenant") {

            Tenant tenant = TenantDao.findByNameAndPassword(
                    login.getUsername(), login.getPassword());

            List<Reservation> reservations = tenant.getReservations();
            int resValue = reservations.size();

    %>

    <div class="container">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Location</th>
                <th>date</th>
                <th>price</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${reservations}" var="reservation">

                <tr>
                    <td>${reservation.getLocation().getName()}</td>
                    <td>Doe</td>
                    <td>${reservation.getLocation().getPrice()}</td>
                </tr>

            </c:forEach>


            </tbody>
        </table>
    </div>

    <%
        }
    %>
</c:if>


</div>

</body>
</html>
