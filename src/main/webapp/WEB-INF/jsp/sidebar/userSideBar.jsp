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
    <fmt:message bundle="${loc}" key="locale.side.bar.orders" var="orders"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.fines" var="fines"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.change.info" var="change"/>

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
                <input type="hidden" name="command" value="USER_ORDERS">
                <input type="hidden" name="number" value="1">
                <input type="submit" value="${orders}">
            </form>
        </li>
        <li class="side-bar-item">
            <form class="" action="${pageContext.request.contextPath}/rental">
                <input type="hidden" name="command" value="USER_FINES">
                <input type="hidden" name="number" value="1">
                <input type="submit"  value="${fines}">
            </form>
        </li>
        <a style="margin: 0; padding: 0; width: 300px; height: 50px; color: white;" href="${pageContext.request.contextPath}/settings">
            <li class="side-bar-nav" style="color: white;">
                ${change}
            </li>
        </a>
    </ul>
    </form>
</div>
</body>
</html>