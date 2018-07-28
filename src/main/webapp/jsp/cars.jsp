<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.cars.title" var="title"/>
    <title>${title}</title>

</head>
<body>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <jsp:forward page="${pageContext.request.contextPath}/home"/>
</c:if>
<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <jsp:include page="/WEB-INF/jsp/header/userHeader.jsp"/>
    </c:when>
    <c:when test="${empty sessionScope.user}">
        <jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
    </c:when>
</c:choose>
<div style="height: 900px;">
    <p>Car page</p>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>