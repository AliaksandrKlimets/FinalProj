<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.register.title" var="title"/>
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
<div style="height: 900px;">
    <p>Register page</p>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>