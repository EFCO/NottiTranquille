<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="paymentBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.PaymentForm"/>
<jsp:useBean id="Login" scope="session"
             class="it.ispw.efco.nottitranquille.view.LoginBean"/>


<%
    if (request.getParameter("Pay") != null) {
        paymentBean.populate(request.getParameter("Pay"));
        paymentBean.setTenantUsername(Login.getUsername());
    }
    if (request.getParameter("Exit") != null) {
%>
<jsp:forward page="index.jsp"/>
<%
    }
    if (request.getParameter("payNow") != null) {
        if (paymentBean.validate()) {
%>
<jsp:forward page="index.jsp"/>
<%
} else {
%>
<jsp:forward page="errorPage.jsp"/>
<%
        }
    }
%>

<html>
<head>

    <!-- meta for image gallery
================================================== -->

    <style>
        .container-fluid {
            margin-right: auto;
            margin-left: auto;
            max-width: 950px; /* or 950px */
        }
    </style>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/collapse.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/resources/js/legacy.js"></script>


    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <%@include file="header.html" %>

    <title>NottiTranquille</title>

</head>
<body>

<%@include file="navbar.jsp" %>

<br><br>

<div class="container-fluid">
    <h1>Reasume</h1>
    <hr>
    <p>
    <h3>Verify the date before confirm the payment! </h3> </p>
    </br>
    <div class="row">
        <div class="col-sm-3 col-md-6 col-lg-4" style="border-right: outset">

            Booking made by
            <b><c:out value="${paymentBean.reservation.tenantFirstname}"/>
                <c:out value="${paymentBean.reservation.tenantSurname}"/> </b>

            </br></br>

            Booking dates : From <c:out value="${paymentBean.reservation.startDate}"/>
            to <c:out value="${paymentBean.reservation.endDate}"/>

            </br></br>
            <ul>
                <c:if test="${paymentBean.reservation.buyers != null && paymentBean.reservation.buyers.size() != 0}">
                    Your room mates are :

                    <c:forEach items="${paymentBean.reservation.buyers}" var="name">
                        <li>${name}</li>
                    </c:forEach>

                </c:if>
            </ul>
            </br></br>

        </div>
        <div class="col-sm-9 col-md-6 col-lg-8">

            Address: <c:out value="${paymentBean.reservation.location.address}"/>
            <br>
            Price: <c:out value="${paymentBean.reservation.price}"/> $

        </div>
    </div>

    <hr>

    <form action="Payment.jsp" id="PayNow" class="form-horizontal">
        <div align="right" align="bottom">
            <button type="submit" class="btn btn-lg btn-primary" name="payNow">Pay Now</button>
            <button type="submit" class="btn btn-lg btn-primary" name="Exit">Exit</button>
        </div>
    </form>
</div>


</body>
</html>
