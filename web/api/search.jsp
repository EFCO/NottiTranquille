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

%>

<html>
<head>
    <title>Search Page</title>
</head>

<body>
<%
    if (request.getParameter("search") != null) {
        out.println(basicSearchBean.api_result());
    }
%>

</body>

</html>
