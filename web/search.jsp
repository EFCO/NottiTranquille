<%@ page import="org.joda.time.format.DateTimeFormat" %>
<%@ page import="org.joda.time.format.DateTimeFormatter" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

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
    }
%>

<html>
<head>

    <%@include file="header.html" %>
    <%--<script src="resources/js/searchValidation.js"></script>--%>
    <title>Title</title>

</head>
<body>

<!--sarà la pagina di default se non verrano passati dei dati-->
<form action="search.jsp" name="myform" method="POST" id="searchForm">
        <div class="form-group">
            <label for="nation">Nazione :</label>
            <input name="nation" id="nation" type="text" class="form-control" placeholder="Roma" required>
        </div>
        <div class="form-group">
            <label for="city">Città :</label>
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
        <%
            List<Location> result = new ArrayList<>();
            if (basicSearchBean.validate()) {
                result = basicSearchBean.getResult();
            }
        pageContext.setAttribute("result", result);
    %>
    <div style="width: 800px; margin-left: 50px; margin-top: 30px;">

        <%
            if ( result.size() > 0 ) {
        %>

        <div class="col-md-11">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="icon-calendar"></i>
                    <h3 class="panel-title">Location list</h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover col-md-11">
                        <thead>
                        <tr>
                            <th class="col-md-2">Locartion name</th>
                            <th class="col-md-2">Address</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${result}" var="location">
                            <td><c:out value="${location.structure.name}"/></td>
                            <td><c:out value="${location.structure.address.address}"/></td>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>



</form>
</body>
</html>
