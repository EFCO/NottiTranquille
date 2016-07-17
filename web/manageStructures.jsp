<%@ page import="it.ispw.efco.nottitranquille.model.Manager" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Structure" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.StructureType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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

<jsp:useBean id="structureBean" scope="session" class="it.ispw.efco.nottitranquille.view.StructureBean"/>
<jsp:setProperty name="structureBean" property="*"/>

<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<c:set var="createModalHTML"
       value='<form id="create-price-form" method="post" action="structures.jsp"> <div class="form-group"> <label>Price type</label> <div id="price-type-div"> <div id="first-column-radio-price-type"> <div class="radio first-radio"> <label> <input type="radio" name="priceType" id="option-radio-base-price" value="basePrice" checked="checked"> Base Price </label> </div> </div> <div id="second-column-radio-price-type"> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-fix-discount" value="fixDiscount"> Fix Discount </label> </div> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-fix-fee" value="percentageDiscount"> Percentage Discount </label> </div> </div> <div id="third-column-radio-price-type"> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-percentage-discount" value="fixFee"> Fix Fee </label> </div> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-percentage-fee" value="percentageFee"> Percentage Fee </label> </div> </div> </div> </div> <div id="div-repeat" class="form-group"> <div id="div-repeat-select" class="form-group left"> <label>Repeat it</label> <select class="form-control" name="repetitionType" required> <option value="everyDay" selected="selected">Every day</option> <option value="everyWeek">Every week</option> <option value="everyMonth">Every month</option> <option value="everyYear">Every year</option> <option value="everyWeekend">Every weekend</option> <option value="everyWorkday">Every workday</option> <option value="everyNoWorkday">Every noworkday</option> </select> </div> <div id="div-times" class="form-group right"> <label>Times</label> <select class="form-control" name="times"> <option selected="selected">1</option> <option>2</option> <option>3</option> <option>4</option> <option>5</option> <option>6</option> <option>7</option> </select> </div> </div> <div class="form-group"> <label for="input-price">Price value</label> <div class="input-group"> <div id="addon-input-price" class="input-group-addon">€</div> <input type="number" step="any" min="0" class="form-control" name="value" id="input-price" placeholder="Price" required> </div> </div> <div class="form-group date"> <label for="input-start-date">Start</label> <div class="input-group date"> <input type="text" class="form-control" name="startDate" id="input-start-date" required><div class="input-group-addon"><i class="glyphicon glyphicon-th"></i></div> </div> </div> <div class="form-group"> <label for="input-comment">Comment</label> <div class="input-group"> <input type="text" class="form-control" name="comment" id="input-comment"> </div> </div> <div id="end-div" class="form-group"> <label>End</label> <div class="radio"> <label> <input type="radio" name="option-radio-end" id="option-radio-never" value="never" checked="checked">Never</label> <input type="text" name="endDate" value="31/12/9999" hidden/> </div> <div class="radio"> <label> <input type="radio" name="option-radio-end" id="option-radio-occurrences" value="occurrences"> <div class="input-group"> <input name="occurrences" id="input-occurrences" type="number" min="1" class="form-control" value="1"><span class="input-group-addon" id="sizing-addon1">occurrences</span> </div> </label> </div> <div class="radio"> <label> <input type="radio" name="option-radio-end" id="option-radio-end-date" value="endDate"> <div class="input-group date"> <input type="text" class="form-control" name="endDate" id="input-end-date"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span> </div> </label> </div> </div> <div class="form-group"> <label>Summary</label> <p id="summary"></p> </div> <label> <input name="id" hidden> </label> </form>'/>
<c:set var="structuretypes" value="<%=StructureType.values()%>"/>

<!DOCTYPE html>
<html lang="en">
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

    <title>Manage structures</title>

</head>
<%-- TODO controllare se è manager -->
<%-- All requests are of the form: structures.jsp?page=page&limit=limit --%>
<%

    List<Structure> structures = new ArrayList<>();
    try {
        structures = ((Manager) loginBean.getUser().getRole("Manager")).getManagedStructures();
    } catch (Exception e) {
        e.printStackTrace();
    }

    request.setAttribute("structures", structures);

    if (request.getParameter("create") != null || request.getParameter("update") != null) { // After CREATE or UPDATE
        try {
            if (request.getParameter("checkIn") != null && request.getParameter("checkOut") != null) {
                structureBean.setCheckIn(request.getParameter("checkIn"));
                structureBean.setCheckOut(request.getParameter("checkOut"));
            }
            structureBean.validate(loginBean.getUser());
            response.sendRedirect("manageStructures.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    if (request.getParameter("delete") != null) {
        structureBean.delete(structures, request.getParameter("id"));
        response.sendRedirect("manageStructures.jsp");
    }
%>
<body>
<!-- NAVBAR -->
<%@include file="navbar.jsp" %>

<!-- CONTAINER -->
<div class="container under-navbar" style="margin-top: 50px">
    <div>
        <div class="btn-group">
            <button class='btn btn-primary' data-toggle="modal" data-target="#createModal">Create new structure</button>
            <a class='btn btn-default' type="button" href="<c:url value="manageProfile.jsp"/>">Back</a>
        </div>
        <!-- Table with prices -->
        <table id="prices-table" class="tablesorter table table-bordered table-hover table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>Structure Type</th>
                <th>Name</th>
                <th>Number of locations</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${structures.size() > 0}">
                    <c:forEach items="${structures}" var="structure">
                        <tr>
                            <td>${structure.id}</td>
                            <td>${structure.type.text}</td>
                            <td>${structure.name}</td>
                            <td>${structure.locations.size()}</td>
                            <td>${structure.address}</td>
                            <td>
                                <form action="manageLocations.jsp" method="POST">
                                    <button class='updateStructure btn btn-warning btn-sm' name="structure-id"
                                            value="${structures.indexOf(structure)}">
                                        <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                                    </button>
                                </form>
                                <button class='deleteStructure btn btn-danger btn-sm' role='button' data-toggle="modal"
                                        data-target="#deleteModal" data-id=${structure.id}><span
                                        class='glyphicon glyphicon-trash' aria-hidden='true'></span></button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7">There are no structures</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- Create Modal -->
        <div class="modal fade in" id="createModal" tabindex="-1" role="dialog" aria-labelledby="Create">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                        <h4 class="modal-title" id="createModalLabel">Create Structure</h4>
                    </div>
                    <div class="modal-body">
                        <form action="manageStructures.jsp" name="newStructureForm" id="newStructureForm" method="POST">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="name" id="userlabel">Structure name:</label>
                                    <input name="name" id="name" type="text" class="form-control"
                                           placeholder="Villa bella" value="Villa bella" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="type">Structure type:</label>
                                    <select name="type" id="type" class="form-control">
                                        <c:forEach items="${structuretypes}" var="type">
                                            <option value="${type.name()}">${type.text}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="termsOfService">Terms of Services:</label>
                                <input name="termsOfService" id="termsOfService" type="text" class="form-control"
                                       placeholder="Non bisogna fare cose" value="Non bisogna fare cose" required>
                            </div>
                            <div class="form-group">
                                <label for="termsOfCancellation">Terms of Cancellation:</label>
                                <input name="termsOfCancellation" id="termsOfCancellation" type="text"
                                       class="form-control"
                                       placeholder="Se fate casino ciao" value="Se fate casino ciao" required>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="checkinpicker">Check In:</label>
                                    <div class='input-group date' id='checkinpicker'>
                                        <input type='text' name="checkIn" id="checkIn" class="form-control" required/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="checkoutpicker">Check Out:</label>
                                    <div class='input-group date' id='checkoutpicker'>
                                        <input type='text' name="checkOut" id="checkOut" class="form-control" required/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                                    </div>
                                </div>
                            </div>
                            <script>
                                $(function () {
                                    $('#checkinpicker').datetimepicker({
                                        format: 'DD-MM-YYYY',
                                    });
                                    $('#checkoutpicker').datetimepicker({
                                        format: 'DD-MM-YYYY',
                                    });
                                });
                            </script>
                            <div class="row">
                                <div class="col-md-3">
                                    <label for="address">Indirizzo:</label>
                                    <input name="address" id="address" type="text" class="form-control"
                                           placeholder="Piazza Ciao" value="Piazza Ciao">
                                </div>
                                <div class="col-md-3">
                                    <label for="nation">Nazione:</label>
                                    <input name="nation" id="nation" type="text" class="form-control"
                                           placeholder="Italia"
                                           value="Italia">
                                </div>
                                <div class="col-md-3">
                                    <label for="city">Città:</label>
                                    <input name="city" id="city" type="text" class="form-control" placeholder="Roma"
                                           value="Roma">
                                </div>
                                <div class="col-md-3">
                                    <label for="postalcode">Codice postale:</label>
                                    <input name="postalcode" id="postalcode" type="text" class="form-control"
                                           placeholder="00039" value="00039">
                                </div>
                            </div>
                            <div class="row" style="margin-top: 6px">
                                <div class="form-group col-md-12">
                                    <label for="owner">Sei anche il possessore della struttura?</label>
                                    <input type="checkbox" name="owner" id="owner" checked>
                                </div>
                            </div>
                            <script>
                                $(function () {
                                    $("#owner").click(function () {
                                        if (!$('#owner').is(':checked')) {
                                            $('#owner').attr("checked", false);
                                            $("#managerisownerdiv").show('slide');
                                            $('#managerisownerdiv :input').slice(0, 3).prop("required", true);
                                        } else {
                                            $('#owner').attr("checked", true);
                                            $("#managerisownerdiv").hide('slide');
                                            $('#managerisownerdiv :input').slice(0, 3).prop("required", false);
                                        }
                                    })
                                })
                            </script>
                            <div class="form-group" id="managerisownerdiv" style="display: none">
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label for="ownerFirstName">Nome possessore struttura:</label>
                                        <input name="ownerFirstName" id="ownerFirstName" type="text"
                                               class="form-control"
                                               value="Federico">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="ownerLastName">Cognome possessore struttura:</label>
                                        <input name="ownerLastName" id="ownerLastName" type="text" class="form-control"
                                               value="Vagnoni">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label for="ownerEmail">Email possessore struttura:</label>
                                        <input name="ownerEmail" id="ownerEmail" type="email" class="form-control"
                                               value="fede93.vagnoni@gmail.com">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="sameaddress">Vive all'interno della struttura?</label>
                                    <input type="checkbox" name="sameaddress" id="sameaddress" checked>
                                </div>
                                <script>
                                    $(function () {
                                        $("#sameaddress").click(function () {
                                            if (!$('#sameaddress').is(':checked')) {
                                                $('#sameaddress').attr("checked", false);
                                                $("#owneraddressdiv").show('slide');
                                                $('#owneraddressdiv :input').each(function () {
                                                    $(this).prop("required", true);
                                                })
                                            } else {
                                                $('#sameaddress').attr("checked", true);
                                                $("#owneraddressdiv").hide('slide');
                                                $('#owneraddressdiv :input').each(function () {
                                                    $(this).prop("required", false);
                                                })
                                            }
                                        })
                                    })
                                </script>
                                <div class="row" id="owneraddressdiv" style="display: none">
                                    <div class="form-group col-md-3">
                                        <label for="ownerAddress">Indirizzo:</label>
                                        <input name="ownerAddress" id="ownerAddress" type="text" class="form-control"
                                               placeholder="Piazza Ciao" value="Piazza Ciao">
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="ownerNation">Nazione:</label>
                                        <input name="ownerNation" id="ownerNation" type="text" class="form-control"
                                               placeholder="Italia"
                                               value="Italia">
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="ownerCity">Città:</label>
                                        <input name="ownerCity" id="ownerCity" type="text" class="form-control"
                                               placeholder="Roma"
                                               value="Roma">
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="ownerPostalcode">Codice postale:</label>
                                        <input name="ownerPostalcode" id="ownerPostalcode" type="text"
                                               class="form-control"
                                               placeholder="00039" value="00039">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" form="newStructureForm" name="create" class="btn btn-default btn-primary"
                                id="create"
                                value="create">
                            Create new Structure
                        </button>
                    </div>

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
                    <form id="delete-structure-form" method="POST" action="manageStructures.jsp">Note that after the
                        confirmation
                        the
                        structure will be lost.
                        <label>
                            <input name="id" id="structure-id" hidden>
                        </label>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary" form="delete-structure-form" id="delete" name="delete"
                            value="delete">Delete
                    </button>
                </div>
                <script>
                    $(document).on("click", ".deleteStructure", function () {
                        var structureID = $(this).data('id');
                        $(".modal-body #structure-id").val(structureID);
                    });
                </script>
            </div>
        </div>
    </div>
    <!-- FOOTER -->
    <%@include file="footer.html" %>
    <!-- Bootstrap core JavaScript -->
    <%@include file="bootstrap_core_js.html" %>
</body>
</html>