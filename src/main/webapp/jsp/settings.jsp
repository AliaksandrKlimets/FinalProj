<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/side-bar-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header-style.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.home.title" var="title"/>
    <title>${title}</title>

</head>
<body>
<c:if test = "${empty sessionScope.user}">
    <jsp:forward page="${pageContext.request.contextPath}/login" />
</c:if>
<c:choose>
    <c:when test="${sessionScope.user.role eq 'USER'}">
        <jsp:include page="/WEB-INF/jsp/header/userHeader.jsp"/>
    </c:when>
    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
        <jsp:forward page="${pageContext.request.contextPath}/home" />
    </c:when>
</c:choose>
<div class="content">
    <c:if test="${sessionScope.user.role eq 'USER'}">
        <jsp:include page="/WEB-INF/jsp/sidebar/userSideBar.jsp"/>
    </c:if>
    <h1>Settings page</h1>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>