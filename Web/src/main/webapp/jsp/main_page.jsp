<%--@elvariable id="user" type="User"--%>
<%--@elvariable id="sensorsList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.main.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<%@ include file="../include/navbar.jsp" %>


<%--users role: 7 - user; 6 - administrator--%>
<%--roleI  7 display a list of sensors without the possibility to edit them--%>


<div class="container-fluid">
    <div class="col-sm-10">
        <c:if test="${empty user or (not empty user and user.roleId < 6)}">
            <lable class="col-sm-19 control-label"><b><fmt:message key="welcome.guest"/></b></lable>
        </c:if>
    </div>
</div>

<c:if test="${not empty user  and  (user.roleId == 6) or (user.roleId == 7)}">

    <div class="container-fluid">
        <div class="col-md-6 col-md-offset-6">
            <table class="table table-condensed table-bordered">
                <tr>
                    <c:if test="${not empty user  and  (user.roleId == 6)}">
                        <td align="center" style="border-color: #8381eb"><fmt:message key="blank"/></td>
                    </c:if>
                    <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.id"/></td>
                    <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.title"/></td>
                    <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.model"/></td>
                    <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.range"/></td>
                    <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.type"/></td>
                    <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.unit"/></td>
                    <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.location"/></td>
                        <%--   <td align="center" style="border-color: #8381eb"><fmt:message key="table.sensor.description"/></td>--%>


                    <c:if test="${not empty user  and  (user.roleId == 6)}">
                        <td align="center" style="border-color: #8381eb"><fmt:message key="blank"/></td>
                    </c:if>
                </tr>

                <c:forEach items="${sensorsList}" varStatus="сounter" >
                    <tr>
                        <c:if test="${not empty user  and  (user.roleId == 6)}">
                            <td align="center" style="vertical-align: middle; border-color: #dae5ff">
                                <form action="/controller" method="post">
                                    <input type="hidden" name="action" value="show_add_sensor_page">
                                    <button type="submit" class="btn btn-link" id="sensorId" name="sensorId"
                                            value="${sensorsList[counter.count-1].id}">

                                        edit
                                    </button>
                                </form>
                            </td>
                        </c:if>
                        <td align="center"
                            style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].id}</td>
                        <td align="center"
                            style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].name}</td>
                        <td align="center"
                            style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].model}</td>
                        <td align="center"
                            style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].range_to-sensorsList[сounter.count-1].range_from}</td>
                        <td align="center"
                            style="vertical-align: middle; border-color: #8381eb"> ${sensorsList[сounter.count-1].type}</td>
                        <td align="center"
                            style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].unit}</td>

                        <td align="center"
                            style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].location}</td>

                            <%--      <td align="center"
                                      style="vertical-align: middle; border-color: #8381eb">${sensorsList[сounter.count-1].description}</td> --%>


                        <c:if test="${not empty user  and  (user.roleId == 6)}">

                            <td align="center" style="vertical-align: middle; border-color: #dae5ff">
                                <form action="/controller" method="post">
                                    <input type="hidden" name="action" value="show_delete_sensor_page">

                                    <button type="submit" class="btn btn-link" name="sensorId"
                                            value="${sensorsList[counter.count-1].id}"><img src="../images/delete.png">

                                    </button>
                                </form>
                            </td>

                        </c:if>
                    </tr>
                </c:forEach>
            </table>

            <nav aria-label="...">
                <ul class="pager">
                    <li class="previous${leftPageClass}"><a href="${leftPage}"><fmt:message key="page.previous"/></a>
                    </li>
                    <li class="next${rightPageClass}"><a href="${rightPage}"><fmt:message key="page.next"/></a></li>
                </ul>
            </nav>
        </div>
    </div>

    <%--
     <c:if test="${not empty user  and  (user.roleId == 6)}">
 <div class="container-fluid">
     <div class="col-md-6 col-md-offset-6">
         <table class="table table-condensed table-bordered">
     <td align="center" style="vertical-align: middle; border-color: #dae5ff">
         <form action="/controller" method="post">
             <input type="hidden" name="action" value="show_admin_page">

             <button type="submit" class="btn btn-link"><fmt:message key="nav.admin"/></button>
         </form>
     </td>
         </table>
     </div>
 </div>
             </c:if>--%>

</c:if>

<%@ include file="../include/footer.jsp" %>
<%-- <jsp:include page="../include/footer.jsp"/>--%>
</body>
</html>



