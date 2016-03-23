<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.LocationType" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.Commodities" %>

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

<jsp:useBean id="basicSearchBean" scope="session"
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
        #searchForm {
            padding-top: 30px;
            padding-left: 10px;
            padding-right: 10px;
            padding-bottom: 20px;
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#0fb4e7+0,a9e4f7+100 */
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#76d8f6+0,cff0fb+100 */
            background: #76d8f6; /* Old browsers */
            background: -moz-linear-gradient(top,  #76d8f6 0%, #cff0fb 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(top,  #76d8f6 0%,#cff0fb 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(to bottom,  #76d8f6 0%,#cff0fb 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#76d8f6', endColorstr='#cff0fb',GradientType=0 ); /* IE6-9 */
        }

        #resultSet {
            padding-top: 20px;
        }
    </style>

</head>
<body>

<%@include file="navbar.html" %>

<c:set var="nation"  value="${param.nation}"/>
<c:set var="city"  value="${param.city}"/>
<c:set var="pricerange"  value="${param.pricerange}"/>
<c:set var="checkin"  value="${param.checkin}"/>
<c:set var="checkout"  value="${param.checkout}"/>


<c:if test="${nation == null}">
    <c:set var="nation" value=""/>
</c:if>
<c:if test="${city == null}">
    <c:set var="city" value=""/>
</c:if>
<c:if test="${pricerange == null}">
    <c:set var="pricerange" value=""/>
</c:if>
<c:if test="${checkin == null}">
    <c:set var="checkin" value=""/>
</c:if>
<c:if test="${checkout == null}">
    <c:set var="checkout" value=""/>
</c:if>

<div id="searchForm" >
    <form action="search.jsp" name="myform" method="POST" id="searchForm">
        <div class="row">
            <div class="form-group col-xs-4 col-md-4">
                <label for="nation">Nazione :</label>
                <input name="nation" id="nation" type="text" class="form-control" placeholder="Roma" required value="${nation}"/>
            </div>
            <div class="form-group col-xs-4 col-md-4">
                <label for="city">Città :</label>
                <input name="city" id="city" type="text" class="form-control" placeholder="Zagarolo" required value="${city}"/>
            </div>
            <div class="form-group col-xs-4 col-md-4">
                <label for="pricerange">Prezzo :</label>
                <select name="pricerange" id="pricerange" class="form-control" value="${pricerange}">
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
                            <input type='text' name="checkin" id ="checkin" class="form-control" placeholder="Check In" required value="${checkin}"/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
                <div class='col-md-6 col-xs-6'>
                    <div class="form-group">
                        <div class='input-group date' id='checkoutpicker'>
                            <input type='text' name="checkout" id="checkout" class="form-control" placeholder="Check Out" required value="${checkout}"/>
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
                        $('#checkoutpicker').data("DateTimePicker").minDate(e.date);
                    });
                    $("#checkoutpicker").on("dp.change", function (e) {
                        $('#checkinpicker').data("DateTimePicker").maxDate(e.date);
                    });
                });
            </script>
        </div>
        <div class="collapse row" id="collapseSearch">
            <div class="form-group col-xs-4 col-md-4">
                <label for="locationtype">Tipo di alloggio :</label>
                <select name="locationtype" id="locationtype" class="form-control">
                    <% pageContext.setAttribute("LocationTypes", LocationType.values()); %>
                    <c:forEach items="${LocationTypes}" var="type">
                        <option>${type.name()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-xs-4 col-md-4">
                <label for="maxtenant">Numero di ospiti :</label>
                <select name="maxtenant" id="maxtenant" class="form-control">
                    <c:forEach begin="1" end="5" step="1" var="num">
                        <option>${num}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="row">
                <div class="form-group col-xs-4 col-md-4">
                    <% pageContext.setAttribute("Commodites", Commodities.values()); %>
                    <c:forEach items="${Commodites}" var="commodity">
                        <label class="checkbox">
                            <input type="checkbox"> ${commodity.name()}
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%
            if (request.getParameter("search") != null) {
        %>
        <%--<c:if test="${param.search != null}">--%>
            <div class="alert alert-danger" role="alert" id="alert" style="display:none">
                Devi riempire tutti i campi per effettuare una ricerca!
            </div>
        <%--</c:if>--%>
        <%
            }
        %>
        <div class="btn-group btn-group-justified">
            <div class="btn-group">
                <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#collapseSearch" aria-expanded="false" aria-controls="collapseSearch">Advanced Search</button>
            </div>

            <div class="btn-group">
                <button name="search" type="submit" class="btn btn-primary" value="search" id="search"><span class="glyphicon glyphicon-search"></span> Search</button>
            </div>
        </div>
    </form>
</div>
<div id="resultSet" >
    <%
        List<Location> result = new ArrayList<>();
        if (request.getParameter("search") != null) {
            if (basicSearchBean.validate()) {
                result = basicSearchBean.getResult();
            }
        }
        pageContext.setAttribute("result", result);
    %>
    <%--<c:if test="${basicSearchBean.validate()}">--%>
        <%--<c:set var="result" value="${basicSearchBean.result}"/>--%>
    <%--</c:if>--%>

    <%--&lt;%&ndash;<div style="width: 800px; margin-left: 50px; margin-top: 30px;">&ndash;%&gt;--%>
        <%
            if (result.size() > 0) {
        %>
    <%--<c:choose>--%>
        <%--<c:when test="${result.size > 0}">--%>
            <div class="container">
                <c:forEach items="${result}" var="location">
                    <div class="row">
                        <div class="col-sm-6 col-xs-6 col-md-4">
                            <div class="thumbnail">
                                <img src="${location.photos}" alt="...">
                                <div class="caption">
                                    <h3>${location.structure.name}</h3>
                                    <p>${location.locationAddress}</p>
                                    <p><a href="<c:url value="showOffer.jsp"><c:param name="id" value="${result.indexOf(result)}"/></c:url>" type="submit" class="btn btn-primary" role="button">Mostra</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        <%--</c:when>--%>
        <%
            } else if (request.getParameter("search") != null && result.size() == 0) {
        %>
        <%--<c:when test="${param.search != null && result.size() == 0}">--%>
                <div class="alert alert-danger" role="alert">Nessun risultato trovato!</div>
        <%--</c:when>--%>
        <%--<c:otherwise></c:otherwise>--%>
    <%--</c:choose>--%>
    <%
        } else {
        }
    %>
</div>


<%@include file="bootstrap_core_js.html" %>

</body>

</html>
