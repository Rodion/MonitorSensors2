<%--@elvariable id="user" type="User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-default navbar-static-top" style="background: #20B2AA">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:if test="${empty user}">
                <form action="/jsp/user_login.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.signin"/></button>
                </form>
                <form action="/jsp/user_register.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.register"/></button>
                </form>
            </c:if>
            <c:if test="${not empty user}">


                <form action="/controller" class="navbar-form navbar-right">


                    <input type="hidden" name="action" value="find_sensor">

                    <div class="form-group">
                             <div class="col-sm-9">
                            <input type="text" id="find" name="findString" class="form-control"
                                   placeholder="<fmt:message key="search.sensor.placeholder"/>"
                                   onKeyup="checkData('find')"  maxlength="100" required pattern="[a-zA-Z0-9. ,-_*]{0,100}">
                            <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.search.sensor"/></b>
                        </div>


                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-default"><fmt:message key="nav.find.sensor"/></button>
                        </div>
                    </div>
                    </div>
                </form>



                <form action="/controller" class="navbar-form navbar-right">

                      <fmt:message key="nav.welcome"/><ctg:info user="${user}"/>
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.signout"/></button>
                </form>
            </c:if>

            <c:if test="${not empty user and user.roleId == 6}">
                <form action="/controller" method="post" class="navbar-form navbar-right">
                    <input type="hidden" name="action" value="show_admin_page">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.admin"/></button>
                </form>
            </c:if>
        </div>
    </div>
</nav>
