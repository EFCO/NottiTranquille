<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.PriceRanges" %>

<c:set var="priceranges" value="<%=PriceRanges.values()%>"/>


<div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Search</h4>
            </div>
            <div class="modal-body">
                <form action="search.jsp" name="myform" method="GET">
                    <div class="form-group">
                        <label for="nation">Nazione :</label>
                        <input name="nation" id="nation" type="text" class="form-control" placeholder="Roma">
                    </div>
                    <div class="form-group">
                        <label for="city">Citt&agrave; :</label>
                        <input name="city" id="city" type="text" class="form-control" placeholder="Zagarolo">
                    </div>
                    <div class="form-group">
                        <div class='input-group date' id='checkinpickermodal'>
                            <input type='text' name="checkin" id ="checkin" class="form-control" placeholder="Check In" required/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class='input-group date' id='checkoutpickermodal'>
                            <input type='text' name="checkout" id="checkout" class="form-control" placeholder="Check Out" required/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function() {
                            $('#birthdatepickermodal').datetimepicker({
                                format: 'DD-MM-YYYY',
                            });
                            $('#checkinpickermodal').datetimepicker({
                                format: 'DD-MM-YYYY',
                            });
                            $('#checkoutpickermodal').datetimepicker({
                                format: 'DD-MM-YYYY',
                                useCurrent: false //Important! See issue #1075
                            });
                            $("#checkinpickermodal").on("dp.change", function (e) {
                                $('#checkoutpickermodal').data("DateTimePicker").minDate(e.date);
                            });
                            $("#checkoutpickermodal").on("dp.change", function (e) {
                                $('#checkinpickermodal').data("DateTimePicker").maxDate(e.date);
                            });
                        });

                    </script>
                    <div class="form-group">
                        <label for="pricerange">Prezzo :</label>
                        <select name="pricerange" id="pricerange" class="form-control">
                            <c:forEach items="${priceranges}" var="pr">
                                <option value="${pr.name()}">${pr.text}</option>
                            </c:forEach>
                        </select>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="submit" name="search" class="btn btn-default" id="advsearch" value="advsearch">Advanced Search</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" name="search" class="btn btn-primary" id="search" value="search">Search</button>
            </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="signModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">Sign In</h4>
            </div>
            <div class="modal-body">
                <form action="access.jsp" name="loginForm" id="regLogForm" method="POST">
                    <div class="form-group">
                        <label for="username" id="userlabel">Username or Email:</label>
                        <input name="username" id="username" type="text" class="form-control" placeholder="fede" value="fede" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input name="password" id="password" type="password" class="password-field form-control" placeholder="password" value="fede" required>
                    </div>
                    <div id="regdiv" style="display: none">
                        <div class="form-group" >
                            <label for="firstName">Nome:</label>
                            <input name="firstName" id="firstName" type="text" class="form-control" placeholder="Federico" value="Federico">
                        </div>
                        <div class="form-group" >
                            <label for="lastName">Cognome:</label>
                            <input name="lastName" id="lastName" type="text" class="form-control" placeholder="Vagnoni" value="Vagnoni">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input name="email" id="email" type="email" class="form-control" placeholder="fede93.vagnoni@gmail.com" value="fede93.vagnoni@gmail.com">
                        </div>
                        <div class="form-group" >
                            <label for="phonenumber">Numero di telefono:</label>
                            <input name="phonenumber" id="phonenumber" type="text" class="form-control" placeholder="331 4447041" value="331 4447041">
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <label for="address">Indirizzo:</label>
                                <input name="address" id="address" type="text" class="form-control" placeholder="Piazza Ciao" value="Piazza Ciao">
                            </div>
                            <div class="col-md-3">
                                <label for="nation">Nazione:</label>
                                <input name="nation" id="nation" type="text" class="form-control" placeholder="Italia" value="Italia">
                            </div>
                            <div class="col-md-3">
                                <label for="city">Città:</label>
                                <input name="city" id="city" type="text" class="form-control" placeholder="Roma" value="Roma">
                            </div>
                            <div class="col-md-3">
                                <label for="postalcode">Codice postale:</label>
                                <input name="postalcode" id="postalcode" type="text" class="form-control" placeholder="00039" value="00039">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="gender">Sesso:</label>
                            <select name="gender" id="gender" class="form-control">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div class='input-group date' id="birthdatepickermodal">
                            <input type='text' name="dateofbirth" id ="dateofbirth" class="form-control" placeholder="Data di nascita" value="01-08-1993"/>
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <div class="form-group">
                            <label for="manager">Vorresti anche mettere in affitto locazioni?</label>
                            <input type="checkbox" name="manager" id="manager"/>
                        </div>
                    </div>
            <div class="modal-footer">
                <button type="submit" name="login" class="btn btn-default btn-primary" id="action" value="login">Log in</button>
                <button type="button" name="switcher" class="btn btn-default" id="switcher">Or Register</button>
            </div>
                <script>
                    $(function () {
                        var switchButton = $('#switcher');
                        var actionButton = $('#action');

                        switchButton.click(function () {
                            if ($("#regLogForm").attr("name") == "loginForm") {
                                $('#userlabel').text("Username :");
                                switchButton.text("or Log In");
                                actionButton.text("Register");
                                $("#regLogForm :input").each(function () {
                                    $(this).prop("required",true);
                                })
                                $('#regdiv').show('slide');
                                $("#regLogForm").attr("name","regForm");
                                actionButton.val("register");
                                actionButton.attr("name",'register');
                            } else {
                                $('#userlabel').text("Username or Email :");
                                $("#regLogForm").attr("name", "loginForm");
                                switchButton.text("or Register");
                                actionButton.text("Log in");
                                actionButton.val("login");
                                actionButton.attr("name",'login');
                                $('#regdiv').hide('slide');
                                $('#regLogForm :input').slice(2).each(function () {
                                    $(this).prop("required",false);
                                })
                            }
                        });
                    });
                </script>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Profile Manager</h4>
            </div>
            <div class="modal-body">
                <form action="manageProfile.jsp" name="myform" method="POST">
                    <button type="submit" class="btn btn-default">Accedi al tuo profilo</button>
                </form>
            </div>
            <div class="modal-footer">
                <form action="access.jsp" name="myform" method="POST">
                    <button type="submit" name="logout" class="btn btn-default btn-primary" id="logout" value="logout">Logout</button>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="index.jsp"/>">Notti Tranquille</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                        <li><a href="#">Chi Siamo</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#searchModal">Search</button>
                        <%
                        if (loginBean.isLoggedIn() != 1) {
                        %>
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#signModal">Sign In/Sign Up</button>
                        <%
                            } else {
                        %>
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#profileModal"
                                style="-fx-font-weight: 300">${loginBean.username}</button>
                        <%
                            }
                        %>

                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

    </div>
</div>
