<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.Date" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.Day" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.ispw.efco.nottitranquille.controller.ManagePrices" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%@ page import="it.ispw.efco.nottitranquille.model.dao.LocationDao" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Address" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Structure" %>
<%@ page import="org.joda.time.Interval" %>
<%@ page import="org.joda.time.format.DateTimeFormatter" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ page import="org.joda.time.format.DateTimeFormat" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.LocationType" %>
<%@ page import="it.ispw.efco.nottitranquille.model.dao.StructureDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<jsp:useBean id="priceBean" class="it.ispw.efco.nottitranquille.view.PriceBean"/>
<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>
<jsp:setProperty name="priceBean" property="*"/>

<c:set var="today" value="<%=new Date()%>"/>
<c:set var="createModalHTML"
value='<form id="create-price-form" method="post" action="prices.jsp"> <div class="form-group"> <label>Price type</label> <div id="price-type-div"> <div id="first-column-radio-price-type"> <div class="radio first-radio"> <label> <input type="radio" name="priceType" id="option-radio-base-price" value="basePrice" checked="checked"> Base Price </label> </div> </div> <div id="second-column-radio-price-type"> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-fix-discount" value="fixDiscount"> Fix Discount </label> </div> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-fix-fee" value="percentageDiscount"> Percentage Discount </label> </div> </div> <div id="third-column-radio-price-type"> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-percentage-discount" value="fixFee"> Fix Fee </label> </div> <div class="radio"> <label> <input type="radio" name="priceType" id="option-radio-percentage-fee" value="percentageFee"> Percentage Fee </label> </div> </div> </div> </div> <div id="div-repeat" class="form-group"> <div id="div-repeat-select" class="form-group left"> <label>Repeat it</label> <select class="form-control" name="repetitionType" required> <option value="everyDay" selected="selected">Every day</option> <option value="everyWeek">Every week</option> <option value="everyMonth">Every month</option> <option value="everyYear">Every year</option> <option value="everyWeekend">Every weekend</option> <option value="everyWorkday">Every workday</option> <option value="everyNoWorkday">Every noworkday</option> </select> </div> <div id="div-times" class="form-group right"> <label>Times</label> <select class="form-control" name="times"> <option selected="selected">1</option> <option>2</option> <option>3</option> <option>4</option> <option>5</option> <option>6</option> <option>7</option> </select> </div> </div> <div class="form-group"> <label for="input-price">Price value</label> <div class="input-group"> <div id="addon-input-price" class="input-group-addon">€</div> <input type="number" step="any" min="0" class="form-control" name="value" id="input-price" placeholder="Price" required> </div> </div> <div class="form-group date"> <label for="input-start-date">Start</label> <div class="input-group date"> <input type="text" class="form-control" name="startDate" id="input-start-date" required><div class="input-group-addon"><i class="glyphicon glyphicon-th"></i></div> </div> </div> <div class="form-group"> <label for="input-comment">Comment</label> <div class="input-group"> <input type="text" class="form-control" name="comment" id="input-comment"> </div> </div> <div id="end-div" class="form-group"> <label>End</label> <div class="radio"> <label> <input type="radio" name="option-radio-end" id="option-radio-never" value="never" checked="checked">Never</label> </div> <div class="radio"> <label> <input type="radio" name="option-radio-end" id="option-radio-occurrences" value="occurrences"> <div class="input-group"> <input name="occurrences" id="input-occurrences" type="number" min="1" class="form-control" value="1"><span class="input-group-addon" id="sizing-addon1">occurrences</span> </div> </label> </div> <div class="radio"> <label> <input type="radio" name="option-radio-end" id="option-radio-end-date" value="endDate"> <div class="input-group date"> <input type="text" class="form-control" id="input-end-date"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span> </div> </label> </div> </div> <div class="form-group"> <label>Summary</label> <p id="summary"></p> </div> <label> <input name="id" hidden> </label> </form>'/>
<!DOCTYPE html>
<html lang="en">
<head>

    <!-- HEADER -->
    <%@include file="header.html" %>

    <!-- Table sorter CSS -->
    <link href="<c:url value="resources/css/tablesorter.css" />" rel="stylesheet">

    <!-- Data picker CSS -->
    <link href="<c:url value="resources/css/bootstrap-datepicker3.min.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="resources/css/prices.css" />" rel="stylesheet">

    <!-- Table sorter JS -->
    <script type="text/javascript" src="<c:url value="resources/js/jquery.tablesorter.js" />"></script>
    <%--TODO manage datatime with table sorter--%>

    <!-- Data picker JS -->
    <script type="text/javascript" src="<c:url value="resources/js/bootstrap-datepicker.min.js" />"></script>

    <script>
        var maxRepetitionDay = '${priceBean.MAX_REPETITION_DAY}';
        var maxRepetitionWeek = '${priceBean.MAX_REPETITION_WEEK}';
        var maxRepetitionMonth = '${priceBean.MAX_REPETITION_MONTH}';
        var maxRepetitionYear = '${priceBean.MAX_REPETITION_YEAR}';
        var createModalHTML = '${createModalHTML}';
    </script>

    <!-- Custom JS -->
    <script src="<c:url value="resources/js/prices.js" />"></script>

    <title>Notti Tranquille</title>

</head>

<%-- TODO USE CONTROLLER WITH LOCATION!!! -->
<%-- All requests are of the form: prices.jsp?locationId=id&type=type&page=page&limit=limit --%>
<%
/*    Structure s = StructureDao.retrieveStructures().get(0);
    List<Interval> booking = new ArrayList<Interval>();
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
    DateTime start = DateTime.parse("01-01-2016", dateTimeFormatter);
    DateTime end = DateTime.parse("30-12-2016", dateTimeFormatter);
    booking.add(new Interval(start,end));
    Location loc = new Location(booking,s,5, LocationType.Hotel);

    LocationDao.store(loc);*/

    //TODO Get location from bean
    Location location = LocationDao.retrieveLocations().get(0);
    request.setAttribute("locationId", location.getId());

    if (request.getParameter("create") != null || request.getParameter("update") != null) { // After CREATE or UPDATE

        // Transforms day in Day enumeration
        String daysWeek[] = request.getParameterValues("daysWeek");
        if (daysWeek != null) {

            List<Day> days = new ArrayList<Day>();

            for (String day : daysWeek) {
                if (day.equals("MONDAY")) {
                    days.add(Day.MONDAY);
                }
                if (day.equals("TUESDAY")) {
                    days.add(Day.TUESDAY);
                }
                if (day.equals("WEDNESDAY")) {
                    days.add(Day.WEDNESDAY);
                }
                if (day.equals("THURSDAY")) {
                    days.add(Day.THURSDAY);
                }
                if (day.equals("FRIDAY")) {
                    days.add(Day.FRIDAY);
                }
                if (day.equals("SATURDAY")) {
                    days.add(Day.SATURDAY);
                }
                if (day.equals("SUNDAY")) {
                    days.add(Day.SUNDAY);
                }
            }
            priceBean.setDays(days);
        } else {
            priceBean.setDays(new ArrayList<Day>());
        }

        // Validates data
        try {
            if (!priceBean.validate()) {
                for (Field field : priceBean.getErrorFields()) {
                    field.setAccessible(true);
                    try {
                        request.setAttribute("alertType", "success");
                        request.setAttribute("alertMessage", String.format("FieldValue: %s Message: %s", field.get(priceBean), priceBean.getErrorMessage(field)));
                        System.out.println(String.format("FieldValue: %s Message: %s", field.get(priceBean), priceBean.getErrorMessage(field)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            request.setAttribute("alertType", "success");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (request.getParameter("create") != null) {   // After create POST
            try {
                ManagePrices.addPrice(location, priceBean);
                response.setHeader("Refresh", "2;url=prices.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("update") != null) {   // After update POST
            request.setAttribute("alertType", "info");
            request.setAttribute("alertMessage", "The price was updated!");
            ManagePrices.updatePrice(location, priceBean);
            response.setHeader("Refresh", "2;url=prices.jsp");
        }

    } else if (request.getParameter("delete") != null) {    // After delete POST
        request.setAttribute("alertType", "info");
        request.setAttribute("alertMessage", "The price was deleted!");
        ManagePrices.deletePrice(location, priceBean);
        response.setHeader("Refresh", "2;url=prices.jsp");
    }

    // Gets pageNumber from request
    Integer pageNumber;
    try {
        pageNumber = Integer.parseInt(request.getParameter("page"));
        request.setAttribute("pageNumber", pageNumber);
    } catch (NumberFormatException exception) {
        // If is not provided it will be set 1 for default
        request.setAttribute("pageNumber", 1);
        pageNumber = Integer.parseInt(String.valueOf(request.getAttribute("pageNumber")));
    }

    // Gets limit from request
    Integer limit;
    try {
        limit = Integer.parseInt(request.getParameter("limit"));
        request.setAttribute("limit", limit);
    } catch (NumberFormatException exception) {
        if (request.getParameter("limit") == null) {
            // If is not provided it will be set 25 for default
            request.setAttribute("limit", 25);
            limit = Integer.parseInt(String.valueOf(request.getAttribute("limit")));
        } else {
            // Otherwise it will be set to -1 (all)
            request.setAttribute("limit", -1);
            limit = Integer.parseInt(String.valueOf(request.getAttribute("limit")));
        }
    }

    // Gets type from request
    String type = request.getParameter("type");
    if (type == null) {
        // If is not provided it will be set allPrices for default
        request.setAttribute("type", "allPrices");
    } else {
        request.setAttribute("type", request.getParameter("type"));
    }
    type = String.valueOf(request.getAttribute("type"));

    // Retrieves the counts of result for type and the list of the price of certain kind
    if (type.equals("allPrices")) {
        request.setAttribute("counts", ManagePrices.countAllPrices(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves prices
        request.setAttribute("prices", ManagePrices.fetchPrices(location, pageNumber, limit));
    } else if (type.equals("basePrices")) {
        request.setAttribute("counts", ManagePrices.countAllBasePrices(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves basePrices
        request.setAttribute("prices", ManagePrices.fetchBasePrices(location, pageNumber, limit));
    } else if (type.equals("discounts")) {
        request.setAttribute("counts", ManagePrices.countAllDiscounts(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves discounts
        request.setAttribute("prices", ManagePrices.fetchDiscounts(location, pageNumber, limit));
    } else if (type.equals("fees")) {
        request.setAttribute("counts", ManagePrices.countAllFees(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves fees
        request.setAttribute("prices", ManagePrices.fetchFees(location, pageNumber, limit));
    } else if (type.equals("fixDiscounts")) {
        request.setAttribute("counts", ManagePrices.countAllFixDiscounts(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves fixDiscounts
        request.setAttribute("prices", ManagePrices.fetchFixDiscounts(location, pageNumber, limit));
    } else if (type.equals("fixFees")) {
        request.setAttribute("counts", ManagePrices.countAllFixFees(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves fixFees
        request.setAttribute("prices", ManagePrices.fetchFixFees(location, pageNumber, limit));
    } else if (type.equals("percentageDiscounts")) {
        request.setAttribute("counts", ManagePrices.countAllPercentageDiscounts(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves percentageDiscounts
        request.setAttribute("prices", ManagePrices.fetchPercentageDiscounts(location, pageNumber, limit));
    } else if (type.equals("percentageFees")) {
        request.setAttribute("counts", ManagePrices.countAllPercentageFees(location));
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = (Integer) request.getAttribute("counts");
        }
        // Retrieves percentageFees
        request.setAttribute("prices", ManagePrices.fetchPercentageFees(location, pageNumber, limit));
    } else {
        //TODO error
    }

    // If limit is different from 0
    if (limit != 0) {
        // Calculates the max pages
        int maxPages = (Integer) request.getAttribute("counts") / limit;

        if (maxPages == 0) {
            request.setAttribute("maxPages", 1);
        } else {
            request.setAttribute("maxPages", maxPages);
        }
    } else {
        // Otherwise it will be set maxPages to 1
        request.setAttribute("maxPages", 1);
    }
%>
<body>
<!-- NAVBAR -->
<%@include file="navbar.jsp" %>

<!-- CONTAINER -->
<div class="container under-navbar">

    <c:choose>
        <c:when test="${alertType.equals('success')}">
            <div class="alert alert-success">
                <strong>Success!</strong>
            </div>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${alertType.equals('error')}">
                    <div class="alert alert-danger">
                        <strong>Error!</strong> ${alertMessage}
                    </div>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${alertType.equals('info')}">
                            <div class="alert alert-info">
                                <strong>Info!</strong> ${alertMessage}
                            </div>
                        </c:when>
                        <c:otherwise>
                            <%--Nothing--%>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <div>
        <div class="left">
            <button class='btn btn-primary' data-toggle="modal" data-target="#createModal">Create new price</button>
        </div>

        <div class="second right">
            <div class="btn-group left">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <c:choose>
                        <c:when test="${limit eq -1}">
                            All
                        </c:when>
                        <c:otherwise>
                            ${limit}
                        </c:otherwise>
                    </c:choose>
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
                    <c:choose>
                        <c:when test="${type.equals('allPrices')}">
                            All prices
                        </c:when>
                        <c:when test="${type.equals('basePrices')}">
                            Base prices
                        </c:when>
                        <c:when test="${type.equals('discounts')}">
                            Discounts
                        </c:when>
                        <c:when test="${type.equals('fees')}">
                            Fees
                        </c:when>
                        <c:when test="${type.equals('fixDiscounts')}">
                            Fix discounts
                        </c:when>
                        <c:when test="${type.equals('fixFees')}">
                            Fix fees
                        </c:when>
                        <c:when test="${type.equals('percentageDiscounts')}">
                            Percentage discounts
                        </c:when>
                        <c:when test="${type.equals('percentageFees')}">
                            Percentage fees
                        </c:when>
                    </c:choose> <span class="caret"></span>
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

    <!-- Table with prices -->
    <table id="prices-table" class="tablesorter table table-bordered table-hover table-striped">
        <caption>You are changing the location with id <strong>${locationId}</strong></caption>
        <thead>
        <tr>
            <th>#</th>
            <th>Price Type</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Value</th>
            <th>Comment</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${prices.size() > 0}">
                <c:forEach items="${prices}" var="price">
                    <tr>
                        <td>${price.id}</td>
                        <td>${fn:join(price['class'].simpleName.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"), ' ')}</td>
                        <td><joda:format value="${price.startDate}" locale="en_US" style="SM"
                                         pattern="dd MMM, yyyy"/></td>
                        <td>
                            <c:choose>
                            <c:when test="${price.endDate eq '9999-12-31T00:00:00.000+01:00'}">
                                ---
                            </c:when>
                            <c:otherwise>
                            <joda:format value="${price.endDate}" locale="en_US" style="SM"
                                         pattern="dd MMM, yyyy"/></td>
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${fn:contains(price['class'].simpleName, 'Percentage')}">
                                <td><fmt:formatNumber value="${price.value / 100}" type="percent"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><fmt:setLocale value="it_IT"/><fmt:formatNumber value="${price.value}"
                                                                                    type="currency"/></td>
                            </c:otherwise>
                        </c:choose>
                        <td>${price.comment}</td>
                        <td>
                            <button class='updatePrice btn btn-warning btn-sm' data-toggle="modal"
                                    data-target="#createModal"
                                    data-id=${price.id} data-pricetype=${price['class'].simpleName}
                                    data-comment="${price.comment}" data-startdate=
                                        <joda:format value="${price.startDate}" locale="en_US" style="SM"
                                                     pattern="dd/MM/yyyy"/> data-endDate=<joda:format
                                    value="${price.endDate}" locale="en_US" style="SM" pattern="dd/MM/yyyy"/>
                                    data-occurrences=${price.occurrences} data-repetitiontype=${price.repetitionType}
                                    data-times=${price.times} data-value=${price.value} data-days="${price.days}"><span
                                    class='glyphicon glyphicon-pencil' aria-hidden='true'></span></button>
                            <button class='deletePrice btn btn-danger btn-sm' role='button' data-toggle="modal"
                                    data-target="#deleteModal" data-id=${price.id}><span
                                    class='glyphicon glyphicon-trash' aria-hidden='true'></span></button>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7">There are no prices</td>
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
                    <c:if test="${pageNumber != 1}">
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
                    </c:if>
                </ul>
            </nav>
        </div>
        <div id="items-counter" class="right">
            <p>
                Items <b>
                <c:choose>
                    <c:when test="${counts == 0}">
                        0
                    </c:when>
                    <c:otherwise>
                        ${(pageNumber - 1) * (limit) + 1}
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
            </p>
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
                <h4 class="modal-title" id="createModalLabel">Create price</h4>
            </div>

            <div class="modal-body">
                ${createModalHTML}
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" form="create-price-form" id="create" name="create"
                        value="create">Create price
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
                <form id="delete-price-form" method="post" action="prices.jsp">Note that after the confirmation the price will be lost.
                    <label>
                        <input name="id" id="price-id" hidden>
                    </label>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="delete-price-form" id="delete" name="delete"
                        value="delete">Delete
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript -->
<%@include file="bootstrap_core_js.html" %>
</body>
</html>