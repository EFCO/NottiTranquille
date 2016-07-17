<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Structure" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.Commodities" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.StructureType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>

<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 30/12/2015
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" session="true" %>
<%--session=true keep the session alive and it should be used inside pages that initialize session beans--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<jsp:useBean id="basicSearchBean" scope="session" class="it.ispw.efco.nottitranquille.view.SearchBean"/>
<%--scope can be "session", "application", "page", "request"--%>
<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<jsp:setProperty name="basicSearchBean" property="*"/>
<%--it sets all the properties of the requried bean if such properties are passed throught a GET or POST request--%>

<%
    if (request.getParameter("search") != null) {
//       This two checks are required beacuse the bean does not override a previously set variable if it is empty
//       e.g.: if city was "Cagliari" the bean has "Cagliari" as value for its city attribute, but if I clean the textfield and make a new search
//       it remains "Cagliari" and it is not overridden.
        if (request.getParameter("nation").equals("")) {
            basicSearchBean.setNation("");
        }
        if (request.getParameter("city").equals("")) {
            basicSearchBean.setCity("");
        }
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
        }

        #searchForm {
            padding: 15px 15px;
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#0fb4e7+0,a9e4f7+100 */
            /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#76d8f6+0,cff0fb+100 */
            background: #76d8f6; /* Old browsers */
            background: -moz-linear-gradient(top, #76d8f6 0%, #cff0fb 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(top, #76d8f6 0%, #cff0fb 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(to bottom, #76d8f6 0%, #cff0fb 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#76d8f6', endColorstr='#cff0fb', GradientType=0); /* IE6-9 */
        }

        #resultSet {
            padding-top: 20px;
        }

    </style>
    <script>
        $(function () {
            function orderBy(type) {

                var order = {}; //it will contain all the hmtl for each card
                var keys = {}; //it will contain all the price texts
                var appoggio = [];
                $(type + "_text").each(function (j, elem) {
                    keys[j] = $(elem).text(); //copy price text of each card
                    appoggio[j] = keys[j]; //mantain the same copy in "appoggio"
                });
                $("div.paneldiv").each(function (i, elem) {
                    order[i] = $(elem).html(); //copy html for each card
                });

                $("#result_row").empty(); //empty the row containing the cards

                appoggio.sort(); //reorder just appoggio
                for (var i = 0; i < appoggio.length; i++) {
                    for (var key in keys) {
                        if (keys[key] == appoggio[i]) { //confront an element of appoggio with an element of key (that has the same order of "order"
                            $("#row").append(order[key]); //append an element of order that has the same key ordered in appoggio
                        }
                    }
                }
            }
        });
    </script>
</head>
<body>

<%@include file="navbar.jsp" %>

<c:set var="nation" value="${param.nation}"/>
<c:set var="city" value="${param.city}"/>
<c:set var="pricerange" value="${param.pricerange}"/>
<c:set var="checkin" value="${param.checkin}"/>
<c:set var="checkout" value="${param.checkout}"/>
<c:set var="search" value="${param.search}"/>
<c:set var="structuretype" value="${param.structuretype}"/>
<c:set var="maxtenant" value="${param.maxtenant}"/>

<%
    for (Commodities elem : Commodities.values()) {
        //if there are commodities passed as parameters in the URL then set them
        if (request.getParameter(elem.name()) != null) {
            pageContext.setAttribute(elem.name(), request.getParameter(elem.name()));
        } else {
            pageContext.setAttribute(elem.name(), "");
        }
    }
    //It useful for checkbox setting later
    pageContext.setAttribute("Commodities", Commodities.values());
%>

<c:set var="structuretypes" value="<%=StructureType.values()%>"/>

<c:if test="${nation == null}">
    <c:set var="nation" value=""/>
</c:if>
<c:if test="${city == null}">
    <c:set var="city" value=""/>
</c:if>
<c:if test="${pricerange == null}">
    <c:set var="pricerange" value=""/>
</c:if>
<c:if test="${search == null}">
    <c:set var="search" value="search"/>
</c:if>
<c:if test="${structuretype == null}">
    <c:set var="structuretype" value=""/>
</c:if>
<c:if test="${maxtenant == null}">
    <c:set var="maxtenant" value=""/>
</c:if>


<div id="searchFormContainer">
    <form action="search.jsp" name="myform" method="POST" id="searchForm">
        <div class="row">
            <div class="form-group col-xs-4 col-md-4">
                <label for="nation">Nazione :</label>
                <input name="nation" id="nation" type="text" class="form-control" placeholder="Roma" value="${nation}"/>
            </div>
            <div class="form-group col-xs-4 col-md-4">
                <label for="city">Città :</label>
                <input name="city" id="city" type="text" class="form-control" placeholder="Zagarolo" value="${city}"/>
            </div>
            <div class="form-group col-xs-4 col-md-4">
                <label for="pricerange">Prezzo :</label>
                <select name="pricerange" id="pricerange" class="form-control">
                    <c:forEach items="${priceranges}" var="pr">
                        <option value="${pr.name()}" ${pricerange == pr.name() ? "selected='selected'" : ''}>${pr.text}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="container">
                <div class='form-group col-md-6 col-xs-6'>
                    <div class='input-group date' id='checkinpicker'>
                        <input type='text' name="checkin" id="checkin" class="form-control" placeholder="Check In"
                               value="${checkin}" required/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class='form-group col-md-6 col-xs-6'>
                    <div class='input-group date' id='checkoutpicker'>
                        <input type='text' name="checkout" id="checkout" class="form-control" placeholder="Check Out"
                               value="${checkout}" required/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <script>
                $(function () {
                    var checkinpicker = $('#checkinpicker');
                    var checkoutpricker = $('#checkoutpicker');
                    checkinpicker.datetimepicker({
                        format: 'DD-MM-YYYY'
                    });
                    checkoutpricker.datetimepicker({
                        format: 'DD-MM-YYYY',
                        useCurrent: false //Important! See issue #1075
                    });
                    checkinpicker.on("dp.change", function (e) {
                        checkoutpricker.data("DateTimePicker").minDate(e.date);
                    });
                    checkoutpricker.on("dp.change", function (e) {
                        checkinpicker.data("DateTimePicker").maxDate(e.date);
                    });
                });
            </script>
        </div>
        <div class="collapse row" id="collapseSearch">
            <div class="form-group col-xs-4 col-md-4">
                <label for="structuretype">Tipo di alloggio :</label>
                <select name="structuretype" id="structuretype" class="form-control">
                    <c:forEach items="${structuretypes}" var="type">
                        <option value="${type.name()}" ${structuretype == type.name() ? "selected='selected'" : ''}>${type.text}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-xs-4 col-md-4">
                <label for="maxtenant">Numero di ospiti :</label>
                <select name="maxtenant" id="maxtenant" class="form-control">
                    <c:forEach begin="1" end="5" step="1" var="num">
                        <option value="${num}" ${maxtenant == num ? "selected='selected'" : ''}>${num}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="row">
                <div class="form-group col-xs-4 col-md-4">
                    <c:forEach items="${Commodities}" var="commodity">
                        <label class="checkbox">
                            <input id="${commodity.name()}" name="${commodity.name()}"
                                   type="checkbox" ${param[commodity.name()] == 'on' ? 'checked' : ''}> ${commodity.text}
                            <%
                                //I have to set Commodities attribute as an array with 0 or 1 depending on which commodity is checked
                                int[] array = new int[Commodities.values().length];
                                for (Commodities elem : Commodities.values()) {
                                    if (request.getParameter(elem.name()) != null) {
                                        if (request.getParameter(elem.name()).equals("on")) {
                                            array[Commodities.valueOf(elem.name()).ordinal()] = 1;
                                        } else {
                                            array[Commodities.valueOf(elem.name()).ordinal()] = 0;
                                        }
                                    }
                                }
                                basicSearchBean.setCommodities(array);
                            %>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%--<%--%>
        <%--if (request.getParameter("search") != null) {--%>
        <%--%>--%>
        <%--<div class="alert alert-danger" role="alert" id="alert" style="display:none">--%>
        <%--Devi riempire tutti i campi per effettuare una ricerca!--%>
        <%--</div>--%>
        <%--<%--%>
        <%--}--%>
        <%--%>--%>
        <div class="btn-group btn-group-justified">
            <div class="btn-group">
                <script>
                    var search = '${search}';
                    if (search == 'advsearch') {
                        $('#collapseSearch').collapse();
                    }
                </script>
                <script>
                    //To alternate 'search' variabile value
                    function searchSwitcher() {
                        var searchbutton = document.getElementById("searchbutton");
                        if (searchbutton.value == 'search') {
                            searchbutton.value = 'advsearch';
                        } else {
                            searchbutton.value = 'search';
                        }
                    }
                </script>
                <button type="button" onclick="searchSwitcher()" id=advsearchbutton" class="btn btn-default"
                        data-toggle="collapse" data-target="#collapseSearch" aria-expanded="false"
                        aria-controls="collapseSearch">Advanced Search
                </button>
            </div>
            <div class="btn-group">
                <button name="search" type="submit" class="btn btn-primary" value='${search}' id="searchbutton"><span
                        class="glyphicon glyphicon-search"></span> Search
                </button>
            </div>
        </div>
    </form>
</div>

<button class="btn btn-default" onclick="orderBy('price')">Order by Price</button>
<button class="btn btn-default" onclick="orderBy('name')">Order by Name</button>

<div id="resultSet">
    <%
        HashMap<Structure, ArrayList<Location>> result = new HashMap<>();
        if (request.getParameter("search") != null) {
            try {
                basicSearchBean.validate();
            } catch (Exception e) {
    %>
    <div class="alert alert-danger" role="alert"><%=e.getLocalizedMessage()%>
    </div>
    <%
            }
            result = basicSearchBean.getResult();

        }
        pageContext.setAttribute("result", result);
    %>
    <%
        if (result.size() > 0) {
    %>
    <div class="container">
        <div class="row" id="result_row">
            <c:forEach items="${result}" var="structure">
                <div class="paneldiv">
                    <div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                        <div class="panel price panel-red">
                            <div class="thumbnail">
                                    <%--${location.photos}--%>
                                <img src="resources/img/piscine-di-albergo-Medulin-2.jpg" alt="...">
                                <div class="panel-heading text-center" id="name_text">
                                    <h3>${structure.key.name}</h3></div>
                                    <%--${location.locationAddress}--%>
                                <div class="panel-body text-center" id="price_text"><p class="lead"><strong>28 &euro; a
                                    notte</strong></p></div>
                                <p class="lead"><strong>Questa struttura comprende ${structure.value.size()}
                                    locazioni</strong></p>
                                <div class="panel-footer text-center"><a
                                        href="<c:url value="showOffer.jsp"><c:param name="id" value="${structure.key.id}"/></c:url>"
                                        type="submit" class="btn btn-lg btn-block" role="button">Mostra</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <%
    } else if (request.getParameter("search") != null && result.size() == 0) {
    %>
    <div class="alert alert-danger" role="alert">Nessun risultato trovato!</div>

    <%
        } else {
        }
    %>
</div>

<%@include file="bootstrap_core_js.html" %>

</body>

</html>
