<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<jsp:useBean id="ListBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.ListReservationBean"/>

<%
    // Populate bean with information about reservation of the logged user
    if (Login != null && Login.getUsername() != "")
        ListBean.populate(Login.getUsername(), Login.getRole());

    if (request.getParameter("Decline") != null)
        ListBean.decline(request.getParameter("Decline"));

    if (request.getParameter("Approve") != null)
        ListBean.approve(request.getParameter("Approve"));

%>

<c:if test="${ Login != null && Login.username != '' }">

    <div style=" max-height: 420px; overflow-y: auto;" class=" modal fade" id="reservationModal" tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">

                <c:choose>
                    <c:when test="${Login.role== 'Tenant'}">


                        <c:if test='${ListBean.nRes > 0 }'>


                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>

                                <h2> Ciao
                                        ${Login.username}
                                    !</h2>
                                <h4> Scegli cosa fare con le tue prenotazioni!</h4>
                            </div>

                            <div class="modal-body">


                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>Location</th>
                                        <th>from</th>
                                        <th>to</th>
                                        <th>price</th>
                                        <th>pay</th>

                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach items="${ListBean.beans}" var="entry">
                                        <c:set var="resBean" scope="session" value="${entry}"/>

                                        <form action="Payment.jsp">
                                            <tr>
                                                <td>${resBean.locationBean.name}</td>
                                                <td>${resBean.startDate}</td>
                                                <td>${resBean.endDate}</td>
                                                <td>${resBean.price}</td>

                                                <c:choose>
                                                    <c:when test="${resBean.state == 'Paid'}">
                                                        <td>Paid</td>
                                                    </c:when>
                                                    <c:when test="${resBean.state == 'ToApprove'}">
                                                        <td>to be approve</td>
                                                    </c:when>
                                                    <c:when test="${resBean.state == 'Declined'}">
                                                        <td>Declined</td>
                                                    </c:when>
                                                    <c:when test="${resBean.state == 'ToPay'}">
                                                        <td>
                                                            <button type="submit" class="btn btn-primary" name="Pay"
                                                                    id="Pay"
                                                                    value="${resBean.id}">Pay
                                                            </button>
                                                        </td>
                                                    </c:when>
                                                </c:choose>


                                            </tr>
                                        </form>

                                    </c:forEach>

                                    </tbody>
                                </table>

                            </div>
                            <!-- ./modal-body -->

                        </c:if>

                        <c:if test='${ListBean.nRes == 0 }'>
                            <div class="modal-body">
                                <h4>Non sono presenti prenotazioni</h4>
                            </div>
                        </c:if>

                    </c:when>
                    <c:when test="${Login.role=='Manager'}">

                        <c:if test='${ListBean.nRes > 0 }'>

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>

                                <h2> Ciao
                                        ${Login.username}
                                    !</h2>
                                <h4> Scegli cosa fare con le tue prenotazioni!</h4>
                            </div>

                            <div class="modal-body">

                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Location</th>
                                        <th>from</th>
                                        <th>to</th>
                                        <th>Tenant</th>
                                        <th>Approve</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${ListBean.beans}" var="entry">
                                        <c:set var="resBean" scope="session" value="${entry}"/>

                                        <form action="index.jsp">
                                            <tr>
                                                <td>${resBean.locationBean.name}</td>
                                                <td>${resBean.startDate}</td>
                                                <td>${resBean.endDate}
                                                <td>
                                                    <c:out value=" ${resBean.tenant} "> </c:out>
                                                </td>

                                                <c:choose>
                                                    <c:when test="${resBean.state == 'Paid'}">
                                                        <td>Paid</td>
                                                    </c:when>
                                                    <c:when test="${resBean.state == 'ToApprove'}">
                                                        <td>
                                                            <button type="submit" class="btn btn-primary" name="Approve"
                                                                    id="Approve"
                                                                    value="${resBean.id}">Approve
                                                            </button>
                                                            <button type="submit" class="btn btn-primary" name="Decline"
                                                                    id="Decline"
                                                                    value="${resBean.id}">Decline
                                                            </button>
                                                        </td>
                                                    </c:when>
                                                    <c:when test="${resBean.state== 'Declined'}">
                                                        <td>Declined</td>
                                                    </c:when>
                                                    <c:when test="${resBean.state == 'ToPay'}">
                                                        <td>To pay</td>
                                                    </c:when>
                                                </c:choose>
                                            </tr>
                                        </form>

                                    </c:forEach>

                                    </tbody>
                                </table>

                            </div>
                            <!-- ./modal-body -->

                        </c:if>

                        <c:if test="${ListBean.nRes == 0}">

                            <div class="modal-body">
                                <h4>Non sono presenti prenotazioni</h4>
                            </div>

                        </c:if>

                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>

</c:if>


<c:if test="${ Login == null or Login.username == '' }">
    <div style=" max-height: 420px; overflow-y: auto;" class=" modal fade" id="reservationModal" tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">

                    <h4>
                        Questa funzionalita' e' disponibile solo per utenti Registrati!
                        <br><br>
                        Se possiedi un account qui potrai visualizzare le prenotazioni da te effettuate!

                    </h4>
                </div>
            </div>
        </div>
    </div>
</c:if>