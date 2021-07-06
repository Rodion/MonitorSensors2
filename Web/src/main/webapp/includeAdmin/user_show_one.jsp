<%--@elvariable id="userOne" type="User"--%>
<%--@elvariable id="rolesList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../../js/validator.js"></script>
    <title><fmt:message key="title.user.page"/></title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<br/>
<br/>
<br/>

<div class="container-fluid">
    <div class="col-md-6 col-md-offset-3">
        <c:if test="${not empty emptyUser}">
            <div class="text-center">
                <h4><fmt:message key="message.empty.user"/></h4>
            </div>
        </c:if>
        <c:if test="${not empty userOne}">
            <table class="table table-condensed table-bordered">
                <tr>
                    <td align="center" style="border-color:  #20B2AA"><fmt:message key="table.user.id"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.first.name"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.last.name"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.login"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.password"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.role"/></td>
                </tr>
                <tr>
                    <td align="center" style="border-color: #dae5ff">${userOne.id}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.firstName}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.lastName}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.login}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.password}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.roleId}</td>
                </tr>
            </table>
        </c:if>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">

            <div class="container-fluid">
                <div class="row">

                    <div class="col-md-6 col-md-offset-3">


                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="change_user_role">
                            <div class="hidden">
                                <label for="user2" class="col-sm-2 control-label"><fmt:message
                                        key="update.user.form.user"/></label>
                                <div class="hidden">
                                    <input type="text" id="user2" name="userId" value="${userOne.id}"
                                           class="form-control"
                                           placeholder="<fmt:message key="search.form.user.id.placeholder"/>"
                                           maxlength="3" required pattern="[0-9]{1,3}">
                                </div>
                            </div>
                            <div class="col-sm-10">
                                <input type="hidden" name="action" value="change_user_role">
                                <div class="form-group">
                                    <label for="roleForm"
                                           class="col-sm-2 control-label"><fmt:message
                                            key="nav.role"/></label>
                                    <div class="col-sm-10">
                                        <select class="form-control" id="roleForm" name="roleId">
                                            <c:forEach items="${rolesList}" var="role">
                                                <option value="${role.id}"><fmt:message
                                                        key="update.user.id"/> ${role.id},
                                                    <fmt:message key="update.user.role"/> ${role.role}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7">
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="nav.user.change"/></button>
                                    </div>
                                </div>

                            </div>
                        </form>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <a
                            href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message
                            key="continue"/></a> &nbsp; <a href="/controller?action=show_delete_user_page"
                                                           class="btn btn-default"><fmt:message key="delete.user"/></a>
                    </div>
                </div>

            </div>

            <br/>
            <br/>
            <%@ include file="../include/home.jsp" %>
            <br/>
            <br/>

            <%@ include file="../include/footer_admin.jsp" %>

</body>
</html>

