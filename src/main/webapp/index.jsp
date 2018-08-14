<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="icon" type="image/png" href="assets/favicon/favicon-32x32.png" sizes="32x32"/>
    <link rel="icon" type="image/png" href="assets/favicon/favicon-16x16.png" sizes="16x16"/>
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
<body >
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
<div class="content" style="width: 100%;min-height: 650px; margin: 0 auto 0 auto; border-bottom: 1px solid
        white; background-color: #ffffff;">
    <div style="width: 100%; height: 600px; background-image: url(${pageContext.request.contextPath}
            /assets/img/content3.jpg); background-size: 100%;">
    </div>
    <div style="width: 1250px; min-height:250px; margin: 0 auto 0 auto;">
        <h1 style="margin: 20px auto 5px auto; width: 400px; text-align: center; font-family: calibri; font-weight:
        normal; font-size: 33px;">${sidehead2}</h1>
        <table class="icon-table">
            <tr>
                <th class="icon-cell"><img src="${pageContext.request.contextPath}/assets/img/medal.png" width="70px">
                </th>
                <th class="icon-cell"><img src="${pageContext.request.contextPath}/assets/img/money.png" width="70px">
                </th>
                <th class="icon-cell"><img src="${pageContext.request.contextPath}/assets/img/car.png" width="70px">
                </th>
                <th class="icon-cell"><img src="${pageContext.request.contextPath}/assets/img/oil.png" width="70px">
                </th>
                <th class="icon-cell"><img src="${pageContext.request.contextPath}/assets/img/list.png" width="70px">
                </th>
            </tr>
            <tr>
                <th class="icon-cell">${sidesix}</th>
                <th class="icon-cell">${sideseventh}</th>
                <th class="icon-cell">${sideeighth}</th>
                <th class="icon-cell">${sideninth}</th>
                <th class="icon-cell">${sidetenth}</th>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
