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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.register.label.login" var="login"/>
    <fmt:message bundle="${loc}" key="locale.register.label.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.register.label.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.register.label.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="locale.register.label.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.register.label.birth" var="bitrh"/>
    <fmt:message bundle="${loc}" key="locale.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.action.add.fine" var="fine"/>
    <fmt:message bundle="${loc}" key="locale.nothing.to.show" var="nothing"/>

</head>
<body>
<div style="min-height: 500px; width: 1000px; padding: 0; margin: 0;">
    <c:choose>
        <c:when test="${fn:length(requestScope.users) gt 0}">
            <table>
                <tr class="thead">
                    <th style="width: 30px;">id</th>
                    <th style="width: 130px;">${login}</th>
                    <th style="width: 130px;">${name}</th>
                    <th style="width: 180px;">${surname}</th>
                    <th style="width: 220px;">${email}</th>
                    <th style="width: 100px;">${phone}</th>
                    <th style="width: 100px;">${bitrh}</th>
                    <th style="width: 100px; text-align: center;">${action}</th>
                </tr>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <th>${user.userId}</th>
                        <th>${user.login}</th>
                        <th>${user.name}</th>
                        <th>${user.surname}</th>
                        <th>${user.email}</th>
                        <th>${user.phone}</th>
                        <th><ahs:date-locale locale="ru" date="${user.birthDate}"/></th>
                        <th>
                            <form class="button" action="${pageContext.request.contextPath}/rental" method="get">
                                <input type="hidden" name="command" value="ADDING_HELP">
                                <input type="hidden" name="add_param" value="fine">
                                <input type="hidden" name="id" value="${user.userId}">
                                <input type="hidden" name="name" value="${user.login}">
                                <input type="submit" style="margin-top: 17px;" value="${fine}">
                            </form>
                        </th>

                    </tr>
                </c:forEach>

            </table>
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
