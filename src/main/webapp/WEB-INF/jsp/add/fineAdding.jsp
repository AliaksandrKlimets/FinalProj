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

    <fmt:message bundle="${loc}" key="locale.fine.car.id" var="carId"/>
    <fmt:message bundle="${loc}" key="locale.fine.cause" var="cause"/>
    <fmt:message bundle="${loc}" key="locale.fine.repair.bill" var="bill"/>
    <fmt:message bundle="${loc}" key="locale.fine.due.date" var="date"/>
    <fmt:message bundle="${loc}" key="locale.fine.fine.to.user" var="user"/>
    <fmt:message bundle="${loc}" key="locale.add.fine" var="add"/>

</head>
<body>
<div class="content">


    <div style="width: 800px; min-height: 650px; margin: 0 0 0 19px; padding: 0">
        <form class="car-adding" action="${pageContext.request.contextPath}/rental" method="post">
            <input type="hidden" name="command" value="ADD_USER_FINE">
            <input type="hidden" name="id" value="${requestScope.id}">
            <input type="hidden" name="name" value="${requestScope.name}">
            <label class="input-label">${user}: ${requestScope.name}</label><br><br>

            <label class="input-label">${carId}:</label><br>
            <input type="text" name="carId" placeholder="${carId}" pattern="[\d]+" required><br>

            <label class="input-label">${cause}:</label><br>
            <input type="text" name="cause" placeholder="${cause}" pattern="[a-zA-Zа-яА-Я\s]+" required><br>

            <label class="input-label">${bill}:</label><br>
            <input type="text" name="bill" placeholder="${bill}" pattern="[\d]+\.{0,1}[\d]{0,1}" required><br>

            <label class="input-label">${date}:</label><br>
            <input type="date" name="dueDate" min="2018-08-03" required><br><br>

            <input type="submit" value="${add}">
            <h1 style="font-size: 15px;color: white; width: 200px; margin-left: 200px;">${requestScope.error}</h1>
        </form>
    </div>
</div>

</body>
</html>
