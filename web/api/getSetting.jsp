<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.Commodities" %>
<%@ page import="it.ispw.efco.nottitranquille.model.enumeration.StructureType" %>
<%@ page import="org.json.JSONObject" %>
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
        for (StructureType st : StructureType.values()) {
            jsonObject.put(st.name(), st.toString());
        }
        out.println(jsonObject.toString());
    }
%>
