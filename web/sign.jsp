<%
    if (request.getParameter("sign") != null) {
        Login.validate();
    }
%>

<c:if test="${ !Login.logged }">

    <div class=" modal fade" id="signModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">Sign In</h4>
                </div>

                <form action="index.jsp" name="loginForm" method="POST">
                    <div class="modal-body">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="E-mail" name="username">
                            <input type="password" class="form-control" placeholder="Password" name="password">
                        </div>
                        <button type="submit" class="btn btn-default" name="sign" id="sign">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>
