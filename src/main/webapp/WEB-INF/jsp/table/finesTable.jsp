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

    <fmt:message bundle="${loc}" key="locale.fine.user.id" var="userId"/>
    <fmt:message bundle="${loc}" key="locale.fine.car.id" var="carId"/>
    <fmt:message bundle="${loc}" key="locale.fine.cause" var="cause"/>
    <fmt:message bundle="${loc}" key="locale.fine.repair.bill" var="bill"/>
    <fmt:message bundle="${loc}" key="locale.fine.due.date" var="date"/>
    <fmt:message bundle="${loc}" key="locale.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.fine.paid" var="paid"/>
    <fmt:message bundle="${loc}" key="locale.fine.unpaid" var="unpaid"/>
    <fmt:message bundle="${loc}" key="locale.fine.pay" var="pay"/>
    <fmt:message bundle="${loc}" key="locale.register.label.login" var="login"/>
    <fmt:message bundle="${loc}" key="locale.car.model" var="model"/>
    <fmt:message bundle="${loc}" key="locale.fine.all.fines" var="allFines"/>
    <fmt:message bundle="${loc}" key="locale.fine.unpaid.fines" var="unpaidFines"/>
    <fmt:message bundle="${loc}" key="locale.cost.byn" var="byn"/>
    <fmt:message bundle="${loc}" key="locale.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.nothing.to.show" var="nothing"/>
    <fmt:message bundle="${loc}" key="locale.confirm.delete.fine" var="confirm"/>
    <script src="${pageContext.request.contextPath}/assets/js/confirm.js"></script>
</head>
<body>
<div style="min-height: 500px; width: 1000px; padding: 0; margin: 0;">
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <div style="width: 650px; height: 70px;">
                <ul>
                    <li style="margin: 0; padding: 0;">
                        <form class="head-button">
                            <input type="hidden" name="command" value="SHOW_ALL_FINES">
                            <input type="hidden" name="number" value="1">
                            <input type="submit" value="${allFines}">
                        </form>
                    </li>
                    <li style="margin: 0; padding: 0;">
                        <form class="head-button">
                            <input type="hidden" name="command" value="SHOW_UNPAID_FINES">
                            <input type="hidden" name="number" value="1">
                            <input type="submit" value="${unpaidFines}">
                        </form>
                    </li>
                </ul>
            </div>
        </c:if>
    <c:choose>
        <c:when test="${fn:length(requestScope.fines) gt 0}">
            <table>
                <tr class="thead">
                    <th style="width: 50px;">id</th>
                    <th style="width: 150px;">${login}</th>
                    <th style="width: 150px;">${model}</th>
                    <th style="width: 200px;">${bill}</th>
                    <th style="width: 300px;">${cause}</th>
                    <th width="120px">${date}</th>
                    <th style="width: 150px; text-align: center;">${action}</th>
                </tr>
                <c:forEach items="${requestScope.fines}" var="fine">
                    <tr>
                        <th style="width: 30px;">${fine.fineId}</th>
                        <th><ahs:login-by-id userId="${fine.userId}"/></th>
                        <th style="width: 120px;"><ahs:model-by-id carId="${fine.carId}"/></th>
                        <th>${fine.repairBill} ${byn}</th>
                        <th style="width: 200px;">${fine.cause}</th>
                        <th style="width: 100px;"><ahs:date-locale locale="ru" date="${fine.dueDate}"/></th>
                        <c:choose>
                            <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                                <c:choose>
                                    <c:when test="${fine.state eq 'UNPAID'}">
                                        <th style="width: 150px;">
                                            <form class="button" action="${pageContext.request.contextPath}/rental"
                                                  method="post">
                                                <input type="hidden" name="command" value="CHANGE_FINE_PAYMENT_STATE">
                                                <input type="hidden" name="id" value="${fine.fineId}">
                                                <input type="hidden" name="number" value="${requestScope.page.current}">
                                                <input type="submit" style="margin-top: 20px;" value="${pay}">
                                            </form>

                                            <form class="button" action="${pageContext.request.contextPath}/rental"
                                                  method="post">
                                                <input type="hidden" name="command" value="FINE_DELETING">
                                                <input type="hidden" name="id" value="${fine.fineId}">
                                                <input type="hidden" name="number" value="${requestScope.page.current}">
                                                <input type="submit" onclick="return confirmAction('${confirm}')"
                                                       style="background-color: #A61200;" value="${delete}">
                                            </form>
                                        </th>
                                    </c:when>
                                    <c:when test="${fine.state eq 'PAID'}">
                                        <th style="background-color: lightskyblue; width: 150px; height: 69px; text-align: center;">${paid}</th>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${sessionScope.user.role eq 'USER'}">
                                <c:choose>
                                    <c:when test="${fine.state eq 'UNPAID'}">
                                        <th style="background-color: darkred; color: white; width: 150px; height: 50px; text-align: center;">${unpaid}</th>
                                    </c:when>
                                    <c:when test="${fine.state eq 'PAID'}">
                                        <th style="background-color: lightskyblue; width: 150px; height: 50px; text-align: center;">${paid}</th>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
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
