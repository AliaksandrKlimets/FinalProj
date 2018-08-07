<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/side-bar-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header-style.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.cars.title" var="title"/>
    <title>${title}</title>

</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user.role eq 'USER'}">
        <jsp:include page="/WEB-INF/jsp/header/userHeader.jsp"/>
    </c:when>
    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
        <jsp:include page="/WEB-INF/jsp/header/adminHeader.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
    </c:otherwise>
</c:choose>
<div class="content">
    <c:choose>
        <c:when test="${sessionScope.user.role eq 'USER'}">
            <jsp:include page="/WEB-INF/jsp/sidebar/userSideBar.jsp"/>
            <c:if test="${requestScope.carList ne null}">
                <jsp:include page="/WEB-INF/jsp/carList.jsp"/>
            </c:if>
        </c:when>
        <c:when test="${ sessionScope.user.role eq 'ADMIN'}">
            <jsp:include page="/WEB-INF/jsp/sidebar/adminSideBar.jsp"/>
            <c:if test="${empty requestScope.carList}">
                <h1>Список пуст</h1>
            </c:if>
            <c:if test="${ not empty requestScope.carList}">
                <jsp:include page="/WEB-INF/jsp/table/carTable.jsp"/>
            </c:if>
        </c:when>
        <c:otherwise>
            <c:if test="${requestScope.carList ne null}">
                <jsp:include page="/WEB-INF/jsp/carList.jsp"/>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>