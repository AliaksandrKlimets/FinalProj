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

    <fmt:message bundle="${loc}" key="locale.order.user" var="user"/>
    <fmt:message bundle="${loc}" key="locale.order.car" var="car"/>
    <fmt:message bundle="${loc}" key="locale.order.passport.data" var="data"/>
    <fmt:message bundle="${loc}" key="locale.order.service.start" var="start"/>
    <fmt:message bundle="${loc}" key="locale.order.service.end" var="end"/>
    <fmt:message bundle="${loc}" key="locale.order.service.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.order.all.orders" var="all"/>
    <fmt:message bundle="${loc}" key="locale.order.new.orders" var="newOrders"/>
    <fmt:message bundle="${loc}" key="locale.order.accept" var="accept"/>
    <fmt:message bundle="${loc}" key="locale.order.decline" var="decline"/>
    <fmt:message bundle="${loc}" key="locale.order.decline.reason" var="reason"/>
    <fmt:message bundle="${loc}" key="locale.order.order.state" var="orderState"/>
    <fmt:message bundle="${loc}" key="locale.order.payment.state" var="payment"/>
    <fmt:message bundle="${loc}" key="locale.order.paid" var="paid"/>
    <fmt:message bundle="${loc}" key="locale.order.status.accept" var="accepted"/>
    <fmt:message bundle="${loc}" key="locale.order.status.decline" var="declined"/>
    <fmt:message bundle="${loc}" key="locale.order.pay" var="pay"/>
    <fmt:message bundle="${loc}" key="locale.order.waiting" var="wait"/>
    <fmt:message bundle="${loc}" key="locale.nothing.to.show" var="nothing"/>
    <fmt:message bundle="${loc}" key="locale.order.unpaid" var="unpaid"/>
    <fmt:message bundle="${loc}" key="locale.confirm.decline.order" var="confirmDecline"/>
    <fmt:message bundle="${loc}" key="locale.confirm.delete.order" var="confirmDelete"/>
    <fmt:message bundle="${loc}" key="locale.cost.byn" var="byn"/>
    <script src="${pageContext.request.contextPath}/assets/js/confirm.js"></script>

</head>
<body>
<div style="min-height: 500px; width: 1000px; padding: 0; margin: 0;">
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <div style="width: 650px; height: 70px;">
        <ul>
            <li style="margin: 0; padding: 0;">
                <form class="head-button" action="${pageContext.request.contextPath}/rental" method="get">
                    <input type="hidden" name="command" value="SHOW_ALL_ORDERS">
                    <input type="hidden" name="number" value="1">
                    <input type="submit" value="${all}">
                </form>
            </li>
            <li style="margin: 0; padding: 0;">
                <form class="head-button" action="${pageContext.request.contextPath}/rental" method="get">
                    <input type="hidden" name="command" value="SHOW_NEW_ORDERS">
                    <input type="hidden" name="number" value="1">
                    <input type="submit" value="${newOrders}">
                </form>
            </li>
        </ul>

    </div>
    </c:if>
    <c:choose>
    <c:when test="${fn:length(requestScope.orders) gt 0}">
    <c:forEach items="${requestScope.orders}" var="order">
    <div class="order-item">

        <div style="float: right;">
            <table class="order-table">
                <tr class="thead">
                    <th>id</th>
                    <th>${user}</th>
                    <th style="width: 350px;">${data}</th>
                    <th>${car}</th>
                    <th>${start}</th>
                    <th>${end}</th>
                    <th>${cost}</th>
                </tr>
                <tr>
                    <th>${order.orderId}</th>
                    <th><ahs:login-by-id userId="${order.userId}"/></th>
                    <th>${order.passportNumber}<br>${order.identificationNumber}<br><ahs:date-locale date="${order.dateOfExpiry}" locale="ru"/></th>
                    <th><ahs:model-by-id carId="${order.carId}"/></th>
                    <th><ahs:date-locale date="${order.serviceStart}" locale="ru"/></th>
                    <th><ahs:date-locale date="${order.serviceEnd}" locale="ru"/></th>
                    <th>${order.serviceCost} ${byn}</th>
                </tr>
            </table>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <c:choose>
                        <c:when test="${order.orderState eq 'WAITING'}">
                            <ul style="padding: 0;">
                                <li ><form class="order-button" action="${pageContext.request.contextPath}/rental" method="post">
                                    <input type="hidden" name="command" value="CHANGE_ORDER_STATE">
                                    <input type="hidden" name="change" value="ACCEPT">
                                    <input type="hidden" name="id" value="${order.orderId}">
                                    <input type="hidden" name="number" value="${requestScope.page.current}">
                                    <input type="submit" style="background-color: green;" value="${accept}">
                                </form></li>
                                <li ><form class="order-button" action="${pageContext.request.contextPath}/rental" method="post">
                                    <select class="order-select" name="reason">
                                        <option value="Слишком молод(а)">Слишком молод(а)</option>
                                        <option value="Ты мне не нравишься">Ты мне не нравишься</option>
                                        <option value="Плохая репутация в нашей фирме">Плохая репутация в нашей фирме</option>
                                        <option value="Плохая репутация в нашей фирме">Близок срок истечения</option>
                                    </select>
                                    <input type="hidden" name="command" value="CHANGE_ORDER_STATE">
                                    <input type="hidden" name="change" value="DECLINE">
                                    <input type="hidden" name="id" value="${order.orderId}">
                                    <input type="hidden" name="number" value="${requestScope.page.current}">
                                    <input type="submit" onclick="return confirmAction('${confirmDecline}')" style="background-color: darkred;" value="${decline}">
                                </form></li>
                            </ul>
                        </c:when>
                        <c:when test="${order.orderState eq 'ACCEPT'}">
                            <c:if test="${order.paymentState eq 'UNPAID'}">
                                <ul style="padding: 0;">
                                    <li ><form><label style="color: black; font-size: 18px;">${orderState}: ${accepted}</label></form></li>
                                    <li > <form class="order-button" action="${pageContext.request.contextPath}/rental" method="post">
                                        <input type="hidden" name="command" value="CHANGE_ORDER_PAYMENT_STATE">
                                        <input type="hidden" name="id" value="${order.orderId}">
                                        <input type="hidden" name="number" value="${requestScope.page.current}">
                                        <input type="submit" style="background-color: green;" value="${pay}">
                                    </form></li>
                                </ul>
                            </c:if>
                            <c:if test="${order.paymentState eq 'PAID'}">
                                <ul style="padding: 0;">
                                    <li > <form><label style="color: black; font-size: 18px;">${orderState}: ${accepted}</label></form></li>
                                    <li > <form><label style="color: black; font-size: 18px;">${payment}: ${paid}</label></form></li>
                                </ul>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <ul style="padding: 0;">
                                <li ><form><label style="color: black; font-size: 18px;">${orderState}: ${declined}</label></form></li>
                                <li ><form><label style="color: black; font-size: 18px;">${reason}: ${order.declineReason}</label></form></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'USER'}">
                    <c:choose>
                        <c:when test="${order.orderState eq 'WAITING' and order.paymentState eq 'UNPAID'}">
                            <ul style="padding: 0;">
                                <li > <form><label style="color: black; font-size: 18px;">${orderState}: ${wait}</label></form></li>
                                <li > <form><label style="color: black; font-size: 18px;">${payment}: ${unpaid}</label></form></li>
                                <li > <form class="order-button" action="${pageContext.request.contextPath}/rental" method="post">
                                    <input type="hidden" name="command" value="ORDER_DELETING">
                                    <input type="hidden" name="number" value="${requestScope.page.current}">
                                    <input type="hidden" name="id" value="${order.orderId}">
                                    <input type="hidden" name="userId" value="${sessionScope.user.userId}">
                                    <input type="submit" onclick="return confirmAction('${confirmDelete}')" style="background-color: green;" value="${decline}">
                                </form></li>
                            </ul>
                        </c:when>
                        <c:when test="${order.orderState eq 'ACCEPT' and order.paymentState eq 'UNPAID'}">
                            <ul style="padding: 0;">
                                <li > <form><label style="color: black; font-size: 18px;">${orderState}: ${accepted}</label></form></li>
                                <li > <form><label style="color: black; font-size: 18px;">${payment}: ${unpaid}</label></form></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                                <ul style="padding: 0;">
                                    <li > <form><label style="color: black; font-size: 18px;">${orderState}: ${accepted}</label></form></li>
                                    <li > <form><label style="color: black; font-size: 18px;">${payment}: ${paid}</label></form></li>
                                </ul>
                        </c:otherwise>
                    </c:choose>
                </c:if>
        </div>
            <c:choose>
            <c:when test="${order.orderState eq 'ACCEPT'}">
                    <div style="width: 20px; height: 220px; background-color: forestgreen;"></div>
            </c:when>
            <c:when test="${order.orderState eq 'DECLINE'}">
                    <div style="width: 20px; height: 220px; background-color: #A61200;"></div>
            </c:when>
            <c:otherwise>
                    <div style="width: 20px; height: 220px; background-color: grey;"></div>
            </c:otherwise>

            </c:choose>
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
