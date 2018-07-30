<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.home.title" var="title"/>
    <title>${title}</title>

</head>
<body>
<c:if test = "${empty sessionScope.user}">
    <jsp:forward page="${pageContext.request.contextPath}/registration" />
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
            <jsp:include page="/WEB-INF/jsp/table/user-table.jsp"/>
        </c:when>
        <c:when test="${sessionScope.user.role eq 'ADMIN'}">
            <jsp:include page="/WEB-INF/jsp/sidebar/adminSideBar.jsp"/>
        </c:when>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>