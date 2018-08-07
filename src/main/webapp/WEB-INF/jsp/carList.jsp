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

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.nothing.to.show" var="nothing"/>
    <fmt:message bundle="${loc}" key="locale.car.car.type" var="carType"/>
    <fmt:message bundle="${loc}" key="locale.car.fuel.type" var="fuelType"/>
    <fmt:message bundle="${loc}" key="locale.car.consumption" var="consumption"/>
    <fmt:message bundle="${loc}" key="locale.car.capacity" var="capacity"/>
    <fmt:message bundle="${loc}" key="locale.car.transmission" var="transmission"/>
    <fmt:message bundle="${loc}" key="locale.car.add.info" var="info"/>
    <fmt:message bundle="${loc}" key="locale.rent" var="rent"/>
    <fmt:message bundle="${loc}" key="locale.car.rent.rule" var="rule"/>
    <fmt:message bundle="${loc}" key="locale.duration" var="duration"/>
    <fmt:message bundle="${loc}" key="locale.cost.byn" var="byn"/>
    <fmt:message bundle="${loc}" key="locale.day" var="day"/>
    <fmt:message bundle="${loc}" key="locale.order.service.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.car.type.coupe" var="coupe"/>
    <fmt:message bundle="${loc}" key="locale.car.type.crossover" var="crossover"/>
    <fmt:message bundle="${loc}" key="locale.car.type.pickup" var="pickup"/>
    <fmt:message bundle="${loc}" key="locale.car.type.universal" var="universal"/>
    <fmt:message bundle="${loc}" key="locale.car.type.hatchback" var="hatchback"/>
    <fmt:message bundle="${loc}" key="locale.all" var="all"/>

</head>
<body>
<div style="min-height: 500px; width: 1000px; padding: 0; margin: 0;">
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
                    <input type="submit" style="width: 163px;" value="${hatchback}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button">
                    <input type="hidden" name="command" value="SHOW_ALL_CARS_BY_TYPE">
                    <input type="hidden" name="number" value="1">
                    <input type="hidden" name="type" value="COUPE">
                    <input type="submit" style="width: 164px;" value="${coupe}">
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
            <c:forEach items="${requestScope.carList}" var="car">
                <div class="car-item">
                    <div class="car-item-content">
                        <table class="car-table" style="border: 0;">
                            <tr>
                                <th class="car-th" style="width: 270px;">${duration} (${day})</th>
                                <th class="car-th">1</th>
                                <th class="car-th">2-7</th>
                                <th class="car-th">8-15</th>
                                <th class="car-th">16+</th>
                            </tr>
                            <tr>
                                <th class="car-th" style="width: 270px;">${cost} (${byn})</th>
                                <th class="car-th">${car.costPerDay}</th>
                                <th class="car-th">${car.twoToSevenDays}</th>
                                <th class="car-th">${car.eightToFifteen}</th>
                                <th class="car-th">${car.sixteenAndMore}</th>
                            </tr>
                        </table>
                        <div style="min-height: 226px; width: 550px;">
                        <table class="car-table">
                            <tr class="car-tr">
                                <th style="width: 270px">${carType}</th>
                                <th >${car.type.value}</th>
                            </tr>
                            <tr class="car-tr">
                                <th style="width: 270px">${fuelType}:</th>
                                <th >${car.fuelType.value}</th>
                            </tr>
                            <tr class="car-tr">
                                <th style="width: 270px">${consumption}:</th>
                                <th >${car.consumption}</th>
                            </tr>
                            <tr class="car-tr">
                                <th style="width: 270px">${transmission}:</th>
                                <th >${car.transmission}</th>
                            </tr>
                            <tr class="car-tr">
                                <th style="width: 270px">${capacity}:</th>
                                <th >${car.engineCapacity}</th>
                            </tr>
                            <tr class="car-tr">
                                <th style="width: 270px">${info}:</th>
                                <th >${car.addInfo}</th>
                            </tr>
                        </table>
                        </div>
                        <div style="width: 550px; height: 70px;">
                            <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <form class="rent-button" action="${pageContext.request.contextPath}/rental" method="get">
                                    <input type="hidden" name="command" value="ADDING_HELP">
                                    <input type="hidden" name="add_param" value="order">
                                    <input type="hidden" name="id" value="${car.carId}">
                                    <input type="submit" value="${rent}">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form class="rent-button">
                                    <label style="width: 400px; margin-top: 20px;">${rule}</label>
                                </form>
                            </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="car-item-img">
                        <h3 class="car-item-header">${car.model} ${car.year}</h3>
                        <img src="${car.image}" width="440" style="border-radius: 0 0 4px 4px; padding-left: 5px;">
                    </div>
                </div>
            </c:forEach>
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
