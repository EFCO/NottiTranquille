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

<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

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

    <title>Search</title>

    <style>
        body {
            padding-top: 50px;
            padding-left: 10px;
            padding-right: 10px;
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#0fb4e7+0,a9e4f7+100 */
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#76d8f6+0,cff0fb+100 */
            background: #76d8f6; /* Old browsers */
            background: -moz-linear-gradient(top,  #76d8f6 0%, #cff0fb 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(top,  #76d8f6 0%,#cff0fb 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(to bottom,  #76d8f6 0%,#cff0fb 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#76d8f6', endColorstr='#cff0fb',GradientType=0 ); /* IE6-9 */


        }
    </style>

</head>
<body>

<%@include file="navbar.html" %>

<div id="searchForm" >
    <form action="search.jsp" name="myform" method="POST" id="searchForm">
            <div class="row">
                <div class="form-group col-xs-4 col-md-4">
                    <label for="nation">Nazione :</label>
                    <input name="nation" id="nation" type="text" class="form-control" placeholder="Roma" required>
                </div>
                <div class="form-group col-xs-4 col-md-4">
                    <label for="city">Città :</label>
                    <input name="city" id="city" type="text" class="form-control" placeholder="Zagarolo" required>
                </div>
                <div class="form-group col-xs-4 col-md-4">
                    <label for="pricerange">Prezzo :</label>
                    <select name="pricerange" id="pricerange" class="form-control">
                        <option>Fino a 100 euro</option>
                        <option>Fino a 200 euro</option>
                        <option>Fino a 500 euro</option>
                        <option>Nessun limite</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="container">
                    <div class='col-md-6 col-xs-6'>
                        <div class="form-group">
                            <div class='input-group date' id='checkinpicker'>
                                <input type='text' name="checkin" id ="checkin" class="form-control" placeholder="Check In"/>
                                <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class='col-md-6 col-xs-5'>
                        <div class="form-group">
                            <div class='input-group date' id='checkoutpicker'>
                                <input type='text' name="checkout" id="checkout" class="form-control" placeholder="Check Out"/>
                                <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        $('#checkinpicker').datetimepicker({
                           format: 'DD-MM-YYYY'
                        });
                        $('#checkoutpicker').datetimepicker({
                            format: 'DD-MM-YYYY',
                            useCurrent: false //Important! See issue #1075
                        });
                        $("#checkinpicker").on("dp.change", function (e) {
                            $('#checkinpicker').data("DateTimePicker").minDate(e.date);
                        });
                        $("#checkoutpicker").on("dp.change", function (e) {
                            $('#checkoutpicker').data("DateTimePicker").maxDate(e.date);
                        });
                    });
                </script>
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
            <div class="btn-group btn-group-justified">
                <div class="btn-group">
                    <button type="button" class="btn btn-default">Advanced Search</button>
                </div>
                <div class="btn-group">
                    <button name="search" type="submit" class="btn btn-primary" value="search" id="search"><span class="glyphicon glyphicon-search"></span> Search</button>
                </div>
            </div>
    </form>
    </section>
            <%
                List<Location> result = new ArrayList<>();
                if (basicSearchBean.validate()) {
                    result = basicSearchBean.getResult();
                }
                pageContext.setAttribute("result", result);
            %>
        <%--<div style="width: 800px; margin-left: 50px; margin-top: 30px;">--%>
            <%
                if (request.getParameter("search") != null && result.size() > 0) {
            %>

    <div class="container">
            <c:forEach items="${result}" var="location">
                <div class="row">
                    <div class="col-sm-6 col-xs-6 col-md-4">
                        <div class="thumbnail">
                            <img src="${location.photos}" alt="...">
                            <div class="caption">
                                <h3>${location.structure.name}</h3>
                                <p>${location.locationAddress}</p>
                                <p><a href="#" class="btn btn-primary" role="button">Vai</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <%
                } else {
            %>
                    <div class="alert alert-danger" role="alert">Nessun risultato trovato!</div>
            <%
                }
            %>
        </div>
</div>


<%@include file="bootstrap_core_js.html" %>

</body>

</html>
