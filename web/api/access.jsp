<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 02/05/2016
  Time: 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="userBean" scope="session" class="it.ispw.efco.nottitranquille.view.UserBean"/>
<jsp:setProperty name="userBean" property="*" />
 <%
        if (request.getParameter("login") != null) {
            out.println(userBean.api_login_response());
        }

        if (request.getParameter("register") != null) {
            out.println(userBean.api_register_response());

        }
 %>
