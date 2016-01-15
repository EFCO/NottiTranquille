<%@ page import="it.ispw.efco.nottitranquille.model.dao.PriceDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Use JSTL core lib in order to add some useful feature --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

    <!-- Data picker JS -->
    <script type="text/javascript" src="<c:url value="resources/js/bootstrap-datepicker.min.js" />"></script>

    <!-- Custom JS -->
    <script src="<c:url value="resources/js/prices.js" />"></script>

    <title>Notti Tranquille</title>

</head>


<%-- prices.jsp?locationId=&type=&page=&limit=--%>
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
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${param.limit}<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">25</a></li>
                    <li><a href="#">50</a></li>
                    <li><a href="#">100</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">All</a></li>
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
                    All prices <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">Base prices</a></li>
                    <li><a href="#">Discounts</a></li>
                    <li><a href="#">Fees</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Fix discounts</a></li>
                    <li><a href="#">Fix fees</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Percentage discounts</a></li>
                    <li><a href="#">Percentage fees</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">All prices</a></li>
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
        <%
            request.setAttribute("prices", PriceDao.retrievePrices( 0, 100));
        %>
        <tbody>
            <c:forEach items="${prices}" var="price">
                <tr>
                    <td>${price.id.toString()}</td>
                    <td>BasePrice</td>
                    <td>Jan 18, 2001 9:12 AM</td>
                    <td>Jan 18, 2001 9:12 AM</td>
                    <td>5€</td>
                    <td width='100px'>
                        <button class='btn btn-warning btn-sm' data-toggle="modal" data-target="#updateModal"><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></button>
                        <button class='btn btn-danger btn-sm' role='button' data-toggle="modal" data-target="#deleteModal"><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <div>
        <div class="left">
            <nav>
                <ul class="pagination">
                    <li><a href='{$page_dom}' title='Go to the first page.'>&laquo;</a></li>
                    <li class='active'><a href=\"#\">1</a></li>
                    <li><a href='{$page_dom}?page=$x'>2</a></li>
                    <li><a href='{$page_dom}?page=$x'>3</a></li>
                    <li><a href='" .$page_dom . "?page={$total_pages}' title='Last page is {$total_pages}.'>&raquo;</a></li>
                </ul>
            </nav>
        </div>
        <div id="items-counter" class="right">
            <p>
                Items <b>1</b> to <b>1</b> of <b>1</b>.
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