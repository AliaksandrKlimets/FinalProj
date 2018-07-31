<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/side-bar-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header-style.css">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.side.bar.profile" var="profile"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.all.users" var="users"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.all.cars" var="cars"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.all.orders" var="orders"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.all.fines" var="fines"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.add.car" var="addCar"/>

</head>
<body style="margin: 0">
<div class="side-bar">
    <ul>

        <a style="margin: 0; padding: 0; width: 300px; height: 50px; color: white;" href="${pageContext.request.contextPath}/home">
            <li class="side-bar-nav" style="color: white;">
                ${profile}
            </li>
        </a>
        <li class="side-bar-item">
            <form class="" action="${pageContext.request.contextPath}/rental">
                <input type="hidden" name="command" value="SHOW_ALL_USERS">
                <input type="submit"  value="${users}">
            </form>
        </li>
        <li class="side-bar-item">
            <form class="" action="${pageContext.request.contextPath}/rental">
                <input type="hidden" name="command" value="SHOW_ALL_CARS">
                <input type="submit"  value="${cars}">
            </form>
        </li>
        <li class="side-bar-item">
            <form class="" action="${pageContext.request.contextPath}/rental">
                <input type="hidden" name="command" value="SHOW_ALL_ORDERS">
                <input type="hidden" name="number" value="1">
                <input type="submit"  value="${orders}">
            </form>
        </li>
        <li class="side-bar-item">
            <form class="" action="${pageContext.request.contextPath}/rental">
                <input type="hidden" name="command" value="SHOW_ALL_FINES">
                <input type="hidden" name="number" value="1">
                <input type="submit"  value="${fines}">
            </form>
        </li>
        <li class="side-bar-item">
            <form class="" action="${pageContext.request.contextPath}/rental">
                <input type="hidden" name="command" value="ADD_CAR">
                <input type="submit"  value="${addCar}">
            </form>
        </li>
    </ul>
    </form>
</div>
</body>
</html>