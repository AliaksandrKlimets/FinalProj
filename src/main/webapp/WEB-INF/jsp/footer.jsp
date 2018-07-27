<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.footer.contacts" var="contact"/>
    <fmt:message bundle="${loc}" key="locale.footer.lang" var="lang"/>

</head>
<body style="margin: 0;">
<div class="footer">
    <div class="wrapper">
        <div class="footer-content">
            <div class="footer-right">

            </div>
            <div class="footer-left">
                <ul>
                    <li class="info">
                        <ul style="padding-top: 1px;">
                            <li class="footer-list"><h3>${contact}:</h3></li>
                            <li class="footer-list"><h3>+ 375 29 850-79-63</h3></li>
                            <li class="footer-list"><h3>+ 375 29 563-04-74</h3></li>
                        </ul>

                    </li>
                    <li class="info">
                        <ul>
                            <li class="lang"><h3>${lang}:</h3></li>
                            <li class="lang">
                                <form class="frm" action="${pageContext.request.contextPath}/rental" method="post">
                                    <input type="hidden" name="command" value="LOCALE_CHANGING"/>
                                    <input type="hidden" name="page" value="${pageContext.request.requestURL}"/>
                                    <input type="hidden" name="param" value="${pageContext.request.queryString}"/>
                                    <input type="hidden" name="lang" value="ru"/>
                                    <input type="submit" value="Русский"/>
                                </form>
                            </li>
                            <li class="lang">
                                <form action="${pageContext.request.contextPath}/rental" method="post">
                                    <input type="hidden" name="command" value="LOCALE_CHANGING"/>
                                    <input type="hidden" name="page" value="${pageContext.request.requestURL}"/>
                                    <input type="hidden" name="param" value="${pageContext.request.queryString}"/>
                                    <input type="hidden" name="lang" value="en"/>
                                    <input type="submit" value="English"/>
                                </form>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>