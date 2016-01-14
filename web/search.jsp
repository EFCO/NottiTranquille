<%@ page import="org.joda.time.format.DateTimeFormat" %>
<%@ page import="org.joda.time.format.DateTimeFormatter" %>
<%@ page import="org.joda.time.DateTime" %>

<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 30/12/2015
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="basicSearchBean" scope="request"
             class="it.ispw.efco.nottitranquille.view.SearchBean" />

<jsp:setProperty name="basicSearchBean" property="*" />

<%
    if (request.getParameter("search") != null) {
        if (!request.getParameter("checkin").equals("") && !request.getParameter("checkout").equals("")) {
            basicSearchBean.setCheckin(request.getParameter("checkin"));
            basicSearchBean.setCheckout(request.getParameter("checkout"));
        }

        if (basicSearchBean.validate()) {
            %>
            <!-- Passa il controllo alla nuova pagina -->
            <jsp:forward page="RiassuntoLogin.jsp" />
            <%
        }
    }
%>

<html>
<head>

    <%@include file="header.html" %>
    <%--<script src="resources/js/searchValidation.js"></script>--%>
    <title>Title</title>

</head>
<body>

<!--sar� la pagina di default se non verrano passati dei dati-->
<form action="search.jsp" name="myform" method="POST" id="searchForm">
        <div class="form-group">
            <label for="nation">Nazione :</label>
            <input name="nation" id="nation" type="text" class="form-control" placeholder="Roma" required>
        </div>
        <div class="form-group">
            <label for="city">Citt� :</label>
            <input name="city" id="city" type="text" class="form-control" placeholder="Zagarolo" required>
        </div>
        <div class="form-group">
            <label for="checkin">Check In:</label>
            <input name="checkin" id="checkin" type="date" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="checkout">Check Out:</label>
            <input name="checkout" id="checkout" type="date" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="pricerange">Prezzo :</label>
            <select name="pricerange" id="pricerange" class="form-control">
                <option>Fino a 100 euro</option>
                <option>Fino a 200 euro</option>
                <option>Fino a 500 euro</option>
                <option>Nessun limite</option>
            </select>
        </div>
        <%
            if (request.getParameter("search") != null) {
        %>
            <div class="alert alert-danger" role="alert" id="alert" style="display:none">
                Devi riempire tutti i campi per effettuare una ricerca!
            </div>
        <%
            }
        %>
        <button type="button" class="btn btn-default">Advanced Search</button>
        <button name="search" type="submit" class="btn btn-primary" value="search" id="search">Search</button>



</form>
</body>
</html>
