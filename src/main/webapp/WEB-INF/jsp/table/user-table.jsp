<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ahs" uri="rentalTag" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.personal.info" var="personal"/>
    <fmt:message bundle="${loc}" key="locale.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="locale.birth" var="birth"/>
    <fmt:message bundle="${loc}" key="locale.registration.date" var="regDate"/>
    <fmt:message bundle="${loc}" key="locale.user.ages" var="age"/>

</head>
<body>
<div class="content">
    <div style="min-height: 200px; width: 600px; background-color: #41484e; margin-left: 20px; padding: 15px; border-radius: 6px;">
        <table class="user-info-head">
            <tr style="color: white;">
                <th style="width: 400px; text-align: left;"><h3
                        style="font-family: calibri; font-size: 25px; font-weight: normal;">
                    <ahs:full-name name="${sessionScope.user.name}" surname="${sessionScope.user.surname}"/></h3>
                </th>
                <th style="width: 200px; text-align: right;"><h3
                        style="font-family: calibri; font-size: 25px; font-weight: normal;">${age}: <ahs:ages
                        date="${sessionScope.user.birthDate}"/></h3></th>
            </tr>
        </table>
        <h3 style="font-family: calibri; font-size: 19px; font-weight: normal; width: 250px; margin-top: 15px; color: white;">${personal}:</h3>
        <table style="width: 600px; margin-top: 20px; background-color: white;">
            <tr class="user-info-row">
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">E-mail:</h3>
                </th>
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">${sessionScope.user.email}</h3>
                </th>
            </tr>
            <tr class="user-info-row">
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">${phone}</h3>
                </th>
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">${sessionScope.user.phone}</h3>
                </th>
            </tr>
            <tr class="user-info-row">
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">${birth}</h3>
                </th>
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">
                    <ahs:date-locale date="${sessionScope.user.birthDate}" locale="${sessionScope.lang}"/></h3>
                </th>
            </tr>
            <tr>
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">${regDate}</h3>
                </th>
                <th class="table-user-cell"><h3
                        style="font-family: calibri; font-size:17px; font-weight: normal; height: 10px;">
                    <ahs:date-locale date="${sessionScope.user.registrationDate}" locale="${sessionScope.lang}"/></h3>
                </th>
            </tr>
        </table>
    </div>
</div>
</body>
</html>