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
    <fmt:message bundle="${loc}" key="locale.rent.rules.head" var="head"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.head.condition" var="conditions"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.info.first" var="info1"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.first" var="condition1"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.second" var="condition2"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.third" var="condition3"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.fourth" var="condition4"/>
    <fmt:message bundle="${loc}" key="locale.index.side.list.fifth" var="condition5"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.info.second" var="info2"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.info.third" var="info3"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.info.fourth" var="info4"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.info.fifth" var="info5"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.first" var="table1"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.second" var="table2"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.third" var="table3"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.fourth" var="table4"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.fifth" var="table5"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.sisth" var="table6"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.seventh" var="table7"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.table.condition.eighth" var="table8"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.notes.first" var="note1"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.notes.second" var="note2"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.notes.third" var="note3"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.list.add" var="adds"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.add.first" var="add1"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.add.second" var="add2"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.add.third" var="add3"/>
    <fmt:message bundle="${loc}" key="locale.rent.rules.add.fourth" var="add4"/>

    <fmt:message bundle="${loc}" key="locale.cost.byn" var="byn"/>
    <fmt:message bundle="${loc}" key="locale.cost.free" var="free"/>
    <fmt:message bundle="${loc}" key="locale.day" var="day"/>
    <fmt:message bundle="${loc}" key="locale.km" var="km"/>

    <fmt:message bundle="${loc}" key="locale.rules.title" var="title"/>
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
<div class="content">

    <div class="rule-content">
        <h1 class="head-label">${head}</h1>
        <h2 class="rule-h2">${conditions}:</h2>
        <ul style="list-style-type: disc; margin: 5px 0 5px 57px;">
            <li class="side-list">${condition1}</li>
            <li class="side-list">${condition2}</li>
            <li class="side-list">${condition3}</li>
            <li class="side-list">${condition4}</li>
            <li class="side-list">${condition5}</li>
        </ul>
        <p class="rule-p">${info1}</p>
        <p class="rule-p">${info2}</p>
        <p class="rule-p">${info3}</p>
        <h2 style="font-family: calibri; margin-left: 20px; font-weight: normal;">${info4}*</h2>
        <p class="rule-p">${info5}</p>
        <table style="border-collapse: collapse; margin-left: 20px; width: 850px;">
            <tr>
                <th style="width: 400px;" class="table-rule">${table1}</th>
                <th class="table-rule" style="width: 150px;">15 ${byn}</th>
            </tr>
            <tr>
                <th style="width: 400px;" class="table-rule">${table2}</th>
                <th class="table-rule" style="width: 150px;">45 ${byn}</th>
            </tr>
            <tr>
                <th style="width: 400px;" class="table-rule">${table3}</th>
                <th class="table-rule" style="width: 150px;">${free}</th>
            </tr>
            <tr>
                <th style="width: 400px;" class="table-rule">${table4}</th>
                <th class="table-rule" style="width: 150px;">1 ${day} **</th>
            </tr>
            <tr>
                <th style="width: 400px;" class="table-rule">${table5}</th>
                <th class="table-rule" style="width: 150px;">350 ${km}</th>
            </tr>
            <tr>
                <th style="width: 400px;" class="table-rule">${table6}{</th>
                <th class="table-rule" style="width: 150px;">15 ${byn}</th>
            </tr>
            <tr>
                <th style="width: 400px;" class="table-rule">${table7}</th>
                <th class="table-rule" style="width: 150px;">30-35 ${byn}***</th>
            </tr>
            <tr>
                <th style="width: 400px;" class="table-rule">${table8}</th>
                <th class="table-rule" style="width: 150px;">20 ${byn}</th>
            </tr>
        </table>
        <p class="rule-p">
            *${note1}
            <br>**${note2}
            <br>***${note3}
        </p>
        <h2 class="rule-h2">${adds}:</h2>
        <ul style="list-style-type: disc; margin: 5px 0 5px 57px;">
            <li class="side-list">${add1}</li>
            <li class="side-list">${add2}</li>
            <li class="side-list">${add3}</li>
            <li class="side-list">${add4}</li>
        </ul>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>