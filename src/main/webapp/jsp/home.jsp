<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ahs" uri="rentalTag" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-16x16.png" sizes="16x16" />
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.home.title" var="title"/>
    <fmt:message bundle="${loc}" key="locale.nothing.to.show" var="nothing"/>
    <title>${title}</title>

</head>
<body>
<c:if test="${empty sessionScope.user}">
    <jsp:forward page="${pageContext.request.contextPath}/login"/>
</c:if>
<c:choose>
    <c:when test="${sessionScope.user.role eq 'USER'}">
        <jsp:include page="/WEB-INF/jsp/header/userHeader.jsp"/>
    </c:when>
    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
        <jsp:include page="/WEB-INF/jsp/header/adminHeader.jsp"/>
    </c:when>
</c:choose>
<div class="content">
    <c:choose>
        <c:when test="${sessionScope.user.role eq 'USER'}">
            <jsp:include page="/WEB-INF/jsp/sidebar/userSideBar.jsp"/>
            <c:choose>
                <c:when test="${ requestScope.orders ne null}">
                    <jsp:include page="/WEB-INF/jsp/table/ordersTable.jsp"/>
                </c:when>
                <c:when test="${requestScope.fines ne null}">
                    <jsp:include page="/WEB-INF/jsp/table/finesTable.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="/WEB-INF/jsp/table/user-table.jsp"/>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test="${sessionScope.user.role eq 'ADMIN'}">
            <jsp:include page="/WEB-INF/jsp/sidebar/adminSideBar.jsp"/>
            <c:choose>
                <c:when test="${ requestScope.orders ne null}">
                    <jsp:include page="/WEB-INF/jsp/table/ordersTable.jsp"/>
                </c:when>
                <c:when test="${requestScope.fines ne null}">
                    <jsp:include page="/WEB-INF/jsp/table/finesTable.jsp"/>
                </c:when>
                <c:when test="${requestScope.users ne null}">
                    <jsp:include page="/WEB-INF/jsp/table/usersTable.jsp"/>
                </c:when>
                <c:when test="${requestScope.carOrders ne null}">
                    <jsp:include page="/WEB-INF/jsp/table/carOrdersTable.jsp"/>
                </c:when>
            </c:choose>
        </c:when>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>