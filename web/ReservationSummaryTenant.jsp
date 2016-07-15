<%
    if (request.getParameter("delete") != null) {
        TenantListBean.remove(request.getParameter("delete"));
        TenantListBean.populate(Login.getUsername(), "Tenant");
    }
%>

<c:if test='${TenantListBean.nRes > 0 }'>


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
                <th>State</th>
                <th>Action</th>

            </tr>
            </thead>
            <tbody>


            <c:forEach items="${TenantListBean.beans}" var="entry">
                <c:set var="resBean" scope="session" value="${entry}"/>

                <form action="Payment.jsp">
                    <tr>
                        <td>${resBean.location.name}</td>
                        <td>${resBean.startDate}</td>
                        <td>${resBean.endDate}</td>
                        <td>${resBean.price}</td>

                        <c:choose>
                        <c:when test="${resBean.state == 'Paid'}">
                        <td>Paid</td>
                        </c:when>
                        <c:when test="${resBean.state == 'ToApprove'}">
                        <td>to be approved</td>
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
                </form>

                <c:if test="${resBean.state!='Paid'}">
                    <form action="index.jsp">
                        <td>
                            <button type="submit" class="btn btn-primary" name="delete"
                                    id="delete"
                                    value="${resBean.id}">delete
                            </button>
                        </td>
                    </form>
                </c:if>
                </tr>

            </c:forEach>

            </tbody>
        </table>

    </div>

</c:if>

<c:if test='${TenantListBean.nRes == 0 }'>
    <div class="modal-body">
        <h4>Non sono presenti prenotazioni</h4>
    </div>
</c:if>

