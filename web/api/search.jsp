<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 12/05/2016
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="basicSearchBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.SearchBean" />


<jsp:setProperty name="basicSearchBean" property="*" />

<%
    if (request.getParameter("search") != null) {
        if(request.getParameter("nation").equals("")) {
            basicSearchBean.setNation("");
        }
        if(request.getParameter("city").equals("")) {
            basicSearchBean.setCity("");
        }
        if (!request.getParameter("checkin").equals("") && !request.getParameter("checkout").equals("")) {
            basicSearchBean.setCheckin(request.getParameter("checkin"));
            basicSearchBean.setCheckout(request.getParameter("checkout"));
        }
        out.println(basicSearchBean.api_result());
    }
%>

