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

<html>
<head>
    <title>Access Page</title>
</head>
<body>

    <%
        if (request.getParameter("login") != null) {
            if (userBean.validate()) {
                String referer = request.getHeader("Referer");
                // handle empty referer.....
                response.sendRedirect(referer);
            }
        }
        if (request.getParameter("logout") != null) {
            userBean.logout();
            String referer = request.getHeader("Referer");
            // handle empty referer.....
            response.sendRedirect(referer);
        }

        if (request.getParameter("register") != null) {
            userBean.register();
            String referer = request.getHeader("Referer");
            // handle empty referer.....
            response.sendRedirect(referer);
        }




    %>
</body>
</html>
