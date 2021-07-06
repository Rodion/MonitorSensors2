<%--@elvariable id="usersList" type="java.util.ArrayList"--%>
<%--@elvariable id="sensorsList" type="java.util.ArrayList"--%>
<%--@elvariable id="sensorTypesList" type="java.util.ArrayList"--%>
<%--@elvariable id="sensorUnitsList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.admin.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/navbar_admin.jsp" %>

<%-- %%%%%%%%%%%%%%%%%%%%%%%% useres: uncommemnt when necessary-%%%%%%%%%%%%%%%%%%%%%%%%%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <div class="col-md-10 col-md-offset-1">
                <table class="table table-condensed table-bordered">
                    <tr class="active">
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.id"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.first.name"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.last.name"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.login"/></td>
                         <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.role"/></td>
                    </tr>
                    <c:forEach items="${usersList}" varStatus="сounter">
                        <tr class="active">
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].id}</td>
                            <td align="center"
                                style="border-color: #8381eb">${usersList[сounter.count-1].firstName}</td>
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].lastName}</td>
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].login}</td>
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].roleId}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
--%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <div class="col-md-10 col-md-offset-1">
                <table class="table table-condensed table-bordered">
                    <tr class="active">

                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.title"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.model"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.range"/></td>
                        <%--  <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.type"/></td>
                          <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.unit"/></td>
                          <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.location"/></td>--%>
                        <td align="center" style="border-color: #8381eb"><fmt:message
                                key="table.sensor.description"/></td>
                    </tr>
                    <c:forEach items="${sensorsList}" varStatus="сounter" end="3">
                        <tr class="active">

                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].name}</td>
                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].model}</td>
                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${(sensorsList[сounter.count-1].range_to)-(sensorsList[сounter.count-1].range_from)}</td>
                            <td align="center"
                                <%--        style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].type}</td>
                                    <td align="center"
                                        style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].unit}</td>
                                    <td align="center"
                                        style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].location}</td> --%>
                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].description}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="col-md-4">
            <div class="col-md-10 col-md-offset-0">
                <table class="table table-condensed table-bordered">

                    <tr class="active">

                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor_type.id"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message
                                key="table.sensor_type.type"/></td>

                    </tr>
                    <c:forEach items="${sensorTypesList}" varStatus="сounter" end="3">
                        <tr class="active">

                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${sensorTypesList[сounter.count-1].id}</td>
                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${sensorTypesList[сounter.count-1].type}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div class="col-md-4">
            <div class="col-md-10 col-md-offset-0">
                <table class="table table-condensed table-bordered">
                    <tr class="active">

                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor_unit.id"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message
                                key="table.sensor_unit.unit"/></td>

                    </tr>
                    <c:forEach items="${sensorUnitsList}" varStatus="сounter" end="3">
                        <tr class="active">

                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${sensorUnitsList[сounter.count-1].id}</td>
                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${sensorUnitsList[сounter.count-1].unit}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>

