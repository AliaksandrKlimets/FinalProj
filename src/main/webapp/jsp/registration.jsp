<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="assets/css/style.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.register.title" var="title"/>
    <title>${title}</title>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<div style="width: 1400px; height: 900px; margin: 30px auto 20px auto; ">
    <div class="input-data-form">
        <form class="login" action="" method="post" style="height: 800px; ">
            <input type="hidden" name="command" value="">
            <label class="input-label">Логин:</label><br>
            <input type="text" name="login" placeholder="Логин" minlength="4" maxlength="20"
                   pattern="^[a-zA-Z\d\._]{4,20}$" required>
            <label class="input-label">Пароль:</label><br>
            <input type="password" name="password" placeholder="Пароль" minlength="7" maxlength="18"
                   pattern="^[a-zA-Z\._\d]{7,18}$" required>

            <label class="input-label">Имя:</label><br>
            <input type="text" name="name" placeholder="Имя" minlength="7" maxlength="20"
                   pattern="^[a-zA-Zа-яА-Я]{3,20}$" required>

            <label class="input-label">Фамилия:</label><br>
            <input type="text" name="surname" placeholder="Фамилия" minlength="3" maxlength="20"
                   pattern="^[a-zA-Zа-яА-Я]{3,20}$" required>
            <br>
            <label class="input-label">E-mail:</label><br>
            <input type="text" name="email" placeholder="E-mail" minlength="12" maxlength="27"
                   pattern="^[\w._\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$" required>

            <label class="input-label">Номер телефона:</label><br>
            <input type="text" name="phone" placeholder="XXXXXXXXX" minlength="9" maxlength="9"
                   pattern="^(29|33|25)[\d]{7}$" required>

            <label class="input-label">Дата рождения:</label><br>
            <input type="date" name="birth_date" max="2000-01-01" required>

            <input type="submit" name="enter" value="Зарегистрироваться">

            <hr class="input-separator">
            <div class="login-link"><label class="input-label">Есть аккаунт?</label>
                <a href="${pageContext.request.contextPath}/login">Войти</a>
            </div>
        </form>


    </div>
    <div class="login-rules" style="height: 900px;">
        <h1 class="head-label" style="color: white; font-weight: normal;">Правила ввода логина:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">Длина логина должна быть от 4 до 20 символов</li>
            <li class="side-list">Логин может содержать английские буквы любого регистра</li>
            <li class="side-list">Логин может содержать символы " . " и " _ "</li>
        </ul>

        <h1 class="head-label" style=" color: white; font-weight: normal;">Правила ввода пароля:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">Длина пароля должна быть от 7 до 18 символов</li>
            <li class="side-list">Пароль может содержать английские буквы любого регистра</li>
            <li class="side-list">Логин может содержать цифры, а так же символы " . " и " _ "</li>
        </ul>

        <h1 class="head-label" style=" color: white; font-weight: normal;">Правила ввода имени и фамилии:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">Длина имени должна быть от 3 до 20 символов</li>
            <li class="side-list">Длина фамилии должна быть от 3 до 20 символов</li>
            <li class="side-list">Имя и фамилия должны состоять из русских или английских букв</li>
        </ul>

        <h1 class="head-label" style=" color: white; font-weight: normal;">Правила ввода телефона:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">Телефон должен состоять из 9 цифр без пробелов и начинаться с кодов 25, 29 или 33</li>
        </ul>
        <h1 class="head-label" style=" color: white; font-weight: normal;">Правила ввода e-mail:</h1>
        <ul style="list-style-type: disc; margin: 5px 0 5px 60px;">
            <li class="side-list">E-mail должен состоять из английских символов, а так же символов " @ " и " . "</li>
            <li class="side-list">Длина e-mail должна быть от 12 до 27 символов</li>
        </ul>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>