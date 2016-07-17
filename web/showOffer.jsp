<%@ page import="it.ispw.efco.nottitranquille.model.Structure" %>
<%@ page import="org.joda.time.format.DateTimeFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 20/01/2016
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ elvariable id="location" type="it.ispw.efco.nottitranquille.model.Location.java"--%>

<jsp:useBean id="basicSearchBean" scope="session" class="it.ispw.efco.nottitranquille.view.SearchBean"/>

<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>


<html>

<head>
    <%@include file="header.html" %>

    <%--TODO Inserire nome offerta al posto del title--%>
    <%
        request.setAttribute("formatter", DateTimeFormat.forPattern("dd-MM-yyyy"));
        if (request.getParameter("id") != null) {
            for (Structure structure : basicSearchBean.getResult().keySet()) {
                if (structure.getId() == Long.valueOf(request.getParameter("id"))) {
                    request.setAttribute("structure", structure);
                }
            }
        }
    %>

    <c:set var="locations" value="${basicSearchBean.result.get(structure)}"/>

    <title>Struttura ${structure.name}</title>
    <style>
        body {
            padding-top: 70px;
        }
    </style>

</head>

<body>
<%@include file="navbar.jsp" %>


<div class="container ">
    <div class="col-md-5 col-xs-5">
        <img id="item-display" src="resources/img/piscine-di-albergo-Medulin-2.jpg" class="img-responsive" alt="">
    </div>
    <div class="col-md-7 col-xs-7">
        <h2>${structure.name}</h2>
        <div class="product-desc"></div>
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
                    <div class="form-group">
                        Tipo di struttura: ${structure.type}
                    </div>
                    <div class="form-group">
                        Oraio di checkin: ${structure.checkIn.toString(formatter)}
                    </div>
                    <div class="form-group">
                        Orario di checkout: ${structure.checkOut.toString(formatter)}
                    </div>
                    <div class="form-group">
                        Termini di cancellazione della struttura: ${structure.termsOfCancellation}
                    </div>
                    <div class="form-group">
                        Termini di servizio: ${structure.termsOfService}
                    </div>
                    <div class="form-group">
                        Sita in: ${structure.address}
                    </div>
                    <div class="form-group">
                        Offre i seguenti servizi: ${structure.services}
                    </div>
                    <%--TODO quando saranno riempiti li useremo Termini di cancellazione: ${location.structure.termsOfCancellation}--%>
                    <%--TODO Termini di servizio: ${location.structure.termsofService}--%>
                </section>

            </div>
            <div class="tab-pane fade" id="service-two">

                <section class="container">

                </section>

            </div>
            <div class="tab-pane fade" id="service-three">

            </div>
        </div>
        <div class="container">
            <c:forEach items="${locations}" var="location">
                <div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">Locazione #${locations.indexOf(location)}</div>
                        <div class="panel-body">
                            <div class="form-group">
                                Description: ${location.description}
                            </div>
                            <div class="form-group">
                                # of Rooms ${location.numberOfRooms}
                            </div>
                            <div class="form-group">
                                # of Bathrooms ${location.numberOfBathrooms}
                            </div>
                            Max Guest Number ${location.maxGuestsNumber}
                            <div class="form-group">
                                # of Beds: ${location.numberOfBeds}
                            </div>
                            # of Bedrooms: ${location.numberOfBedrooms}
                            <div class="form-group">
                                Type of location: ${location.type}
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <hr>
    </div>
</div>
</body>

</html>
