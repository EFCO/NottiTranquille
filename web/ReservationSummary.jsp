<%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<jsp:useBean id="ListBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.ListReservationBean"/>
<%
    // Populate bean with information about reservation of the logged user
    if (Login.isLogged())
        ListBean.populate(Login.getUsername(), Login.getRole());

    if (request.getParameter("Decline") != null)
        ListBean.decline(request.getParameter("Decline"));

    if (request.getParameter("Approve") != null)
        ListBean.approve(request.getParameter("Approve"));

%>

<c:if test="${ Login.logged }">

    <div style=" max-height: 420px; overflow-y: auto;" class=" modal fade" id="reservationModal" tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">

                <c:choose>
                    <c:when test="${Login.role== 'Tenant'}">

                        <%@include file="ReservationSummaryTenant.jsp" %>
                    </c:when>

                    <c:when test="${Login.role=='Manager'}">

                        <%@include file="ReservationSummaryManager.jsp" %>

                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>

</c:if>


<c:if test="${ !Login.logged }">
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