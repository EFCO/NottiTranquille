<c:if test='${ManagerListBean.nRes > 0 }'>

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

            <c:forEach items="${ManagerListBean.beans}" var="entry">
                <c:set var="resBean" scope="session" value="${entry}"/>

                <form action="index.jsp">
                    <tr>
                        <td>${resBean.location.name}</td>
                        <td>${resBean.startDate}</td>
                        <td>${resBean.endDate}
                        <td>
                            <c:out value=" ${resBean.username} "> </c:out>
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

</c:if>

<c:if test="${ManagerListBean.nRes == 0}">

    <div class="modal-body">
        <h4>Non sono presenti prenotazioni</h4>
    </div>

</c:if>