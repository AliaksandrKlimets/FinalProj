<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ahs" uri="rentalTag" %>

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
    <ftm:message bundle="${loc}" key="locale.header.you" var="you"/>
</head>
<body style="margin: 0">
<div class="header">
    <div class="header-content">
        <div class="right-content">
            <ul>
                <a href="${pageContext.request.contextPath}/home"><li class="hov">${home}</li></a>
                <li style="margin: 0; padding: 0px;">|</li>
                <li style="padding:0 0 0 19px;" class="form-button"><form action="${pageContext.request.contextPath}/rental" method="post">
                    <input type="hidden" name="command" value="LOG_OFF">
                    <input type="submit" value="${logOut}">
                </form></li>
            </ul>
        </div>
        <div class="left-content">
            <ul>
                <a href="${pageContext.request.contextPath}/main"><li class="hov">${main}</li></a>
                <li style="padding-top: 0;"><form action="${pageContext.request.contextPath}/rental" method="get">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS">
                    <input type="hidden" name="page" value="${pageContext.request.requestURL}"/>
                    <input type="hidden" name="param" value="${pageContext.request.queryString}"/>
                    <input type="submit" value="${park}">
                </form></li>
                <a href="${pageContext.request.contextPath}/rules"><li class="hov">${rules}</li></a>
                <li style="margin-left: 100px; width: 350px; text-align: center;">${you}: <ahs:full-name name="${sessionScope.user.name}" surname="${sessionScope.user.surname}"/></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>