<!--Search modal-->

<div class="modal fade" id="searchModal" tabindex="-3" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Search</h4>
            </div>
            < class="modal-body">
            <form action="search.jsp" name="myform" method="GET">
                <div class="form-group">
                    <label for="nation">Nazione :</label>
                    <input name="nation" id="nation" type="text" class="form-control" placeholder="Roma">
                </div>
                <div class="form-group">
                    <label for="city">Città :</label>
                    <input name="city" id="city" type="text" class="form-control" placeholder="Zagarolo">
                </div>
                <div class="form-group">
                    <label for="checkin">Check In:</label>
                    <input name="checkin" id="checkin" type="date" class="form-control required">
                </div>
                <div class="form-group">
                    <label for="checkout">Check Out:</label>
                    <input name="checkout" id="checkout" type="date" class="form-control required">
                </div>
                <div class="form-group">
                    <label for="pricerange">Prezzo :</label>
                    <select name="pricerange" id="pricerange" class="form-control">
                        <option>Fino a 100 euro</option>
                        <option>da 100 a 200 euro</option>
                        <option>da 200 a 500 euro</option>
                    </select>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default">Advanced Search</button>
                    <button type="submit" name="search" class="btn btn-primary" id="search" value="search">Search
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--Sign in Modal-->
<%@include file="sign.jsp" %>

<%@include file="ReservationSummary.jsp" %>


<!--Button NavBar-->

<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Notti Tranquille</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                        <li><a href="#">Chi Siamo</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
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
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#searchModal">
                            Search
                        </button>
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#signModal">Sign
                            In/Sign Up
                        </button>
                        <button type="button" class="btn btn-default" data-toggle="modal"
                                data-target="#reservationModal">
                            Reservations
                        </button>
                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

    </div>
</div>