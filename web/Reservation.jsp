<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<jsp:useBean id="reservationBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.ReservationBean"/>
<jsp:useBean id="location" scope="session"
             class="it.ispw.efco.nottitranquille.view.LocationBean"/>
<jsp:useBean id="Login" scope="session"
             class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<jsp:setProperty name="reservationBean" property="*"/>


<!-- Populate JavaBean if id matching a Location is correctly passed
================================================== -->
<%
    // Assuming the id of the location we want to see is in the URL
    // because a GET request is made.
    if (request.getParameter("id") != null) {
        location.populate(request.getParameter("id"));
        reservationBean.setUsername(Login.getUsername());
        reservationBean.setLocation(location);
    }

%>


<!-- On button pressed for Reservation confirm
================================================== -->

<%
    if (request.getParameter("Reserve") != null) {

        for (int i = 0; request.getParameter("firstname" + i) != null && request.getParameter("surname" + i) != null; i++)
            reservationBean.addBuyer(request.getParameter("firstname" + i), request.getParameter("surname" + i));

    }

    if (reservationBean.validate()) {
%>

<jsp:forward page="Payment.jsp">
    <jsp:param name="Pay" value="${reservationBean.id}"/>
</jsp:forward>

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

<%@include file="navbar.jsp" %>

<br/><br/>

<!-- LOCATION DESCRIPTION
================================================== -->
<div class="container">
    <div class="page-header">
        <h2>${location.name}</h2>
        <h4>${location.address}</h4>
    </div>
    <p>${location.description}</p>


    <form action="Reservation.jsp" id="myform" method="post" class="form-horizontal">


        <!-- IF NOT VALID INPUT
        ================================================== -->
        <%
            if (request.getParameter("Reserve") != null) {
        %>
        <div class="alert alert-danger" role="alert">Hai dimenticato di immettere qualche dato,
            oppure le date selezionate non sono piu' valide!
        </div>
        <% } %>


        <h3>Personal Info</h3>
        <p>Please enter your info to complete reservation</p>

        <div class="col-sm-9 col-md-6 col-lg-8" style="border-right: outset">

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
                        </div>

                        </tbody>

                    </table>
                </div>
            </div>
            <a id="add_row" class="btn btn-default pull-left">Add Roommate</a>
            <a id='delete_row' class="btn btn-default">Delete</a>


            <script>
                var i = 0;
                $("#add_row").click(function () {
                    $('#addr' + i).html(
                            "<td><input name='firstname" + i + "' type='text'  class='form-control input-md' required /></td>" +
                            "<td><input  name='surname" + i + "' type='text'   class='form-control input-md' required /></td>");

                    $('#tab_logic').append('<tr id="addr' + (i + 1) + '"></tr>');
                    i++;
                });
                $("#delete_row").click(function () {
                    if (i > 0) {
                        $("#addr" + (i - 1)).html('');
                        i--;
                    }
                });


            </script>

            </br></br>
        </div>
        <div class="col-sm-3 col-md-6 col-lg-4">

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
                                {from: [0, 0, 0], to: yesterday},
                                ${location.disableDate}
                            ],

                            enable: [
                                ${location.enablesDate}
                            ]

                        })

                        $('#endDate').pickadate({
                            weekdaysShort: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                            showMonthsShort: true,
                            format: 'dd-mm-yyyy',
                            formatSubmit: 'dd-mm-yyyy',
                            hiddenName: true,

                            disable: [
                                {from: [0, 0, 0], to: yesterday},
                                ${location.disableDate}
                            ],

                            enable: [
                                ${location.enablesDate}
                            ]

                        })
                    });
                </script>
            </div>

        </div>

        <!-- BUTTON
        ================================================== -->

        <div class="container" align="right">
            <form action="Reservation.jsp">
                <button type="submit" class="btn btn-primary" name="Reserve" id="Reserve">Conferma</button>
            </form>
        </div>

    </form>

    <c:if test="${reservationBean.location.reservationType == 'WithConfirm' }">
    <br>
    <div class="alert alert-warning" role="alert">This location is available only after a owner confirm! You will
        receive an email when the owner will display your request!
    </div>
    </c:if>


</body>
</html>