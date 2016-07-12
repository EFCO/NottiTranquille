<%@ page import="it.ispw.efco.nottitranquille.view.LoginBean" %><%-- Use JSTL joda lib in order to format joda's DataTime --%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<jsp:useBean id="TenantListBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.ListReservationBean"/>
<jsp:useBean id="ManagerListBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.ListReservationBean"/>

<%
    // Populate bean with information about reservation of the logged user
    if (Login.isLogged()) {
        if (Login.is("Tenant"))
            TenantListBean.populate(Login.getUsername(), "Tenant");
        if (Login.is("Manager"))
            ManagerListBean.populate(Login.getUsername(), "Manager");
    }

    if (request.getParameter("Decline") != null)
        TenantListBean.decline(request.getParameter("Decline"));

    if (request.getParameter("Approve") != null)
        TenantListBean.approve(request.getParameter("Approve"));

%>

<c:if test="${Login.logged }">

    <div style=" max-height: 420px; overflow-y: auto;" class=" modal fade" id="reservationTenantModal" tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">

                <c:if test="${Login.is('Tenant')}">
                    <%@include file="ReservationSummaryTenant.jsp" %>
                </c:if>
            </div>
        </div>
    </div>

    <div style=" max-height: 420px; overflow-y: auto;" class=" modal fade" id="reservationManagerModal" tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">

                <c:if test="${Login.is('Manager')}">
                    <%@include file="ReservationSummaryManager.jsp" %>
                </c:if>
            </div>
        </div>
    </div>


</c:if>

