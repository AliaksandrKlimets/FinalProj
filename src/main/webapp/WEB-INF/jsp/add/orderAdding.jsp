<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ahs" uri="rentalTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adding.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">


    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.order.passport.number" var="passport"/>
    <fmt:message bundle="${loc}" key="locale.order.passport.id" var="idNumber"/>
    <fmt:message bundle="${loc}" key="locale.order.passport.expiry" var="expiry"/>
    <fmt:message bundle="${loc}" key="locale.order.user" var="user"/>
    <fmt:message bundle="${loc}" key="locale.order.car" var="car"/>
    <fmt:message bundle="${loc}" key="locale.order.service.start" var="start"/>
    <fmt:message bundle="${loc}" key="locale.order.service.end" var="end"/>
    <fmt:message bundle="${loc}" key="locale.order.add.order" var="add"/>

    <script src="${pageContext.request.contextPath}/assets/js/date.js"></script>
</head>
<body>
<div class="content">


    <div style="width: 800px; min-height: 650px; margin: 0 0 0 19px; padding: 0">
        <form class="car-adding" action="${pageContext.request.contextPath}/rental" method="post">
            <input type="hidden" name="command" value="ORDER_ADDING">
            <input type="hidden" name="id" value="${requestScope.id}">
            <input type="hidden" name="userId" value="${sessionScope.user.userId}">

            <label class="input-label">${user}: ${sessionScope.user.login}</label><br><br>
            <label class="input-label">${car}: <ahs:model-by-id carId="${requestScope.id}"/></label><br><br>

            <label class="input-label">${passport}:</label><br>
            <input type="text" name="passportNumber" placeholder="${passport}" pattern="[A-Z]{2}[\d]{7}" minlength="3" maxlength="10" required><br>

            <label class="input-label">${idNumber}:</label><br>
            <input type="text" name="idNumber" placeholder="${idNumber}" pattern="[A-Z\d]{14}" minlength="4" maxlength="14" required><br>

            <label class="input-label">${expiry}:</label><br>
            <input type="date" name="dateOfExpiry" required><br>

            <label class="input-label">${start}:</label><br>
            <input id="serviceStart" type="date"  onclick="return getNow()" name="serviceStart" required><br>

            <label class="input-label">${end}:</label><br>
            <input  id="serviceEnd" type="date"  onclick="return getSecondTime()" name="serviceEnd" required><br><br>

            <input type="submit" value="${add}">
            <h1 style="font-size: 15px;color: white; width: 200px; height: 50px; margin-left: 200px; margin-top: 10px; text-align: center;">${requestScope.error}</h1>
        </form>
    </div>
</div>

</body>
</html>