<%@ page import="it.ispw.efco.nottitranquille.model.dao.ReservationDAO" %>
<%@ page import="it.ispw.efco.nottitranquille.model.Reservation" %><%--
  Created by IntelliJ IDEA.
  User: emanuele
  Date: 25/01/16
  Time: 15.21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="PaymentBean" scope="request"
             class="it.ispw.efco.nottitranquille.view.PaymentBoundary"/>

<jsp:setProperty name="PaymentBean" property="*"/>


<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="Payment.jsp" id="myform" method="post" class="form-horizontal">


    <div class="container">
        <h1>Please enter Payment data </h1>

        <div class="col-md-6 column">
            <table class="table table-hove table-condensed" id="tab_logic">
                <thead>
                <tr>
                    <th class="text-center">
                        Name
                    </th>
                    <th class="text-center">
                        Number
                    </th>
                    <th class="text-center">
                        code
                    </th>
                </tr>
                </thead>

                <tbody>

                <div class="form-group">

                    <tr id='addr0'>
                        <td>
                            <input type="text" id="intestatario" name='intestatario' class='form-control input-md'
                                   required/>
                        </td>
                        <td>
                            <input type="text" id="number" name='number' class='form-control input-md'
                                   required/>
                        </td>
                        <td>
                            <input type="text" id="code" name='code' class='form-control input-md'
                                   required/>
                        </td>
                    </tr>
                </div>

                </tbody>
            </table>
        </div>
    </div>

    <button type="submit" class="btn btn-primary" name="Pay" id="Pay">paga</button>

</form>

</body>
</html>
