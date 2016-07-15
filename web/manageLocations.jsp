<%@ page import="it.ispw.efco.nottitranquille.model.Manager" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Structure" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: claudio
  Date: 7/10/16
  Time: 10:13 AM
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
<jsp:setProperty name="locationBean" property="*"/>

<jsp:useBean id="structureBean" scope="session" class="it.ispw.efco.nottitranquille.view.StructureBean"/>
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
        Structure currentStructure = null;
        List<Structure> managedStructures = null;

        try {
            managedStructures = ((Manager) loginBean.getUser().getRole("Manager")).getManagedStructures();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (request.getParameter("structure-id") != null) {
            currentStructure = managedStructures.get(Integer.parseInt(request.getParameter("structure-id")));
            locationBean.setCurrentStructure(currentStructure);
            request.setAttribute("currentStructure", currentStructure);
            request.setAttribute("locations", currentStructure.getLocations());
        } else {
            currentStructure = locationBean.getCurrentStructure();
            request.setAttribute("currentStructure", currentStructure);
            request.setAttribute("locations", currentStructure.getLocations());
        }

        if (request.getParameter("create") != null) {
            System.out.println("Sono entrato" + locationBean.toString());
            locationBean.validate();
        }
        if (request.getParameter("deleteWhitMerge") != null) {
            locationBean.delete(request.getParameter("id"), currentStructure.getLocations());
        }

    %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container under-navbar" style="margin-top: 50px">
    <%
        if (request.getParameter("modifyField") == null) {
    %>
    <form class="form-horizontal" role="form" action="manageLocations.jsp" method="POST">
        <div class="row">
            <label class="col-lg-3 control-label">Name:</label>
            <c:out value="${currentStructure.name}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="name">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Terms of Service:</label>
            <c:out value="${currentStructure.termsOfService}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="termsOfService">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Terms of Cancellation:</label>
            <c:out value="${currentStructure.termsOfCancellation}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="termsOfCancellation">Modifica
            </button>
        </div>
        <div class="form-group">
            <div class='input-group date'>
                <input type='text' name="checkIn" class="form-control" value="${currentStructure.checkIn}"
                       placeholder="Check In"/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
            </div>
        </div>
        <div class="form-group">
            <div class='input-group date'>
                <input type='text' name="checkOut" class="form-control" value="${currentStructure.checkOut}"
                       placeholder="Check Out"/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
            </div>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Address:</label>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.address}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.city}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.nation}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.postalcode}"/>
            </div>
            <button type="submit" class="btn btn-default" name="modifyField" value="address">Modifica</button>
        </div>
    </form>
    <%
    } else if (request.getParameter("modifyField") != null && request.getParameter("fieldValue") != null) {
        String modify = request.getParameter("modifyField");
        String[] value = request.getParameterValues("fieldValue");
        int result = structureBean.modifyField(modify, value, currentStructure.getId());
    %>
    <form class="form-horizontal" role="form" action="manageLocations.jsp" method="POST">
        <div class="row">
            <label class="col-lg-3 control-label">Name:</label>
            <c:out value="${currentStructure.name}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="name">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Terms of Service:</label>
            <c:out value="${currentStructure.termsOfService}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="termsOfService">Modifica</button>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Terms of Cancellation:</label>
            <c:out value="${currentStructure.termsOfCancellation}"/>
            <button type="submit" class="btn btn-default" name="modifyField" value="termsOfCancellation">Modifica
            </button>
        </div>
        <div class="form-group col-lg-6">
            <div class='input-group date'>
                <input type='text' name="checkIn" class="form-control" value="${currentStructure.checkIn}"
                       placeholder="Check In"/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
            </div>
        </div>
        <div class="form-group col-lg-6">
            <div class='input-group date'>
                <input type='text' name="checkOut" class="form-control" value="${currentStructure.checkOut}"
                       placeholder="Check Out"/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
            </div>
        </div>
        <div class="row">
            <label class="col-lg-3 control-label">Address:</label>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.address}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.city}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.nation}"/>
            </div>
            <div class="col-md-2">
                <c:out value="${currentStructure.address.postalcode}"/>
            </div>
            <button type="submit" class="btn btn-default" name="modifyField" value="address">Modifica</button>
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
        if (request.getParameter("modifyField").equals("address")) {
    %>
    <form action="manageLocations.jsp" method="POST">
        <div class="form-group row">
            <div class="col-md-4">
                <label for="address_address">Modifica Indirizzo</label>
                <input name="fieldValue" id="address_address" type="text" class="form-control"/>
                <label for="address_city">Modifica Citta</label>
                <input name="fieldValue" id="address_city" type="text" class="form-control"/>
                <label for="address_nation">Modifica Nazione</label>
                <input name="fieldValue" id="address_nation" type="text" class="form-control"/>
                <label for="address_postalcode">Modifica Codice Postale</label>
                <input name="fieldValue" id="address_postalcode" type="number" class="form-control"/>
                <button type="submit" class="btn btn-default" name="modifyField" value="${param['modify']}">Modifica
                </button>
            </div>
        </div>
    </form>

    <%
    } else {
    %>
    <form action="manageLocations.jsp" method="POST">
        <div class="form-group row">
            <div class="col-md-4">
                <label for="value">Modifica <c:out value="${param['modify'].toUpperCase()}"/> </label>
                <input name="fieldValue" id="value" type="text" class="form-control"/>
                <button type="submit" class="btn btn-default" name="modifyField" value="${param['modify']}">Modifica
                </button>
            </div>
        </div>
    </form>
    <%
            }
        }
    %>
    <div>
        <div class="left">
            <button class='btn btn-primary' data-toggle="modal" data-target="#createModal">Create new location</button>
        </div>

        <div class="second right">
            <div class="btn-group left">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="<custom:replaceParam name='limit' value='25'/>">25</a></li>
                    <li><a href="<custom:replaceParam name='limit' value='50'/>">50</a></li>
                    <li><a href="<custom:replaceParam name='limit' value='100'/>">100</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="<custom:replaceParam name='limit' value='all'/>">All</a></li>
                </ul>
            </div>
            <p id="perPage" class="right">per page</p>
        </div>

        <div class="right">
            <p id="show" class="left">
                Show:
            </p>

            <div class="btn-group right">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="<custom:replaceParam name='type' value='basePrices'/>">Base prices</a></li>
                    <li><a href="<custom:replaceParam name='type' value='discounts'/>">Discounts</a></li>
                    <li><a href="<custom:replaceParam name='type' value='fees'/>">Fees</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="<custom:replaceParam name='type' value='fixDiscounts'/>">Fix discounts</a></li>
                    <li><a href="<custom:replaceParam name='type' value='fixFees'/>">Fix fees</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="<custom:replaceParam name='type' value='percentageDiscounts'/>">Percentage
                        discounts</a></li>
                    <li><a href="<custom:replaceParam name='type' value='percentageFees'/>">Percentage fees</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="<custom:replaceParam name='type' value='allPrices'/>">All prices</a></li>
                </ul>
            </div>
        </div>
    </div>

    <table id="prices-table" class="tablesorter table table-bordered table-hover table-striped">
        <caption>Ini adalah data biodata anda</caption>
        <thead>
        <tr>
            <th>#</th>
            <th>Price</th>
            <th>Description</th>
            <th>Address</th>
            <th>Max Guest Number</th>
            <th>Number of Bathrooms</th>
            <th>Number of Bedrooms</th>
            <th>Number of Beds</th>
            <th>Number of Rooms</th>
            <th>Type</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${locations.size() > 0}">
                <c:forEach items="${locations}" var="location">
                    <tr>
                        <td>${location.id}</td>
                        <td>${fn:join(price['class'].simpleName.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"), ' ')}</td>
                        <td>${location.description}</td>
                        <td>${location.structure.address}</td>
                        <td>${location.maxGuestsNumber}</td>
                        <td>${location.numberOfBathrooms}</td>
                        <td>${location.numberOfBedrooms}</td>
                        <td>${location.numberOfBeds}</td>
                        <td>${location.numberOfRooms}</td>
                        <td>${location.type}</td>
                            <%--<td>${location.photos}</td>--%>
                        <td>
                            <form action="manageSingleLocation.jsp" method="POST">
                                <button class='updateLocation btn btn-warning btn-sm' name="location-id"
                                        value="${locations.indexOf(location)}">
                                    <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                                </button>
                            </form>
                            <button class='deleteLocation btn btn-danger btn-sm' role='button' data-toggle="modal"
                                    data-target="#deleteModal" data-id=${location.id}><span
                                    class='glyphicon glyphicon-trash' aria-hidden='true'></span></button>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7">There are no locations</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    !-- Pagination -->
    <div>
        <div class="left">
            <nav>
                <ul class="pagination">
                </ul>
            </nav>
        </div>
        <div id="items-counter" class="right">
        </div>
    </div>


    <!-- FOOTER -->
    <%@include file="footer.html" %>

</div>

<!-- Create Modal -->
<div class="modal fade in" id="createModal" tabindex="-1" role="dialog" aria-labelledby="Create">
    <div class="modal-dialog modal-dialog-prices" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="createModalLabel">Create Location</h4>
            </div>
            <div class="modal-body">
                <form action="manageLocations.jsp" id="newLocationForm" method="POST">
                    <div class="form-group col-md-12">
                        <label for="description" id="userlabel">Description :</label>
                        <input name="description" id="description" type="text" class="form-control"
                               placeholder="E' un posto veramente bello" value="E' un posto veramente bello" required>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="numberOfRooms">Number of Rooms:</label>
                        <input name="numberOfRooms" id="numberOfRooms" type="text" class="form-control"
                               placeholder="2" value="2" required>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="numberOfBathrooms">Number of Bathrooms:</label>
                        <input name="numberOfBathrooms" id="numberOfBathrooms" type="text" class="form-control"
                               placeholder="5" value="5" required>
                    </div>
                    <div class="form-group col-md-3 ">
                        <label for="maxGuestsNumber">Max Guest Number:</label>
                        <input name="maxGuestsNumber" id="maxGuestsNumber" type="text" class="form-control"
                               value="4">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="numberOfBeds">Number of Beds:</label>
                        <input name="numberOfBeds" id="numberOfBeds" type="text" class="form-control"
                               value="5">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="numberOfBedrooms">Number of Bedrooms</label>
                        <input name="numberOfBedrooms" id="numberOfBedrooms" type="text" class="form-control"
                               value="79">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" name="create" form="newLocationForm" class="btn btn-default btn-primary"
                        id="create"
                        value="create">
                    Create new Location
                </button>
            </div>
        </div>
    </div>
</div>
<!-- Delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="Delete">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="deleteModalLabel">Are you sure?</h4>
            </div>
            <div class="modal-body">
                <form id="delete-price-form" method="post" action="manageLocations.jsp">Note that after the confirmation
                    the
                    location will be lost.
                    <label>
                        <input name="id" id="location-id" hidden>
                    </label>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="delete-price-form" id="delete" name="delete"
                        value="delete">Delete
                </button>
            </div>
            <script>
                $(function () {
                    $(".deleteLocation").click(function () {
                        var locationID = $(".deleteLocation").attr("data-id");
                        $(".modal-body #location-id").val(locationID);
                    });
                })
            </script>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript -->
<%@include file="bootstrap_core_js.html" %>
</body>
</html>