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

    <fmt:message bundle="${loc}" key="locale.car.model" var="model"/>
    <fmt:message bundle="${loc}" key="locale.car.year" var="year"/>
    <fmt:message bundle="${loc}" key="locale.car.car.type" var="carType"/>
    <fmt:message bundle="${loc}" key="locale.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.cost.byn" var="byn"/>
    <fmt:message bundle="${loc}" key="locale.side.bar.all.orders" var="orders"/>
    <fmt:message bundle="${loc}" key="locale.confirm.delete.car" var="confirm"/>
    <fmt:message bundle="${loc}" key="locale.car.type.coupe" var="coupe"/>
    <fmt:message bundle="${loc}" key="locale.car.type.crossover" var="crossover"/>
    <fmt:message bundle="${loc}" key="locale.car.type.pickup" var="pickup"/>
    <fmt:message bundle="${loc}" key="locale.car.type.universal" var="universal"/>
    <fmt:message bundle="${loc}" key="locale.car.type.hatchback" var="hatchback"/>
    <fmt:message bundle="${loc}" key="locale.all" var="all"/>
    <fmt:message bundle="${loc}" key="locale.nothing.to.show" var="nothing"/>
    <script src="${pageContext.request.contextPath}/assets/js/confirm.js"></script>
</head>
<body>
<div style="min-height: 500px; width: 1004px; padding: 0; margin: 0;">
    <div style="width: 1004px; height: 70px;">
        <ul>
            <li style="margin: 0; padding: 0;">
                <form class="head-button">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS">
                    <input type="hidden" name="number" value="1">
                    <input type="submit" style="width: 163px" value="${all}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS_BY_TYPE">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="type" value="HATCHBACK">
                    <input type="submit" style="width: 161px;" value="${hatchback}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS_BY_TYPE">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="type" value="COUPE">
                    <input type="submit" style="width: 162px;" value="${coupe}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS_BY_TYPE">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="type" value="PICKUP">
                    <input type="submit" style="width: 163px;" value="${pickup}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS_BY_TYPE">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="type" value="CROSSOVER">
                    <input type="submit" style="width: 164px;" value="${crossover}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS_BY_TYPE">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="type" value="UNIVERSAL">
                    <input type="submit" style="width: 167px;" value="${universal}">
                </form>
            </li>
        </ul>
    </div>
    <c:choose>
        <c:when test="${fn:length(requestScope.carList) gt 0}">
            <table>
                <tr class="thead">
                    <th style="width: 30px;">id</th>
                    <th style="width: 230px;">${model}</th>
                    <th style="width: 130px;">${year}</th>
                    <th style="width: 180px;">${carType}</th>
                    <th style="width: 120px;">1</th>
                    <th style="width: 100px;">2-7</th>
                    <th style="width: 100px;">8-15</th>
                    <th style="width: 100px;">16+</th>
                    <th style="width: 100px; text-align: center;">${action}</th>
                </tr>

                <c:forEach items="${requestScope.carList}" var="car">
                    <tr>
                        <th>${car.carId}</th>
                        <th>${car.model}</th>
                        <th>${car.year}</th>
                        <th>${car.type.value}</th>
                        <th>${car.costPerDay} ${byn}</th>
                        <th>${car.twoToSevenDays} ${byn}</th>
                        <th>${car.eightToFifteen} ${byn}</th>
                        <th>${car.sixteenAndMore} ${byn}</th>
                        <th>
                            <form class="button" action="${pageContext.request.contextPath}/rental" method="get">
                                <input type="hidden" name="command" value="SHOW_CAR_ORDERS">
                                <input type="hidden" name="number" value="1">
                                <input type="hidden" name="numberPage" value="${requestScope.page.current}">
                                <input type="hidden" name="id" value="${car.carId}">
                                <input type="submit" style="margin-top: 20px;"  value="${orders}">
                            </form>
                            <form class="button" action="${pageContext.request.contextPath}/rental" method="get">
                                <input type="hidden" name="command" value="CAR_DELETING">
                                <input type="hidden" name="number" value="${requestScope.page.current}">
                                <input type="hidden" name="id" value="${car.carId}">
                                <input type="submit" style="background-color: #A61200" onclick="return confirmAction('${confirm}')" value="${delete}">
                            </form>

                        </th>

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
