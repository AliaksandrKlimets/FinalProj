<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ahs" uri="rentalTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adding.css">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.car.model" var="model"/>
    <fmt:message bundle="${loc}" key="locale.car.year" var="year"/>
    <fmt:message bundle="${loc}" key="locale.car.consumption" var="consumption"/>
    <fmt:message bundle="${loc}" key="locale.car.capacity" var="capacity"/>
    <fmt:message bundle="${loc}" key="locale.car.car.type" var="catType"/>
    <fmt:message bundle="${loc}" key="locale.car.fuel.type" var="fuelType"/>
    <fmt:message bundle="${loc}" key="locale.car.transmission" var="transmission"/>
    <fmt:message bundle="${loc}" key="locale.car.image" var="image"/>
    <fmt:message bundle="${loc}" key="locale.car.add.info" var="addInfo"/>
    <fmt:message bundle="${loc}" key="locale.car.cost.info" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.car.type.coupe" var="coupe"/>
    <fmt:message bundle="${loc}" key="locale.car.type.crossover" var="crossover"/>
    <fmt:message bundle="${loc}" key="locale.car.type.pickup" var="pickup"/>
    <fmt:message bundle="${loc}" key="locale.car.type.universal" var="universal"/>
    <fmt:message bundle="${loc}" key="locale.car.type.hatchback" var="hatchback"/>
    <fmt:message bundle="${loc}" key="locale.car.fuel.type.petrol" var="petrol"/>
    <fmt:message bundle="${loc}" key="locale.car.fuel.type.diesel" var="diedel"/>
    <fmt:message bundle="${loc}" key="locale.car.fuel.type.gas" var="gas"/>
    <fmt:message bundle="${loc}" key="locale.car.add.car" var="addCar"/>

</head>
<body>
<div class="content">
    <div style="width: 800px; min-height: 650px; margin: 0 0 0 19px; padding: 0">
        <form class="car-adding" action="${pageContext.request.contextPath}/rental" method="post">
            <input type="hidden" name="command" value="ADD_NEW_CAR">
            <label class="input-label">${model}:</label><br>
            <input type="text" name="model" placeholder="${model}" pattern="[a-zA-Z\d\.\s]{3,30}" minlength="3" maxlength="30" required><br>

            <label class="input-label">${year}:</label><br>
            <input type="text" name="year" placeholder="${year}" pattern="(19|20)[\d]{2}" minlength="4" maxlength="4" required><br>

            <label class="input-label">${consumption}:</label><br>
            <input type="text" name="consumption" placeholder="min - max" pattern="[\d]+\.{0,1}[\d]{0,1}-[\d]+\.{0,1}[\d]{0,1}" required><br>

            <label class="input-label">${capacity}:</label><br>
            <input type="text" name="capacity" placeholder="${capacity}" pattern="[\d]+\.{0,1}[\d]{0,1}" required><br>

            <label class="input-label">${catType}:</label><br>
            <input type="radio" value="PICKUP" name="carType" checked><label class="radio-label">${pickup}</label><br>
            <input type="radio" value="HATCHBACK" name="carType"><label class="radio-label">${hatchback}</label><br>
            <input type="radio" value="COUPE" name="carType"><label class="radio-label">${coupe}</label><br>
            <input type="radio" value="CROSSOVER" name="carType"><label class="radio-label">${universal}</label><br>
            <input type="radio" value="UNIVERSAL" name="carType"><label class="radio-label">${crossover}</label><br>

            <label class="input-label">${transmission}:</label><br>
            <input type="text" name="transmission" placeholder="${transmission}" pattern="[a-zA-Zа-яА-Я]+" required ><br>

            <label class="input-label">${fuelType}:</label><br>
            <input type="radio" value="DIESEL" name="fuelType" checked><label class="radio-label">${diedel}</label><br>
            <input type="radio" value="PETROL" name="fuelType"><label class="radio-label">${petrol}</label><br>
            <input type="radio" value="GAS" name="fuelType"><label class="radio-label">${gas}</label><br>

            <label class="input-label">${image}:</label><br>
            <input type="text" name="image" placeholder="${image}" required><br>

            <label class="input-label">${addInfo}:</label><br>
            <input type="text" name="addInfo" placeholder="${addInfo}" required><br>

            <label class="input-label">${cost}:</label><br>

            <input style="width: 65px;" type="text" name="day" placeholder="1" pattern="[\d]+\.{0,1}[\d]{0,1}" required>
            <input style="width: 65px;" type="text" name="twoToSeven" placeholder="2-7" pattern="[\d]+\.{0,1}[\d]{0,1}" required>
            <input style="width: 65px;" type="text" name="eightToFifteen" placeholder="8-15" pattern="[\d]+\.{0,1}[\d]{0,1}" required>
            <input style="width: 67px;" type="text" name="sixteenAndMore" placeholder="16+" pattern="[\d]+\.{0,1}[\d]{0,1}" required>

            <input type="submit" value="${addCar}">
            <h1 style="font-size: 15px;color: white; width: 200px; margin-left: 200px;">${requestScope.error}</h1>
        </form>
    </div>
</div>
</body>
</html>
