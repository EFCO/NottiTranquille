<%@ page import="it.ispw.efco.nottitranquille.view.TenantFormReservation" %><%--
  Created by IntelliJ IDEA.
  User: emanuele
  Date: 16/01/16
  Time: 16.12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="ReservationBean" scope="request"
             class="it.ispw.efco.nottitranquille.view.TenantFormReservation"/>

<jsp:setProperty name="ReservationBean" property="*"/>


<%--
<%
    if (request.getParameter("Reserve") != null) {
        if (TenantFormReservation.validate()) {
            %>
            <jsp:forward page="RiassuntoPrenotazione.jsp" />
            <%
        }
%>
--%>


<html>
<head>

    <!-- meta for image gallery
================================================== -->
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
<%@include file="navbar.html" %>

<br/><br/>

<!-- LOCATION DESCRIPTION
================================================== -->
<div class="container">
    <div class="jumbotron">
        <div class="page-header">
            <h2>Location Name</h2>
        </div>
        <p>Description</p>
        <p>... ... ... ...</p>
    </div>
    <div>
        <p>Elenco Servizi</p>
        <ul type=”Servizi”>
            <li>primo</li>
            <li>secondo</li>
            <li>terzo</li>
        </ul>
    </div>
</div>

<!-- TENANT AND OTHER PEOPLE INFO
================================================== -->

<div class="container">

    <h2>Personal Info</h2>
    <p>Please enter your info to complete reservation</p>

    <div class="container">
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
                    <tr id='addr0'>
                        <td>
                            <input type="text" name='Firstname0' placeholder='Firstname' class="form-control"/>
                        </td>
                        <td>
                            <input type="text" name='Surname0' placeholder='Surname' class="form-control"/>
                        </td>
                    </tr>
                    <tr id='addr1'></tr>
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
                    "<td><input name='Firstname" + i + "' type='text' placeholder='Firstname' class='form-control input-md'  /></td>" +
                    "<td><input  name='Surname" + i + "' type='text' placeholder='Surname'  class='form-control input-md'></td>");

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
</div>

</br></br>

<!-- CHOOSE DATE
================================================== -->

<div class="container">
        <div class='col-sm-5'>
            <form id="pickDateForm" method="post" class="form-horizontal">
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

                <%-- <div class="form-group">
                    <div class="col-xs-5 col-xs-offset-3">
                        <button type="submit" class="btn btn-default">Validate</button>
                    </div>
                </div>--%>

            </form>
        </div>

        <script>
            $(document).ready(function () {
                $('#startDate').pickadate({
                    format: 'mm/dd/yyyy',
                    formatSubmit: 'mm/dd/yyyy',
                    hiddenName: true,

                    disable: [
                        { from: [2016,0,14], to: [2016,0,27] }
                    ]

                });

                $('#startDate')
                        .pickadate('picker')
                        .on('render', function () {
                            // http://amsul.ca/pickadate.js/api.htm#events-callbacks
                            // Revalidate the date of birth field
                            $('#pickDateForm').formValidation('revalidateField', 'startDate');
                        });

                $('#endDate').pickadate({
                    format: 'mm/dd/yyyy',
                    formatSubmit: 'mm/dd/yyyy',
                    hiddenName: true
                });

                $('#endDate')
                        .pickadate('picker')
                        .on('render', function () {
                            // http://amsul.ca/pickadate.js/api.htm#events-callbacks
                            // Revalidate the date of birth field
                            $('#pickDateForm').formValidation('revalidateField', 'endDate');
                        });

                $('#pickDateForm').formValidation({
                    framework: 'bootstrap',
                    icon: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    excluded: ':disabled',
                    fields: {
                        startDate: {
                            validators: {
                                notEmpty: {
                                    message: 'The start date is required'
                                },
                                date: {
                                    format: 'MM/DD/YYYY',
                                    message: 'The date is not a valid date'
                                }
                            }
                        }
                    }
                });
            });
        </script>
</div>

<!-- BUTTON
================================================== -->

<div class="container" action="Reservation.jsp" align="right" align="bottom">
    <button type="button" class="btn btn-default">Back</button>
    <button type="button" type="submit" class="btn btn-primary" id="Reserve">Conferma prenotazione</button>
</div>


<!-- IF NOT VALID INPUT
================================================== -->
<%
    if (request.getParameter("Reserve") != null) { %>
<div class="alert alert-danger" role="alert">Sceglie una data valida!</div>
<% } %>


</body>
</html>