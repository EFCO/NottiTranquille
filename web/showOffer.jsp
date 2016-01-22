<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%@ page import="it.ispw.efco.nottitranquille.controller.FilteredSearch" %><%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 20/01/2016
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="basicSearchBean" scope="session"
             class="it.ispw.efco.nottitranquille.view.SearchBean" />

<html>

<head>
    <%@include file="header.html" %>

    <%--TODO Inserire nome offerta al posto del title--%>
    <c:set var="location" value="${basicSearchBean.result.get(requestScope.id)}"/>
    
    
    <title>${location.structure.name}</title>

</head>

<body>

<c:out value="${location.locationAddress}"/>

    





</body>

</html>
