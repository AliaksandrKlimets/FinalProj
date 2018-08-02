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

</head>
<body>
<div style="min-height: 500px; width: 1000px; padding: 0; margin: 0;">
    <table>
        <tr>
            <th>id</th>
            <th>Логин</th>
            <th style="width: 150px;">Имя</th>
            <th>Фамилия</th>
            <th style="width: 200px;">E-mail</th>
            <th>Телефон</th>
            <th style="width: 100px;">Дата рождения</th>
            <th style="width: 100px;">Действие</th>
        </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <th>${user.userId}</th>
                <th>${user.login}</th>
                <th style="width: 120px;">${user.name}</th>
                <th>${user.surname}</th>
                <th style="width: 200px;">${user.email}</th>
                <th style="width: 100px;">${user.phone}</th>
                <th style="width: 100px;"><ahs:date-locale locale="ru" date="${user.birthDate}"/></th>
                <th style="width: 100px;">
                    <form class="button" action="/rental" method="get">
                        <input type="hidden" name="command" value="ADDING_HELP">
                        <input type="hidden" name="add_param" value="fine">
                        <input type="hidden" name="id" value="${user.userId}">
                        <input type="hidden" name="name" value="${user.login}">
                        <input type="submit" value="Добавить штраф">
                    </form>
                </th>

            </tr>
        </c:forEach>

    </table>
    <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
</div>
</body>
</html>
