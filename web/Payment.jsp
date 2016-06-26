<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="paymentBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.PaymentForm"/>
<jsp:useBean id="Login" scope="session"
             class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<%
    if (request.getParameter("Pay") != null) {
        paymentBean.setReservationID(request.getParameter("Pay"));
        paymentBean.setTenantUsername(Login.getUsername());
    }

    if (request.getParameter("payNow") != null) {
        if (paymentBean.validate()) {
%>
<jsp:forward page="index.jsp"/>
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
    <h1>Large Grid</h1>
    <p>The following example will result in a 25%/75% split on small devices, a 50%/50% split on medium devices, and a
        33%/66% split on large devices. On extra small devices, it will automatically stack (100%).</p>
    <p>Resize the browser window to see the effect.</p>
    <div class="row">
        <div class="col-sm-3 col-md-6 col-lg-4" style="border-right: outset">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua.
            Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        </div>
        <div class="col-sm-9 col-md-6 col-lg-8">
            Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem
            aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.
        </div>
    </div>

    <hr>

    <form action="Payment.jsp" id="PayNow" class="form-horizontal">
        <div align="right" align="bottom">
            <button type="submit" class="btn btn-lg btn-primary" name="payNow">Pay Now</button>
        </div>
    </form>
</div>


</body>
</html>
