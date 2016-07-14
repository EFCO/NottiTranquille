<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 02/05/2016
  Time: 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="registrationBean" scope="session" class="it.ispw.efco.nottitranquille.view.RegistrationBean"/>
<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<jsp:setProperty name="registrationBean" property="*" />
<jsp:setProperty name="loginBean" property="*"/>

 <%
        if (request.getParameter("login") != null) {
            out.println(loginBean.api_login_response());
        }

        if (request.getParameter("register") != null) {
            out.println(registrationBean.api_register_response());

        }
 %>
