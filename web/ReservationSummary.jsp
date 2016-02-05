<%@ page import="it.ispw.efco.nottitranquille.model.dao.TenantDao" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Tenant" %>
<%@ page import="it.ispw.efco.nottitranquille.view.LoginBean" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Reservation" %>


<%@ page import="java.util.List" %>
<%@ page import="it.ispw.efco.nottitranquille.model.dao.ManagerDAO" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Manager" %>
<%@ page import="it.ispw.efco.nottitranquille.controller.ReservationController" %>
<%@ page import="it.ispw.efco.nottitranquille.model.dao.ReservationDAO" %><%--
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

<%
    if(request.getParameter("Approve")!=null){

        LoginBean login = (LoginBean) session.getAttribute("Login");

        Reservation approve = ReservationDAO.findByID(Long.decode(
                request.getParameter("Approve")));

        ReservationController.getInstance().approveReservation(approve);
    }
%>

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
        request.setAttribute("reservations", reservations);

        int resValue = reservations.size();
        request.setAttribute("resValue", resValue);
%>

<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Location</th>
            <th>from</th>
            <th>to</th>
            <th>price</th>
            <th>pay</th>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="i" begin="0" end="${resValue-1}">

            <form action="Payment.jsp">
                <tr>
                    <td>${reservations.get(i).getLocation().getName()}</td>
                    <td><joda:format value="${reservations.get(i).getStartDate()}" locale="en_US" style="SM"
                                     pattern="dd MMM, yyyy HH:mm"/></td>
                    <td><joda:format value="${reservations.get(i).getEndDate()}" locale="en_US" style="SM"
                                     pattern="dd MMM, yyyy HH:mm"/></td>
                    <td>${reservations.get(i).getPrice()}</td>

                    <c:choose>
                        <c:when test="${reservations.get(i).getState() == 'Paid'}">
                            <td>Paid</td>
                        </c:when>
                        <c:when test="${reservations.get(i).getState() == 'ToApprove'}">
                            <td>to be approve</td>
                        </c:when>
                        <c:when test="${reservations.get(i).getState() == 'Declined'}">
                            <td>Declined</td>
                        </c:when>
                        <c:when test="${reservations.get(i).getState() == 'ToPay'}">
                            <td>
                                <button type="submit" class="btn btn-primary" name="Pay" id="Pay"
                                        value="${reservations.get(i).getId()}">Pay
                                </button>
                            </td>
                        </c:when>
                    </c:choose>


                </tr>
            </form>

        </c:forEach>

        </tbody>
    </table>
</div>
<%
} else if (login.getRole() == "Manager") {

    Manager manager = ManagerDAO.findByNameAndPassword(login.getUsername(),
            login.getPassword());

    List<Reservation> reservations = manager.getToApprove();
    request.setAttribute("reservations", reservations);

    int resValue = reservations.size();
    request.setAttribute("resValue", resValue);

%>


<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Location</th>
            <th>from</th>
            <th>to</th>
            <th>Tenant</th>
            <th>Approve</th>

        </tr>
        </thead>
        <tbody>


        <c:forEach var="i" begin="0" end="${resValue-1}">

        <form action="ReservationSummary.jsp">
            <tr>
                <td>${reservations.get(i).getLocation().getName()}</td>
                <td><joda:format value="${reservations.get(i).getStartDate()}" locale="en_US" style="SM"
                                 pattern="dd MMM, yyyy HH:mm"/></td>
                <td><joda:format value="${reservations.get(i).getEndDate()}" locale="en_US" style="SM"
                                 pattern="dd MMM, yyyy HH:mm"/></td>

                <td><c:out value=" ${reservations.get(i).getTenant().getFirstName()} ${
                reservations.get(i).getTenant().getLastName()}"> </c:out> </td>

                <c:choose>
                    <c:when test="${reservations.get(i).getState() == 'Paid'}">
                        <td>Paid</td>
                    </c:when>
                    <c:when test="${reservations.get(i).getState() == 'ToApprove'}">
                        <td>
                            <button type="submit" class="btn btn-primary" name="Approve" id="Approve"
                                    value="${reservations.get(i).getId()}">Approve
                            </button>
                        </td>
                    </c:when>
                    <c:when test="${reservations.get(i).getState() == 'Declined'}">
                        <td>Declined</td>
                    </c:when>
                    <c:when test="${reservations.get(i).getState() == 'ToPay'}">
                        <td>To pay</td>
                    </c:when>
                </c:choose>
            </tr>
            </tr>
        </form>

        </c:forEach>


                <%
        }
    %>

        </c:if>


</div>

</body>
</html>
