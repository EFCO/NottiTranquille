<%@ page import="it.ispw.efco.nottitranquille.view.LoginBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:setProperty name="loginBean" property="*" />


<html>
<head>
    <title>Access Page</title>
</head>
<body>

    <%
        if (request.getParameter("login") != null) {
            loginBean.setCookie(request.getCookies()[0].getValue());
            if (loginBean.login() != 0) {
                String referer = request.getHeader("Referer");
                // handle empty referer.....
                response.sendRedirect(referer);
            } else {
                // handle empty referer.....
                session.removeAttribute("loginBean");
                response.sendRedirect("errorPage.jsp");
            }
        }
        if (request.getParameter("logout") != null) {
            loginBean.logout();
            session.removeAttribute("loginBean");
            String referer = request.getHeader("Referer");
            // handle empty referer.....
            response.sendRedirect(referer);
        }

        if (request.getParameter("register") != null) {
            registrationBean.register();
            String referer = request.getHeader("Referer");
            // handle empty referer.....
            response.sendRedirect(referer);
        }
    %>

</body>
</html>
