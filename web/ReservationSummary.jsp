<%@ page import="it.ispw.efco.nottitranquille.model.Manager" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Reservation" %>
<%@ page import="java.util.List" %>
<%@ page import="it.ispw.efco.nottitranquille.model.dao.ManagerDAO" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Tenant" %>
<%@ page import="it.ispw.efco.nottitranquille.model.dao.TenantDao" %>
<%@ page import="it.ispw.efco.nottitranquille.view.LoginBean" %>

<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:if test="${ Login != null && Login.getUsername() != '' }">

    <div style=" max-height: 420px; overflow-y: auto;" class=" modal fade" id="reservationModal" tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">

                <%
                    // if user is a Tenant
                    LoginBean login = (LoginBean) session.getAttribute("Login");

                    if (login.getRole() == "Tenant") {

                        Tenant tenant = TenantDao.findByNameAndPassword(
                                login.getUsername(), login.getPassword());

                        List<Reservation> reservations = tenant.getReservations();
                        request.setAttribute("reservations", reservations);

                        int resValue = reservations.size();
                        request.setAttribute("resValue", resValue);
                %>

                <c:if test='${resValue > 0 }'>


                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>

                        <h2> Ciao
                            <jsp:getProperty name="Login" property="username"></jsp:getProperty>
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

                            <c:forEach var="i" begin="0" end="${resValue-1}">

                                <form action="Payment.jsp">
                                    <tr>
                                        <td>${reservations.get(i).getLocation().getName()}</td>
                                        <td><joda:format value="${reservations.get(i).getStartDate()}" locale="en_US"
                                                         style="SM"
                                                         pattern="dd MMM, yyyy HH:mm"/></td>
                                        <td><joda:format value="${reservations.get(i).getEndDate()}" locale="en_US"
                                                         style="SM"
                                                         pattern="dd MMM, yyyy HH:mm"/></td>
                                        <td>${reservations.get(i).getPrice()}</td>

                                        <c:choose>
                                            <c:when test="${reservations.get(i).state() == 'Paid'}">
                                                <td>Paid</td>
                                            </c:when>
                                            <c:when test="${reservations.get(i).state() == 'ToApprove'}">
                                                <td>to be approve</td>
                                            </c:when>
                                            <c:when test="${reservations.get(i).state() == 'Declined'}">
                                                <td>Declined</td>
                                            </c:when>
                                            <c:when test="${reservations.get(i).state() == 'ToPay'}">
                                                <td>
                                                    <button type="submit" class="btn btn-primary" name="Pay" id="Pay"
                                                            value="${reservations.get(i).getId()}">Pay
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

                <c:if test='${resValue == 0 }'>
                    <div class="modal-body">
                        <h4>Non sono presenti prenotazioni</h4>
                    </div>
                </c:if>

                <%
                    //if user if a Manager
                } else if (login.getRole() == "Manager") {

                    Manager manager = ManagerDAO.findByNameAndPassword(login.getUsername(),
                            login.getPassword());

                    List<Reservation> reservations = manager.getToApprove();
                    request.setAttribute("reservations", reservations);

                    int resValue = reservations.size();
                    request.setAttribute("resValue", resValue);

                %>

                <c:if test='${resValue > 0 }'>

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>

                        <h2> Ciao
                            <jsp:getProperty name="Login" property="username"></jsp:getProperty>
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

                            <c:forEach var="i" begin="0" end="${resValue-1}">

                                <form action="ReservationSummary.jsp">
                                    <tr>
                                        <td>${reservations.get(i).getLocation().getName()}</td>
                                        <td><joda:format value="${reservations.get(i).getStartDate()}" locale="en_US"
                                                         style="SM"
                                                         pattern="dd MMM, yyyy HH:mm"/></td>
                                        <td><joda:format value="${reservations.get(i).getEndDate()}" locale="en_US"
                                                         style="SM"
                                                         pattern="dd MMM, yyyy HH:mm"/></td>

                                        <td>
                                            <c:out value=" ${reservations.get(i).getTenant().getFirstName()} ${reservations.get(i).getTenant().getLastName()}"> </c:out>
                                        </td>

                                        <c:choose>
                                            <c:when test="${reservations.get(i).getState() == 'Paid'}">
                                                <td>Paid</td>
                                            </c:when>
                                            <c:when test="${reservations.get(i).getState() == 'ToApprove'}">
                                                <td>
                                                    <button type="submit" class="btn btn-primary" name="Approve"
                                                            id="Approve"
                                                            value="${reservations.get(i).getId()}">Approve
                                                    </button>
                                                </td>
                                            </c:when>
                                            <c:when test="${reservations.get(i).getState() == 'Declined'}">
                                                <td>Declined</td>
                                            </c:when>
                                            <c:when test="${reservations.get(i).getState() == 'ToPay'}">
                                                <td>To pay</td>
                                            </c:when>
                                        </c:choose>
                                    </tr>
                                    </tr>
                                </form>

                            </c:forEach>

                            </tbody>
                        </table>

                    </div>
                    <!-- ./modal-body -->

                </c:if>

                <c:if test="${resValue == 0}">

                    <div class="modal-body">
                        <h4>Non sono presenti prenotazioni</h4>
                    </div>

                </c:if>

                <%
                    }

                %>

            </div>
        </div>
    </div>

</c:if>


<c:if test="${ Login == null or Login.getUsername() == '' }">
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