<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <link rel="stylesheet" href="assets/css/style.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.index.content.head.first" var="head1"/>
    <fmt:message bundle="${loc}" key="locale.index.content.head.second" var="head2"/>
    <fmt:message bundle="${loc}" key="locale.index.content.head.third" var="head3"/>
    <fmt:message bundle="${loc}" key="locale.index.side.head.first" var="sidehead1"/>
    <fmt:message bundle="${loc}" key="locale.index.side.head.second" var="sidehead2"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.first" var="sidefirst"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.second" var="sidesecond"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.third" var="sidethird"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.fourth" var="sidefourth"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.fifth" var="sidefifth"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.sixth" var="sidesix"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.seventh" var="sideseventh"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.eighth" var="sideeighth"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.ninth" var="sideninth"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.tenth" var="sidetenth"/>
    <fmt:message bundle="${loc}" key="locale.index.content.text.first" var="text1"/>
    <fmt:message bundle="${loc}" key="locale.index.content.text.second" var="text2"/>
    <fmt:message bundle="${loc}" key="locale.index.content.text.third" var="text3"/>

    <fmt:message bundle="${loc}" key="locale.index.title" var="title"/>
    <title>${title}</title>
</head>
<html>
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
<div style=" height: 800px; width: 1400px; margin: 30px auto 20px auto;">
    <div class="index-side">
        <div class="index-side-elem">
            <h1 class="head-label-side">${sidehead1}</h1>
            <ul style="list-style-type: disc; padding-left: 30px; margin-top: 10px;">
                <li class="side-list">${sidefirst}</li>
                <li class="side-list">${sidesecond}</li>
                <li class="side-list">${sidethird}</li>
                <li class="side-list">${sidefourth}</li>
                <li class="side-list">${sidefifth}</li>
            </ul>
        </div>
        <div class="index-side-elem" style="margin-top: 40px;">
            <h1 class="head-label-side">${sidehead2}</h1>
            <ul style="list-style-type: disc; padding-left: 30px; margin-top: 10px;">
                <li class="side-list">${sidesix}</li>
                <li class="side-list">${sideseventh}</li>
                <li class="side-list">${sideeighth}</li>
                <li class="side-list">${sideninth}</li>
                <li class="side-list">${sidetenth}</li>
            </ul>
        </div>
    </div>
    <div class="index-left">
        <h1 class="head-label">${head1}</h1>
        <p class="text-content">
            ${text1}
        </p>
        <h1 class="head-label">
            ${head2}
        </h1>
        <p class="text-content">
            ${text2}
        </p>
        <h1 class="head-label">
            ${head3}
        </h1>
        <p class="text-content">
            ${text3}
        </p>

    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
