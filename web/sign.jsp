<jsp:useBean id="Login" scope="session"
             class="it.ispw.efco.nottitranquille.view.LoginBean"/>

<jsp:setProperty name="Login" property="*"/>


<%
    if (request.getParameter("sign") != null) {
        Login.validate();
%>
<jsp:forward page="index.jsp"/>
<%
    }
%>


<% if (Login.isLogged()) {
    Login.logout();
%>
<jsp:forward page="index.jsp"/>
<%
    }
%>


