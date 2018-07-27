<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.cars.title" var="title"/>
    <title>${title}</title>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<div style="height: 900px;">
    <p>Car page</p>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>