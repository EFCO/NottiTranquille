<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 15/07/2016
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Use JSTL core lib in order to add some useful feature --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Use JSTL formatting lib in order to format price value --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- Use JSTL functions lib in order to have useful functions such as replacing --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%-- Use JSTL custom lib in order to add some useful feature (replaceParam for customize URL with parameter) --%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="locationBean" scope="session" class="it.ispw.efco.nottitranquille.view.LocationBean"/>
<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<html>
<head>
    <!-- HEADER -->
    <%@include file="header.html" %>

    <!-- Table sorter CSS -->
    <link href="<c:url value="resources/css/tablesorter.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="resources/css/structures.css" />" rel="stylesheet">

    <!-- Table sorter JS -->
    <script type="text/javascript" src="<c:url value="resources/js/jquery.tablesorter.js" />"></script>
    <%--TODO manage datatime with table sorter--%>

    <!-- Custom JS -->
    <script src="<c:url value="resources/js/structures.js" />"></script>

    <script>
        $(function () {
            $('.date').datetimepicker({
                format: 'DD-MM-YYYY',
            });
        });
    </script>

    <title>Manage Locations</title>
    <%
        Location currentLocation = null;
        if (request.getParameter("location-id") != null) {
            currentLocation = locationBean.getCurrentStructure().getLocations().get(Integer.parseInt(request.getParameter("location-id")));
            locationBean.setCurrentLocation(currentLocation);
            request.setAttribute("currentLocation", currentLocation);
        } else {
            currentLocation = locationBean.getCurrentLocation();
            request.setAttribute("currentLocation", currentLocation);
        }


    %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container under-navbar" style="margin-top: 50px">
    <%
        if (request.getParameter("modifyField") == null) {
    %>
    <form class="form-horizontal" role="form" action="manageSingleLocation.jsp" method="POST">
        <div class="row">
            <label class="col-lg-3 control-label">Description:</label>
            <c:out value="${currentLocation.description}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="description">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Type:</label>
            <c:out value="${currentLocation.type}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="type">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Number of Rooms:</label>
            <c:out value="${currentLocation.numberOfRooms}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfRooms">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Number of Bedrooms:</label>
            <c:out value="${currentLocation.numberOfBedrooms}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfBedrooms">Modifica
            </button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Numbero of Beds:</label>
            <c:out value="${currentLocation.numberOfBeds}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfBeds">Modifica
            </button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Number of Bathrooms:</label>
            <c:out value="${currentLocation.numberOfBathrooms}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfBathrooms">Modifica
            </button>
        </div>
    </form>
    <%
    } else if (request.getParameter("modifyField") != null && request.getParameter("fieldValue") != null) {
        String modify = request.getParameter("modifyField");
        String value = request.getParameter("fieldValue");
        int result = locationBean.modifyField(modify, value, currentLocation.getId());
    %>
    <form class="form-horizontal" role="form" action="manageSingleLocation.jsp" method="POST">
        <div class="row">
            <label class="col-lg-3 control-label">Description:</label>
            <c:out value="${currentLocation.description}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="description">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Type:</label>
            <c:out value="${currentLocation.type}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="type">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Number of Rooms:</label>
            <c:out value="${currentLocation.numberOfRooms}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfRooms">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Number of Bedrooms:</label>
            <c:out value="${currentLocation.numberOfBedrooms}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfBedrooms">Modifica
            </button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Numbero of Beds:</label>
            <c:out value="${currentLocation.numberOfBeds}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfBeds">Modifica
            </button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Number of Bathrooms:</label>
            <c:out value="${currentLocation.numberOfBathrooms}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="numberOfBathrooms">Modifica
            </button>
        </div>
    </form>
    <%
        if (result == 1) {
    %>
    <div class="alert alert-success">Campo modificato correttamente!</div>
    <%
    } else {
    %>
    <div class="alert alert-danger">Errore nella modifica del campo!</div>
    <%
        }
    } else {
    %>
    <form action="manageSingleLocation.jsp" method="POST">
        <div class="form-group row">
            <div class="col-md-4">
                <label for="value">Modifica <c:out value="${param['modifyField'].toUpperCase()}"/> </label>
                <input name="fieldValue" id="value" type="text" class="form-control"/>
                <button type="submit" class="btn btn-default" name="modifyField" value="${param['modifyField']}">
                    Modifica
                </button>
            </div>
        </div>
    </form>
    <%
        }
    %>

    <!-- Bootstrap core JavaScript -->
    <%@include file="bootstrap_core_js.html" %>
</body>
</html>