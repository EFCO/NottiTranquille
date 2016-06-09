<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%@ page import="it.ispw.efco.nottitranquille.controller.FilteredSearch" %><%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 20/01/2016
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ elvariable id="location" type="it.ispw.efco.nottitranquille.model.Location.java"--%>

<jsp:useBean id="basicSearchBean" scope="session" class="it.ispw.efco.nottitranquille.view.SearchBean"/>

<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>


<html>

<head>
    <%@include file="header.html" %>

    <%--TODO Inserire nome offerta al posto del title--%>
    <c:set var="location" value="${basicSearchBean.result.get(requestScope.id)}"/>


    <title>${location.structure.name}</title>
    <style>
        body {
            padding-top: 70px;
        }
    </style>

</head>

<body>
<%@include file="navbar.html" %>


<div class="container ">
    <div class="col-md-5 col-xs-5">
        <img id="item-display" src="resources/img/piscine-di-albergo-Medulin-2.jpg" class="img-responsive" alt="">
    </div>
    <div class="col-md-7 col-xs-7">
        <h2>${location.structure.name}</h2>
        <div class="product-desc">${location.description}</div>
        <hr>
        <div class="product-price">1234.00 &euro;</div>
        <hr>
        <div class="btn-group cart">
            <button type="button" class="btn btn-success">
                Prenota ora
            </button>
        </div>
    </div>
</div>
<div class="container">
    <div class="col-md-12 product-info">
        <ul id="myTab" class="nav nav-tabs nav_tabs">

            <li class="active"><a href="#service-one" data-toggle="tab">Descrizione</a></li>
            <li><a href="#service-two" data-toggle="tab">Fotografie</a></li>
            <li><a href="#service-three" data-toggle="tab">Recensioni</a></li>

        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="service-one">

                <section class="container product-info">
                    Numero di ospiti massimi: ${location.maxGuestsNumber}
                    Numero di bagni: ${location.numberOfBathrooms}
                    Numero di letti: ${location.numberOfBeds}
                    Numero di stanze: ${location.numberOfRooms}
                    <%--TODO quando saranno riempiti li useremo Termini di cancellazione: ${location.structure.termsOfCancellation}--%>
                    <%--TODO Termini di servizio: ${location.structure.termsofService}--%>
                </section>

            </div>
            <div class="tab-pane fade" id="service-two">

                <section class="container">
                    ${location.photos}
                </section>

            </div>
            <div class="tab-pane fade" id="service-three">

            </div>
        </div>
        <hr>


</body>

</html>
