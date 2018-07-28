<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header-style.css">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <ftm:message bundle="${loc}" key="locale.nav.main.page" var="main"/>
    <ftm:message bundle="${loc}" key="locale.nav.car.park" var="park"/>
    <ftm:message bundle="${loc}" key="locale.nav.rent.rules" var="rules"/>
    <ftm:message bundle="${loc}" key="locale.nav.user.home" var="home"/>
    <ftm:message bundle="${loc}" key="locale.nav.user.logout" var="logOut"/>
</head>
<body style="margin: 0">
<div class="header">
    <div class="header-content">
        <div class="right-content">
            <ul>
                <a href="${pageContext.request.contextPath}/home"><li class="hov">${home}</li></a>
                <li style="margin: 0; padding: 0px;">|</li>
                <li style="padding-top: 0;" class="form-button"><form action="${pageContext.request.contextPath}/rental" method="post">
                    <input type="hidden" name="command" value="LOG_OFF">
                    <input type="submit" value="${logOut}">
                </form></li>
            </ul>
        </div>
        <div class="left-content">
            <ul>
                <a href="${pageContext.request.contextPath}/main"><li class="hov">${main}</li></a>
                <li style="padding-top: 0;"><form action="${pageContext.request.contextPath}/rental" method="post">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS">
                    <input type="submit" value="${park}">
                </form></li>
                <a href="${pageContext.request.contextPath}/rules"><li class="hov">${rules}</li></a>
            </ul>
        </div>
    </div>
</div>
</body>
</html>