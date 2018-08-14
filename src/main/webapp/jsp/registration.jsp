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

    <fmt:message bundle="${loc}" key="locale.register.label.login" var="login"/>
    <fmt:message bundle="${loc}" key="locale.register.label.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.register.label.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.register.label.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.register.label.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="locale.register.label.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.register.label.birth" var="bitrh"/>
    <fmt:message bundle="${loc}" key="locale.register.label.register" var="registration"/>
    <fmt:message bundle="${loc}" key="locale.register.label.account" var="account"/>
    <fmt:message bundle="${loc}" key="locale.register.label.enter" var="enter"/>
    <fmt:message bundle="${loc}" key="locale.register.placeholder.login" var="holderlogin"/>
    <fmt:message bundle="${loc}" key="locale.register.placeholder.password" var="holderpass"/>
    <fmt:message bundle="${loc}" key="locale.register.placeholder.email" var="holdermail"/>
    <fmt:message bundle="${loc}" key="locale.register.placeholder.name" var="holdername"/>
    <fmt:message bundle="${loc}" key="locale.register.placeholder.surname" var="holdersurname"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.head.login" var="loginrules"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.head.password" var="passrules"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.head.names" var="namesrules"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.head.phone" var="phonerules"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.head.email" var="mailrules"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.login.first" var="loginrule1"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.login.second" var="loginrule2"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.login.third" var="loginrule3"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.password.first" var="passrule1"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.password.second" var="passrule2"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.password.third" var="passrules3"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.names.first" var="namesrule1"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.names.second" var="namesrule2"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.phone.first" var="phonerule1"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.email.first" var="mailrule1"/>
    <fmt:message bundle="${loc}" key="locale.register.rules.email.second" var="mailrule2"/>

    <fmt:message bundle="${loc}" key="locale.register.title" var="title"/>
    <fmt:message bundle="${loc}" key="locale.required.title" var="inputtitle"/>
    <script src="${pageContext.request.contextPath}/assets/js/date.js"></script>
    <title>${title}</title>

</head>
<body >
<c:if test="${not empty sessionScope.user}">
    <jsp:forward page="${pageContext.request.contextPath}/home"/>
</c:if>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<div class="content">
    <div class="input-data-form" style="height: 850px;">
        <form class="login" action="${pageContext.request.contextPath}/rental" method="post" style="height: 800px; ">
            <input type="hidden" name="command" value="REGISTRATION">

            <c:if test="${not empty requestScope.error}">
                <h2 style="font-family: calibri; font-size: 17px; color:white; margin: 20px 0 10px 0; width: 400px; height: 40px; text-align: center;">
                        ${requestScope.error}
                </h2>
            </c:if><br>

            <label class="input-label">${login}:</label><br>
            <input type="text" name="login" title="${inputtitle}" placeholder="${holderlogin}" minlength="4"
                   maxlength="20"
                   pattern="^[a-zA-Z\d\._]{4,20}$" required>
            <label class="input-label">${password}:</label><br>
            <input type="password" name="password" title="${inputtitle}" placeholder="${holderpass}" minlength="7"
                   maxlength="18"
                   pattern="^[a-zA-Z\._\d]{7,18}$" required>

            <label class="input-label">${name}:</label><br>
            <input type="text" name="name" placeholder="${holdername}" minlength="3" maxlength="20"
                   pattern="^[a-zA-Zа-яА-Я]{3,20}$" title="${inputtitle}" required>

            <label class="input-label">${surname}:</label><br>
            <input type="text" name="surname" placeholder="${holdersurname}" minlength="3" maxlength="20"
                   pattern="^[a-zA-Zа-яА-Я]{3,20}$" title="${inputtitle}" required>
            <br>
            <label class="input-label">${email}:</label><br>
            <input type="text" name="email" placeholder="${holdermail}" minlength="12" maxlength="27"
                   pattern="^[\w._\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$" title="${inputtitle}" required>

            <label class="input-label">${phone}:</label><br>
            <input type="text" name="phone" placeholder="XXXXXXXXX" minlength="9" maxlength="9"
                   pattern="^(29|33|25)[\d]{7}$" title="${inputtitle}" required>

            <label class="input-label">${bitrh}:</label><br>
            <input id="max" type="date" onclick="return getMax()" name="birth-date" max="2000-01-01" title="${inputtitle}" required>

            <input type="submit" name="enter" value="${registration}">

            <hr class="input-separator">
            <div class="login-link"><label class="input-label">${account}?</label>
                <a href="${pageContext.request.contextPath}/login">${enter}</a>
            </div>
        </form>


    </div>
    <div class="login-rules" style="height: 700px; margin-bottom: 200px; background-color: #ffffff">
        <h1 class="head-label" style="color: white; font-weight: normal;">${loginrules}:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">${loginrule1}</li>
            <li class="side-list">${loginrule2}</li>
            <li class="side-list">${loginrule2}</li>
        </ul>

        <h1 class="head-label" style=" color: white; font-weight: normal;">${passrules}:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">${passrule1}</li>
            <li class="side-list">${passrule2}</li>
            <li class="side-list">${passrules3}</li>
        </ul>

        <h1 class="head-label" style=" color: white; font-weight: normal;">${namesrules}:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">${namesrule1}</li>
            <li class="side-list">${namesrule2}</li>
        </ul>

        <h1 class="head-label" style=" color: white; font-weight: normal;">${phonerules}:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">${phonerule1}</li>
        </ul>
        <h1 class="head-label" style=" color: white; font-weight: normal;">${mailrules}:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">${mailrule1}</li>
            <li class="side-list">${mailrule2}</li>
        </ul>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>