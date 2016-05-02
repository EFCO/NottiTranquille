<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 02/05/2016
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" scope="session" class="it.ispw.efco.nottitranquille.view.UserBean"/>

<html>
<head>
    <title>Logout</title>
</head>
<body>
<%
    userBean.logout();
    String referer = request.getHeader("Referer");
    // handle empty referer.....
    response.sendRedirect(referer);
%>

</body>
</html>
