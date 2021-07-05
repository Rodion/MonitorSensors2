<%--@elvariable id="sensor" type="Sensor"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.delete.sensor.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">

        <table class="table table-condensed table-bordered">
            <tr>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.number"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.sensor.title"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.sensor.model"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.sensor.range"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.sensor.type"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.sensor.unit"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.sensor.location"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.sensor.description"/></td>
            </tr>
            <tr>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.id}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.name}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.model}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.range_to-sensor.range_from}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.type}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.unit}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.location}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${sensor.description}</td>
            </tr>
        </table>


        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-2">
                    <form action="/controller" method="post" class="form-horizontal">
                        <input type="hidden" name="action" value="delete_sensor">
                        <div class="form-group">
                            <label for="type" class="col-sm-3 control-label"><fmt:message key="delete.sensor"/></label>

                            <div class="col-sm-8">
                                <input type="text" id="type" name="roleId" class="form-control"
                                       placeholder="<fmt:message key="search.form.delete.id.placeholder"/>"
                                       maxlength="3" required pattern="[0-9]{1,3}">
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <br/>
                                    <button type="submit" class="btn btn-primary"><fmt:message key="delete.sensor"/></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>



        <a href="../index.jsp" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
    <%@ include file="../include/footer.jsp" %>
</body>
</html>




