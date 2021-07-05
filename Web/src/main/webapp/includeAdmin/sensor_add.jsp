<%--@elvariable id="sensorTypesList" type="java.util.ArrayList"--%>
<%--@elvariable id="sensorUnitsList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.add.sensor.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>


            <form action="/controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="add_sensor">

                <div class="form-group">
                    <label for="title" class="col-sm-3 control-label"><fmt:message key="add.sensor.form.title"/></label>
                    <div class="col-sm-9">
                        <input type="text"  id="title" name="title" class="form-control" placeholder="<fmt:message key="add.sensor.form.title.placeholder"/>"
                               maxlength="30" required pattern="[a-zA-Z0-9._*]{1,30}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.sensor.title"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="model" class="col-sm-3 control-label"><fmt:message key="add.sensor.form.model"/></label>
                    <div class="col-sm-9">
                        <input type="text"  id="model" name="model" class="form-control" placeholder="<fmt:message key="add.sensor.form.model.placeholder"/>"
                               maxlength="15" required pattern="[a-zA-Z0-9.-_*]{1,15}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.sensor.model"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="range_fromForm" class="col-sm-2 control-label"><fmt:message key="register.form.range_from"/></label>
                    <div class="col-sm-10">
                        <input type="number" step="1" id="range_fromForm" name="range_from" class="form-control" placeholder="<fmt:message key="register.form.range_from.placeholder"/>"
                               maxlength="100" required pattern="[0-100]{1,100}">
                        <b id="range_from" style="color: red; font-size: 10px"><fmt:message key="validation.range_from"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="range_fromForm" class="col-sm-2 control-label"><fmt:message key="register.form.range_to"/></label>
                    <div class="col-sm-10">
                        <input type="number" step="1" id="range_toForm" name="range_to" class="form-control" placeholder="<fmt:message key="register.form.range_to.placeholder"/>"
                               <%--TODO: onKeyup="checkData('range')" --%>
                               maxlength="100" required pattern="[0-100]{1,100}">
                        <b id="range_to" style="color: red; font-size: 10px"><fmt:message key="validation.range_to"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sensorType" class="col-sm-3 control-label"><fmt:message key="add.sensor.form.type.choose"/></label>
                    <div class="col-sm-9">
                        <select class="form-control" id="sensorType" name="typeId">
                            <c:forEach items="${sensorTypesList}" var="type">
                                <option value="${type.id}"> ${type.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sensorUnit" class="col-sm-3 control-label"><fmt:message key="add.sensor.form.unit.choose"/></label>
                    <div class="col-sm-9">
                        <select class="form-control" id="sensorUnit" name="unitId">
                            <c:forEach items="${sensorUnitsList}" var="sensorUnit">
                                <option value="${sensorUnit.id}"> ${sensorUnit.unit}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <label for="location" class="col-sm-3 control-label"><fmt:message key="add.sensor.form.location"/></label>
                    <div class="col-sm-9">
                        <input type="text"  id="location" name="location" class="form-control" placeholder="<fmt:message key="add.sensor.form.location.placeholder"/>"
                               maxlength="40" required pattern="[a-zA-Z0-9._*]{0,40}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.sensor.location"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="location" class="col-sm-3 control-label"><fmt:message key="add.sensor.form.description"/></label>
                    <div class="col-sm-9">
                        <input type="text"  id="description" name="location" class="form-control" placeholder="<fmt:message key="add.sensor.form.description.placeholder"/>"
                               maxlength="100" required pattern="[a-zA-Z0-9._*]{0,200}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.sensor.description"/></b>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add.sensor.form.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
<br/>
<br/>
<%@ include file="../include/footer.jsp" %>
</body>
</html>


