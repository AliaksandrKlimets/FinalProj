<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ahs" uri="rentalTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>



</head>
<body>
<div class="content">
    <c:if test="${fn:length(requestScope.orders) gt 0}">
        <c:forEach items="${requestScope.orders}" var="order">
            <c:out value="${order.orderId}"/>
        </c:forEach>
    </c:if>
</div>
</body>
</html>