<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ahs" uri="rentalTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.order.service.start" var="start"/>
    <fmt:message bundle="${loc}" key="locale.order.service.end" var="end"/>
    <fmt:message bundle="${loc}" key="locale.order.user" var="user"/>
    <fmt:message bundle="${loc}" key="locale.order.car" var="car"/>
    <fmt:message bundle="${loc}" key="locale.nothing.to.show" var="nothing"/>
    <fmt:message bundle="${loc}" key="locale.back" var="back"/>
    <fmt:message bundle="${loc}" key="locale.all" var="all"/>
    <fmt:message bundle="${loc}" key="locale.actual" var="actual"/>

    <script src="${pageContext.request.contextPath}/assets/js/confirm.js"></script>
</head>
<body>
<div style="min-height: 500px; width: 1000px; padding: 0; margin: 0;">
    <div style="width: 850px; height: 70px; ">
        <ul>
            <li style="margin: 0; padding: 0;">
        <form class="head-button" action="${pageContext.request.contextPath}/rental" method="get">
            <input type="hidden" name="command" value="SHOW_ALL_CARS">
            <input type="hidden" name="number" value="${requestScope.numberPage}">
            <input type="submit" style="width: 170px" value="${back}">
        </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button" action="${pageContext.request.contextPath}/rental" method="get">
                    <input type="hidden" name="command" value="SHOW_CAR_ORDERS">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="numberPage" value="${requestScope.numberPage}">
                    <input type="hidden" name="id" value="${requestScope.id}">
                    <input type="submit" style="width: 170px" value="${all}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button" action="${pageContext.request.contextPath}/rental" method="get">
                    <input type="hidden" name="command" value="SHOW_ACTUAL_CAR_ORDERS">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="numberPage" value="${requestScope.numberPage}">
                    <input type="hidden" name="id" value="${requestScope.id}">
                    <input type="submit" style="width: 170px" value="${actual}">
                </form>
            </li>
        </ul>
    </div>
<c:choose>
    <c:when test="${fn:length(requestScope.carOrders) gt 0}">
         <table style="width: 850px;">
        <tr class="thead">
            <th>Id</th>
            <th>${car}</th>
            <th>${user}</th>
            <th>${start}</th>
            <th>${end}</th>
        </tr>
        <c:forEach items="${requestScope.carOrders}" var="order">
        <tr>
            <th>${order.carOrderId}</th>
            <th><ahs:model-by-id carId="${order.carId}"/></th>
            <th><ahs:login-by-id userId="${order.userId}"/></th>
            <th><ahs:date-locale date="${order.beginDate}" locale="ru"/></th>
            <th><ahs:date-locale date="${order.endDate}" locale="ru"/></th>
        </tr>
        </c:forEach>
    </table>

    </c:when>
    <c:otherwise>
        <h1 style="font-family: Calibri; margin-left: 19px; font-size: 19px;">${nothing}</h1>
    </c:otherwise>
</c:choose>
    <c:if test="${not empty requestScope.page}">
        <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
    </c:if>
</div>
</body>
</html>
