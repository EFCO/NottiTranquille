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
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#signModal">Sign
                            In/Sign Up
                        </button>
                        <c:if test="${Login.logged}">
                            <c:if test="${Login.is('Tenant')}">
                                <button type="button" class="btn btn-default" data-toggle="modal"
                                        data-target="#reservationTenantModal">
                                    Reservations
                                </button>
                            </c:if>
                            <c:if test="${Login.is('Manager')}">
                                <button type="button" class="btn btn-default" data-toggle="modal"
                                        data-target="#reservationManagerModal">
                                    Reservations
                                </button>
                            </c:if>
                        </c:if>
                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

    </div>
</div>
