<%@ page import="org.json.JSONObject" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.Commodities" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.LocationType" %>
<%--
  Created by IntelliJ IDEA.
  User: Federico
  Date: 12/05/2016
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (request.getParameter("setting").equals("commodities")) {
        JSONObject jsonObject = new JSONObject();
        for (Commodities c : Commodities.values()) {
            jsonObject.put(c.name(), c.toString());
        }
        out.println(jsonObject.toString());
    }

    if (request.getParameter("setting").equals("locationtypes")) {
        JSONObject jsonObject = new JSONObject();
        for (LocationType lt : LocationType.values()) {
            jsonObject.put(lt.name(), lt.toString());
        }
        out.println(jsonObject.toString());
    }
%>
