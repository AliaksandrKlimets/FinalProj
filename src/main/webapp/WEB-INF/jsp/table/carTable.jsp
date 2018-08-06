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
    <fmt:message bundle="${loc}" key="locale.confirm.delete.car" var="confirm"/>
    <script src="${pageContext.request.contextPath}/assets/js/confirm.js"></script>
</head>
<body>
<div style="min-height: 500px; width: 1000px; padding: 0; margin: 0;">
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
                        <input type="hidden" name="command" value="CAR_DELETING">
                        <input type="hidden" name="number" value="${requestScope.page.current}">
                        <input type="hidden" name="id" value="${car.carId}">
                        <input type="submit" onclick="return confirmAction('${confirm}')" value="${delete}">
                    </form>
                    <h3>${requestScope.error}</h3>
                </th>

            </tr>
        </c:forEach>

    </table>
    <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
</div>
</body>
</html>
