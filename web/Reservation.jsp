<%@ page import="it.ispw.efco.nottitranquille.view.LoginBean" %><%--
  Created by IntelliJ IDEA.
  User: emanuele
  Date: 16/01/16
  Time: 16.12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<jsp:useBean id="reservationBean" scope="request"
             class="it.ispw.efco.nottitranquille.view.ReservationBean"/>
<jsp:useBean id="locationBean" scope="request"
             class="it.ispw.efco.nottitranquille.view.LocationBean"/>

<jsp:setProperty name="reservationBean" property="*"/>

<%
    //Needs because compiler not resolve variable Login in ReservationSummary.
    //Cause it re-compiles when url for Reservation.jsp is entered
    LoginBean Login = (LoginBean) session.getAttribute("Login");

    // Assuming the id of the location we want to see is in the URL
    // because a GET request is made.
    // Pull model of mvc architecture
    if (request.getParameter("id") != null)
        locationBean.populate(request.getParameter("id"));

%>

<%
    if (request.getParameter("Reserve") != null) {

        for (int i = 0; request.getParameter("firstname" + i) != null && request.getParameter("surname" + i) != null; i++) {

            if (request.getParameter("firstname" + i) != null && request.getParameter("firstname" + i) != "" &&
                    request.getParameter("surname" + i) != null && request.getParameter("surname" + i) != "") {
                reservationBean.addBuyer(request.getParameter("firstname" + i), request.getParameter("surname" + i));
            }
        }

        reservationBean.setTenantUsername(Login.getUsername());
        reservationBean.setLocationBean(locationBean);
    }

    if (reservationBean.validate()) {
%>
<!-- Passa il controllo alla nuova pagina -->
<jsp:forward page="Payment.jsp"/>
<%
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>

    <!-- meta for image gallery
================================================== -->

    <style>
        .container {
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

    <script type="text/javascript" src="/resources/js/picker.js"></script>
    <script type="text/javascript" src="/resources/js/picker.date.js"></script>
    <script type="text/javascript" src="/resources/js/picker.time.js"></script>
    <script type="text/javascript" src="/resources/js/legacy.js"></script>


    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


    <link rel="stylesheet" href="resources/css/pickadate/default.css">
    <link rel="stylesheet" href="resources/css/pickadate/default.date.css">
    <link rel="stylesheet" href="resources/css/pickadate/default.time.css">
    <%@include file="header.html" %>

    <title>NottiTranquille</title>

</head>
<body>

<!-- NAVBAR
================================================== -->
<%@include file="navbar.jsp" %>

<br/><br/>

<!-- LOCATION DESCRIPTION
================================================== -->
<div class="container">
    <div class="jumbotron">
        <div class="page-header">
            <h2>${locationBean.name}</h2>
        </div>
        <p>${locationBean.description}</p>
    </div>
    <c:if test="${locationBean.services!=null and locationBean.services.size() > 0}">
        <div>
            <p>Elenco Servizi</p>
            <ul type=”Servizi”>
                <li>primo</li>
                <li>secondo</li>
                <li>terzo</li>
            </ul>
        </div>
    </c:if>
</div>

<!-- TENANT AND OTHER PEOPLE INFO
================================================== -->

<form action="Reservation.jsp" id="myform" method="post" class="form-horizontal">

    <div class="container">

        <h2>Personal Info</h2>
        <p>Please enter your info to complete reservation</p>


        <div class="row clearfix">
            <div class="col-md-6 column">
                <table class="table table-hove table-condensed" id="tab_logic">
                    <thead>
                    <tr>
                        <th class="text-center">
                            Firstname
                        </th>
                        <th class="text-center">
                            Surname
                        </th>
                    </tr>
                    </thead>

                    <tbody>

                    <div class="form-group">

                        <tr id='addr0'>
                            <td>
                                <input type="text" id="firstname0" name='firstname0' class='form-control input-md'
                                       required/>
                            </td>
                            <td>
                                <input type="text" id="surname0" name='surname0' class='form-control input-md'
                                       required/>
                            </td>
                        </tr>
                        <tr id='addr1'></tr>

                    </div>

                    </tbody>

                </table>
            </div>
        </div>
        <a id="add_row" class="btn btn-default pull-left">Add Row</a><a id='delete_row'
                                                                        class="btn btn-default">Delete
        Row</a>
    </div>


    <script>
        var i = 1;
        $("#add_row").click(function () {
            $('#addr' + i).html(
                    "<td><input name='firstname" + i + "' type='text'  class='form-control input-md' required /></td>" +
                    "<td><input  name='surname" + i + "' type='text'   class='form-control input-md' required /></td>");

            $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
            i++;
        });
        $("#delete_row").click(function () {
            if (i > 1) {
                $("#addr" + (i - 1)).html('');
                i--;
            }
        });


    </script>

    </br></br>

    <!-- CHOOSE DATE
    ================================================== -->

    <div class="container">
        <div class='col-sm-5'>


            <div class="form-group">
                <label class="col-xs-3 control-label">start date</label>
                <div class="col-xs-5">
                    <input type="text" class="form-control" name="startDate" id="startDate"/>
                </div>

            </div>

            <div class="form-group">

                <label class="col-xs-3 control-label">end date</label>
                <div class="col-xs-5">
                    <input type="text" class="form-control" name="endDate" id="endDate"/>
                </div>

            </div>

        </div>

        <script>
            var yesterday = new Date((new Date()).valueOf() - 1000 * 60 * 60 * 24);

            $(document).ready(function () {

                $('#startDate').pickadate({
                    weekdaysShort: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                    showMonthsShort: true,
                    format: 'dd-mm-yyyy',
                    formatSubmit: 'dd-mm-yyyy',
                    hiddenName: true,

                    disable: [
                        {from: [0, 0, 0], to: yesterday}
                    ],

                    enable: [
                        ${locationBean.enablesDate}
                    ]

                })

                $('#endDate').pickadate({
                    weekdaysShort: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                    showMonthsShort: true,
                    format: 'dd-mm-yyyy',
                    formatSubmit: 'dd-mm-yyyy',
                    hiddenName: true,

                    disable: [
                        {from: [0, 0, 0], to: yesterday}
                    ],

                    enable: [
                        ${locationBean.enablesDate}
                    ]

                })
            });
        </script>
    </div>


    <!-- BUTTON
    ================================================== -->

    <div class="container" align="right" align="bottom">
        <button type="button" class="btn btn-default">Back</button>
        <button type="submit" class="btn btn-primary" name="Reserve" id="Reserve">Conferma</button>
    </div>

</form>


<!-- IF NOT VALID INPUT
================================================== -->
<%

    if (request.getParameter("Reserve") != null) {
%>
<div class="alert alert-danger" role="alert">Immetti tutti i dati!</div>
<% } %>


</body>
</html>