<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon/favicon-16x16.png" sizes="16x16" />
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.login.label.login" var="login"/>
    <fmt:message bundle="${loc}" key="locale.login.label.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.login.label.register" var="registration"/>
    <fmt:message bundle="${loc}" key="locale.login.label.sign.in" var="signin"/>
    <fmt:message bundle="${loc}" key="locale.login.placeholder.login" var="holderlogin"/>
    <fmt:message bundle="${loc}" key="locale.login.placeholder.password" var="holderpassword"/>
    <fmt:message bundle="${loc}" key="locale.login.message.head.rules.login" var="loginrules"/>
    <fmt:message bundle="${loc}" key="locale.login.message.head.rules.password" var="passwordrules"/>
    <fmt:message bundle="${loc}" key="locale.login.message.login.rule.first" var="loginrule1"/>
    <fmt:message bundle="${loc}" key="locale.login.message.login.rule.second" var="loginrule2"/>
    <fmt:message bundle="${loc}" key="locale.login.message.login.rule.third" var="loginrule3"/>
    <fmt:message bundle="${loc}" key="locale.login.message.password.rule.first" var="passrule1"/>
    <fmt:message bundle="${loc}" key="locale.login.message.password.rule.second" var="passrule2"/>
    <fmt:message bundle="${loc}" key="locale.login.message.password.rule.third" var="passrule3"/>
    <fmt:message bundle="${loc}" key="locale.required.title" var="inputtitle"/>

    <fmt:message bundle="${loc}" key="locale.login.title" var="title"/>
    <title>${title}</title>

</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <jsp:forward page="${pageContext.request.contextPath}/home"/>
</c:if>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<div class="content">
    <div class="input-data-form">
        <form class="login" action="${pageContext.request.contextPath}/rental" method="post" style=" padding-top: 49px;">
            <input type="hidden" name="command" value="AUTHORIZATION">
            <label class="input-label">${login}:</label><br>
            <input type="text" name="login" placeholder="${holderlogin}" title="${inputtitle}" minlength="4"
                   maxlength="20"
                   pattern="^[a-zA-Z\d\._]{4,20}$" required>
            <label class="input-label">${password}:</label><br>
            <input type="password" name="password" placeholder="${holderpassword}" title="${inputtitle}" minlength="7"
                   maxlength="18"
                   pattern="^[a-zA-Z\._\d]{7,18}$" required>
            <input type="submit" name="enter" value="${signin}">
            <c:if test="${not empty requestScope.error}">
            <h2 style="font-family: calibri; font-size: 17px; color:white; margin: 20px 0 10px 0; width: 400px; height: 40px; text-align: center;">
                ${requestScope.error}
            </h2>
            </c:if>
            <hr class="input-separator">
            <div class="reg-link">
                <a href="${pageContext.request.contextPath}/registration">${registration}</a>
            </div>
        </form>


    </div>
    <div class="login-rules" style="background-color: #ffffff;">
        <h1 class="head-label" style="color: white; font-weight: normal; ">${loginrules}:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">${loginrule1}</li>
            <li class="side-list">${loginrule2}</li>
            <li class="side-list">${loginrule3}</li>
        </ul>

        <h1 class="head-label" style=" color: white; font-weight: normal;">${passwordrules}:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">${passrule1}</li>
            <li class="side-list">${passrule2}</li>
            <li class="side-list">${passrule3}</li>
        </ul>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>