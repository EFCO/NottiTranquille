<%@ page import="it.ispw.efco.nottitranquille.model.Manager" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Structure" %>
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
            structureBean.validate(loginBean.getUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    if (request.getParameter("delete") != null) {
        structureBean.delete(structures, request.getParameter("id"));
    }
%>
<body>
<!-- NAVBAR -->
<%@include file="navbar.jsp" %>

<!-- CONTAINER -->
<div class="container under-navbar" style="margin-top: 50px">
    <div>
        <div class="left">
            <button class='btn btn-primary' data-toggle="modal" data-target="#createModal">Create new structure</button>
        </div>

    <!-- Table with prices -->
    <table id="prices-table" class="tablesorter table table-bordered table-hover table-striped">
        <%--<caption>Ini adalah data biodata anda</caption>--%>
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
                        <td>${fn:join(price['class'].simpleName.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"), ' ')}</td>
                        <td>${structure.name}</td>
                        <td>${structure.numberOfLocations}</td>
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

    <!-- Pagination -->
    <div>
        <div class="left">
            <nav>
                <ul class="pagination">
                    <%--<c:if test="${pageNumber != 1}">
                        <li><a href='<custom:replaceParam name='page' value='1'/>'
                               title='Go to the first page.'>&laquo;</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${maxPages.intValue()}" varStatus="pageLoop">
                        <c:choose>
                            <c:when test="${pageNumber eq pageLoop.count}">
                                <li class='active'><a href='#'>${pageLoop.count}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href='<custom:replaceParam name='page' value='${(pageLoop.count).toString()}'/>'>${pageLoop.count}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pageNumber != maxPages}">
                        <li><a href='<custom:replaceParam name='page' value='${maxPages}'/>'
                               title='Go to the last page.'>&raquo;</a></li>
                    </c:if>--%>
                </ul>
            </nav>
        </div>
        <div id="items-counter" class="right">
            <%--<p>
                Items <b>
                <c:choose>
                    <c:when test="${counts == 0}">
                        0
                    </c:when>
                    <c:otherwise>
                        ${startPosition + 1}
                    </c:otherwise>
                </c:choose>
            </b> to <b>
                <c:choose>
                    <c:when test="${limit eq -1}">
                        ${counts}
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${counts < (limit * pageNumber)}">
                                ${counts}
                            </c:when>
                            <c:otherwise>
                                ${limit * pageNumber}
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </b> of <b>${counts}</b>.
            </p>--%>
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
                <h4 class="modal-title" id="createModalLabel">Create Structure</h4>
            </div>
            <div class="modal-body">
                <form action="manageStructures.jsp" name="newStructureForm" method="POST">
                    <div class="form-group">
                        <label for="name" id="userlabel">Structure name:</label>
                        <input name="name" id="name" type="text" class="form-control"
                               placeholder="Villa bella" value="Villa bella" required>
                    </div>
                    <div class="form-group">
                        <label for="termsOfService">Terms of Services:</label>
                        <input name="termsOfService" id="termsOfService" type="text" class="form-control"
                               placeholder="Non bisogna fare cose" value="Non bisogna fare cose" required>
                    </div>
                    <div class="form-group">
                        <label for="termsOfCancellation">Terms of Cancellation:</label>
                        <input name="termsOfCancellation" id="termsOfCancellation" type="text" class="form-control"
                               placeholder="Se fate casino ciao" value="Se fate casino ciao" required>
                    </div>
                    <div class="form-group">
                        <div class='input-group date' id='checkinpicker'>
                            <input type='text' name="checkIn" id="checkIn" class="form-control" placeholder="Check In"
                                   required/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='input-group date' id='checkoutpicker'>
                            <input type='text' name="checkOut" id="checkOut" class="form-control"
                                   placeholder="Check Out" required/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
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
                    <div class="form-group">
                        <div class="col-md-3">
                            <label for="address">Indirizzo:</label>
                            <input name="address" id="address" type="text" class="form-control"
                                   placeholder="Piazza Ciao" value="Piazza Ciao">
                        </div>
                        <div class="col-md-3">
                            <label for="nation">Nazione:</label>
                            <input name="nation" id="nation" type="text" class="form-control" placeholder="Italia"
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
                    <div class="form-group">
                        <label for="owner">Sei anche il possessore della struttura?</label>
                        <input type="checkbox" name="owner" id="owner" checked>
                    </div>
                    <script>
                        $(function () {
                            $("#owner").click(function () {
                                if (!$('#owner').is(':checked')) {
                                    $("#managerisownerdiv").show('slide');
                                    $('#managerisownerdiv :input').each(function () {
                                        $(this).prop("required", true);
                                    })
                                } else {
                                    $("#managerisownerdiv").hide('slide');
                                    $('#managerisownerdiv :input').each(function () {
                                        $(this).prop("required", false);
                                    })
                                }
                            })
                        })
                    </script>
                    <div class="form-group" id="managerisownerdiv" style="display: none">
                        <div class="col-md-6">
                            <label for="ownerFirstName">Nome possessore struttura:</label>
                            <input name="ownerFirstName" id="ownerFirstName" type="text" class="form-control"
                                   value="Federico">
                        </div>
                        <div class="col-md-6">
                            <label for="ownerLastName">Cognome possessore struttura:</label>
                            <input name="ownerLastName" id="ownerLastName" type="text" class="form-control"
                                   value="Vagnoni">
                        </div>
                        <div class="col-md-12">
                            <label for="ownerEmail">Email possessore struttura:</label>
                            <input name="ownerEmail" id="ownerEmail" type="email" class="form-control"
                                   value="fede93.vagnoni@gmail.com">
                        </div>
                        <div class="form-group">
                            <label for="sameaddress">Vive all'interno della struttura?</label>
                            <input type="checkbox" name="sameaddress" id="sameaddress" checked>
                        </div>
                        <script>
                            $(function () {
                                $("#sameaddress").click(function () {
                                    if (!$('#sameaddress').is(':checked')) {
                                        $("#owneraddressdiv").show('slide');
                                        $('#owneraddressdiv :input').each(function () {
                                            $(this).prop("required", true);
                                        })
                                    } else {
                                        $("#owneraddressdiv").hide('slide');
                                        $('#owneraddressdiv :input').each(function () {
                                            $(this).prop("required", false);
                                        })
                                    }
                                })
                            })
                        </script>
                        <div class="form-group" id="owneraddressdiv" style="display: none">
                            <div class="col-md-3">
                                <label for="ownerAddress">Indirizzo:</label>
                                <input name="ownerAddress" id="ownerAddress" type="text" class="form-control"
                                       placeholder="Piazza Ciao" value="Piazza Ciao">
                            </div>
                            <div class="col-md-3">
                                <label for="ownerNation">Nazione:</label>
                                <input name="ownerNation" id="ownerNation" type="text" class="form-control"
                                       placeholder="Italia"
                                       value="Italia">
                            </div>
                            <div class="col-md-3">
                                <label for="ownerCity">Città:</label>
                                <input name="ownerCity" id="ownerCity" type="text" class="form-control"
                                       placeholder="Roma"
                                       value="Roma">
                            </div>
                            <div class="col-md-3">
                                <label for="ownerPostalcode">Codice postale:</label>
                                <input name="ownerPostalcode" id="ownerPostalcode" type="text" class="form-control"
                                       placeholder="00039" value="00039">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" name="create" class="btn btn-default btn-primary" id="create"
                                value="create">
                            Create new Structure
                        </button>
                    </div>
                </form>
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
                <form id="delete-structure-form" method="post" action="manageStructures.jsp">Note that after the
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
                $(function () {
                    $(".deleteStructure").click(function () {
                        var structureID = $(".deleteStructure").attr("data-id");
                        $(".modal-body #structure-id").val(structureID);
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