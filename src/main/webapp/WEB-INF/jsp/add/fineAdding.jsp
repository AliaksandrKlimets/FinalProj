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

</head>
<body>
<h1>FINE ADDING PAGE</h1>
<c:out value="${requestScope.id}"/>
<c:out value="${requestScope.name}"/>

</body>
</html>
