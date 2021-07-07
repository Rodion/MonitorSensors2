<%--@elvariable id="sensorsList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.found.sensor.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>

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

<a href="/controller?action=show_all_user_sensors_changing" class="btn btn-default"><fmt:message key="continue"/></a>

</div>
</body>
</html>


