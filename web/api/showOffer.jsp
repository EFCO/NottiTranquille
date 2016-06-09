<%@ page import="org.json.JSONObject" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Location" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 01/06/2016
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:useBean id="basicSearchBean" scope="session" class="it.ispw.efco.nottitranquille.view.SearchBean" />

<jsp:useBean id="loginBean" scope="session" class="it.ispw.efco.nottitranquille.view.LoginBean"/>



<%
    Location location = basicSearchBean.getResult().get(Integer.parseInt(request.getParameter("id")));
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("code","1");
    //TODO jsonObject.put("mainimage",photo)
    jsonObject.put("price",2032); //TODO usare il vero price
    jsonObject.put("description",location.getDescription());
    jsonObject.put("address",location.getLocationAddress());
    jsonObject.put("guests",location.getMaxGuestsNumber());
    jsonObject.put("bathrooms",location.getNumberOfBathrooms());
    jsonObject.put("beds",location.getNumberOfBeds());
    jsonObject.put("bedrooms",location.getNumberOfBedrooms());
    //TODO jsonObject.put("commodities",location.)
    out.println(jsonObject.toString());

%>