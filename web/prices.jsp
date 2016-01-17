<%@ page import="it.ispw.efco.nottitranquille.model.dao.PriceDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Use JSTL core lib in order to add some useful feature --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Use JSTL formatting lib in order to format price value --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%-- Use JSTL custom lib in order to add some useful feature (replaceParam for customize URL with parameter) --%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

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

    <!-- Custom JS -->
    <script src="<c:url value="resources/js/prices.js" />"></script>

    <title>Notti Tranquille</title>

</head>

<%-- All requests are of the form: prices.jsp?locationId=id&type=type&page=page&limit=limit --%>
<%
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

    // Calculates the offset for the query (the start value)
    Integer startPosition = (pageNumber - 1) * (limit);
    request.setAttribute("startPosition", startPosition);

    // Retrieves the counts of result for type and the list of the price of certain kind
    if (type.equals("allPrices")) {
        request.setAttribute("counts", PriceDao.countAllPrices());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves prices
        request.setAttribute("prices", PriceDao.retrievePrices(startPosition, limit));
    } else if (type.equals("basePrices")) {
        request.setAttribute("counts", PriceDao.countAllBasePrices());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves basePrices
        request.setAttribute("prices", PriceDao.retrieveBasePrices(startPosition, limit));
    } else if (type.equals("discounts")) {
        request.setAttribute("counts", PriceDao.countAllDiscounts());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves discounts
        request.setAttribute("prices", PriceDao.retrieveDiscounts(startPosition, limit));
    } else if (type.equals("fees")) {
        request.setAttribute("counts", PriceDao.countAllFees());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves fees
        request.setAttribute("prices", PriceDao.retrieveFees(startPosition, limit));
    } else if (type.equals("fixDiscounts")) {
        request.setAttribute("counts", PriceDao.countAllFixDiscounts());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves fixDiscounts
        request.setAttribute("prices", PriceDao.retrieveFixDiscounts(startPosition, limit));
    } else if (type.equals("fixFees")) {
        request.setAttribute("counts", PriceDao.countAllFixFees());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves fixFees
        request.setAttribute("prices", PriceDao.retrieveFixFees(startPosition, limit));
    } else if (type.equals("percentageDiscounts")) {
        request.setAttribute("counts", PriceDao.countAllPercentageDiscounts());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves percentageDiscounts
        request.setAttribute("prices", PriceDao.retrievePercentageDiscounts(startPosition, limit));
    } else if (type.equals("percentageFees")) {
        request.setAttribute("counts", PriceDao.countAllPercentageFees());
        // If limit is -1 it will mean that all result must be retrieve
        if (limit == -1) {
            limit = ((Long) Long.parseLong(String.valueOf(request.getAttribute("counts")))).intValue();
        }
        // Retrieves percentageFees
        request.setAttribute("prices", PriceDao.retrievePercentageFees(startPosition, limit));
    } else {
        //TODO error
    }

    // If limit is different from 0
    if (limit != 0) {
        // Calculates the max pages
        Integer maxPages = (((Long) request.getAttribute("counts")).intValue() / limit);
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
<%@include file="navbar.html" %>

<!-- CONTAINER -->
<div class="container under-navbar">

    <div>
        <div class="left">
            <button class='btn btn-primary' data-toggle="modal" data-target="#createModal">Create new price</button>
        </div>

        <div class="second right">
            <div class="btn-group left">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                    <li><a href="<custom:replaceParam name='type' value='percentageDiscounts'/>">Percentage discounts</a></li>
                    <li><a href="<custom:replaceParam name='type' value='percentageFees'/>">Percentage fees</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="<custom:replaceParam name='type' value='allPrices'/>">All prices</a></li>
                </ul>
            </div>
        </div>
    </div>

    <!-- Table with prices -->
    <table id="prices-table" class="tablesorter table table-bordered table-hover table-striped">
        <caption>Ini adalah data biodata anda</caption>
        <thead>
            <tr>
                <th>#</th>
                <th>Price Type</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Value</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${prices.size() > 0}">
                    <c:forEach items="${prices}" var="price">
                        <tr>
                            <td>${price.id}</td>
                            <td>${price['class'].simpleName}</td>
                            <td><joda:format value="${price.startDate}" locale="en_US" style="SM" pattern="dd MMM, yyyy HH:mm"/></td>
                            <td><joda:format value="${price.endDate}" locale="en_US" style="SM" pattern="dd MMM, yyyy HH:mm"/></td>
                            <td><fmt:setLocale value="it_IT"/><fmt:formatNumber value="${price.value}" type="currency"/></td>
                            <td>
                                <button class='btn btn-warning btn-sm' data-toggle="modal" data-target="#updateModal"><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></button>
                                <button class='btn btn-danger btn-sm' role='button' data-toggle="modal" data-target="#deleteModal"><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">There are no prices</td>
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
                        <li><a href='<custom:replaceParam name='page' value='1'/>' title='Go to the first page.'>&laquo;</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${maxPages.intValue()}" varStatus="pageLoop">
                        <c:choose>
                            <c:when test="${pageNumber eq pageLoop.count}">
                                <li class='active'><a href='#'>${pageLoop.count}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href='<custom:replaceParam name='page' value='${(pageLoop.count).toString()}'/>'>${pageLoop.count}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pageNumber != maxPages}">
                        <li><a href='<custom:replaceParam name='page' value='${maxPages}'/>' title='Go to the last page.'>&raquo;</a></li>
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
            </p>
        </div>
    </div>

    <!-- Create Modal -->
    <div class="modal fade in" id="createModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="
    width: 480px;
">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="createModalLabel">Create price</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group" style="
">
                            <label style="
    display: block;
">Price type</label>
                            <div style="
    /* margin-left: 25px; */
    /* margin-right: 50px; */
    display: flex;
    justify-content: initial;
">
                                <div style="
    margin-top: 10px;
    margin-right: 20px;
    float: left;
">
                                    <div class="radio first-radio">
                                        <label>
                                            <input type="radio" name="inlineRadioOptions" id="inlineRadioBasePrice" value="basePrice"> Base Price
                                        </label>
                                    </div>
                                </div>
                                <div style="
    margin-left: 20px;
    margin-right: 20px;
    float: left;
">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="inlineRadioOptions" id="inlineRadioFixDiscount" value="fixDiscount"> Fix Discount
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="inlineRadioOptions" id="inlineRadioFixFee" value="fixFee">Percentage Discount
                                        </label>
                                    </div>
                                </div>
                                <div style="
    float: left;
    margin-left: 20px;
">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="inlineRadioOptions" id="inlineRadioPercentageDiscount" value="percentageDiscount"> Fix Fee
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="inlineRadioOptions" id="inlineRadioPercentageFee" value="percentageFee"> Percentage Fee
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div style="
    display: flex;
    justify-content: space-between;
    clear: both;
">

                            <div class="form-group left" style="
    align-self: center;
">

                                <label>Repeat it</label>
                                <select class="form-control" title="Every days">
                                    <option>Every days</option>
                                    <option>Every weeks</option>
                                    <option>Every months</option>
                                    <option>Every years</option>
                                    <option>Every weekends</option>
                                    <option>Every workdays</option>
                                    <option>Every no workdays</option>
                                </select>
                            </div>

                            <div class="form-group right" style="
    align-self: center;
    margin-right: 70px;
">

                                <label>Times</label>
                                <select class="form-control" title="1">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                </select>
                            </div>

                        </div>

                        <div class="form-group">
                            <label for="priceValue">Percentage value</label>
                            <div class="input-group" style="
    width: 440px;
">
                                <div class="input-group-addon">%</div>
                                <input type="text" class="form-control" id="priceValue" placeholder="Percentage">
                                <div class="input-group-addon">.00</div>
                            </div>
                        </div>

                        <div class="form-group date">
                            <label for="startDate">Start</label>
                            <div class="input-group date" style="
    width: 440px;
">
                                <input type="text" class="form-control" id="startDate" placeholder="20/01/2016"><div class="input-group-addon" style="
    width: 45px;
"><i class="glyphicon glyphicon-th"></i></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>End</label>
                            <div class="radio" style="
    height: 39px;">
                                <label>
                                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="never" checked="">Never</label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2"><div class="input-group" style="
">

                                    <div class="input-group" style="
    width: 420px;
">
                                        <input type="number" class="form-control" value="10"><span class="input-group-addon" id="sizing-addon1">occurrences</span>
                                    </div>
                                </div></label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3"><div class="input-group">

                                    <div class="input-group date" style="
    width: 420px;
">
                                        <input type="text" class="form-control" id="endDate" placeholder="20/01/2016" style="
"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div></label>
                            </div>

                        </div></form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Create price</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Edit Modal -->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editModalLabel">Update price</h4>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Delete Modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="deleteModalLabel">Delete price</h4>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <%@include file="footer.html" %>

</div>

<!-- Bootstrap core JavaScript -->
<%@include file="bootstrap_core_js.html" %>
</body>
</html>