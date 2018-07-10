package com.epam.car_rental.service.validation.validator;

public class UserValidator {
    private static final String USERNAME_REGEX = "^[\\w][\\w._\\d]{4,20}$";
    private static final String PASSWORD_REGEX = "^[\\w\\d._]{5,17}$";
    private static final String NAME_REGEX = "^[A-ZА-Я][a-zа-я]{1,20}$";
    private static final String EMAIL_REGEX = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";
    private static final String PHONE_REGEX = "^(29|33|25)[\\d]{7}$";
    private static final String INCORRECT_USERNAME = "Incorrect username";
    private static final String INCORRECT_PASSWORD = "Incorrect password";
    private static final String INCORRECT_EMAIL = "Incorrect email";
    private static final String INCORRECT_PHONE = "Incorrect phone";


}
