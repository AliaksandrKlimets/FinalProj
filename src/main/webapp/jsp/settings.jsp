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
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-16x16.png" sizes="16x16" />
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.settings.title" var="title"/>
    <fmt:message bundle="${loc}" key="locale.settings.email.change" var="changeEmail"/>
    <fmt:message bundle="${loc}" key="locale.settings.password.change" var="changePass"/>
    <fmt:message bundle="${loc}" key="locale.settings.phone.change" var="changePhone"/>
    <fmt:message bundle="${loc}" key="locale.settings.old.password" var="oldPass"/>
    <fmt:message bundle="${loc}" key="locale.settings.old.phone" var="oldPhone"/>
    <fmt:message bundle="${loc}" key="locale.settings.old.email" var="oldEmail"/>
    <fmt:message bundle="${loc}" key="locale.settings.new.phone" var="newPhone"/>
    <fmt:message bundle="${loc}" key="locale.settings.new.email" var="newEmail"/>
    <fmt:message bundle="${loc}" key="locale.settings.new.password" var="newPass"/>
    <fmt:message bundle="${loc}" key="locale.settings.change" var="change"/>
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
    <div class="settings">
        <form class="change-form" action="${pageContext.request.contextPath}/rental" method="post">
            <input type="hidden" name="command" value="CHANGING_EMAIL">
            <input type="hidden" name="id" value="${sessionScope.user.userId}">
            <div class="change-label">
                <label style="color: white; font-size: 23px; font-family: calibri;">${changeEmail}</label>
            </div>
            <div class="change-content">
                <label style="color: black; font-size: 23px; font-family: calibri;">${newEmail}:</label><br>
                <input type="text" name="newEmail" placeholder="${oldEmail}: ${sessionScope.user.email}"
                       pattern="^[\w._\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$" required>
                <input type="submit" value="${change}">
                <c:if test="${not empty requestScope.emailError}">
                    <br><h2 style="font-family: calibri; font-size: 17px; color:black; margin: 30px 0 10px 0; width: 400px; height: 40px; text-align: left;">
                            ${requestScope.emailError}
                    </h2>
                </c:if>
            </div>
        </form>

        <form class="change-form" action="${pageContext.request.contextPath}/rental" method="post">
            <input type="hidden" name="command" value="CHANGING_PHONE">
            <input type="hidden" name="id" value="${sessionScope.user.userId}">
            <div class="change-label">
                <label style="color: white; font-size: 23px; font-family: calibri;">${changePhone}</label>
            </div>
            <div class="change-content">
                <label style="color: black; font-size: 23px; font-family: calibri;">${newPhone}:</label><br>
                <input type="text" name="newPhone" placeholder="${oldPhone}: ${sessionScope.user.phone}"
                       pattern="^(29|33|25)[\d]{7}$" required>
                <input type="submit" value="${change}">
                <c:if test="${not empty requestScope.phoneError}">
                    <br><h2 style="font-family: calibri; font-size: 17px; color:black; margin: 30px 0 10px 0; width: 400px; height: 40px; text-align: left;">
                            ${requestScope.phoneError}
                    </h2>
                </c:if>
            </div>
        </form>

        <form class="change-form" action="${pageContext.request.contextPath}/rental" method="post">
            <input type="hidden" name="command" value="CHANGING_PASSWORD">
            <input type="hidden" name="id" value="${sessionScope.user.userId}">
            <div class="change-label">
                <label style="color: white; font-size: 23px; font-family: calibri;">${changePass}</label>
            </div>
            <div class="change-content">
                <label style="color: black; font-size: 23px; font-family: calibri;">${oldPass}:</label><br><br>
                <input type="password" name="oldPass" pattern="^[\w\d._]{7,18}$" required><br><br>
                <label style="color: black; font-size: 23px; font-family: calibri;">${newPass}:</label><br>
                <input type="password" name="newPass" pattern="^[\w\d._]{7,18}$" required>
                <input type="submit" value="${change}">
                <c:if test="${not empty requestScope.passError}">
                   <br> <h2 style="font-family: calibri; font-size: 17px; color:black; margin: 30px 0 10px 0; width: 400px; height: 40px; text-align: left;">
                            ${requestScope.passError}
                    </h2>
                </c:if>
            </div>
        </form>

    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>