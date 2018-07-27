package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.validation.InputException;

public class UserValidator {
    private static final String LOGIN_REGEX = "^[\\w][\\w._\\d]{4,20}$";
    private static final String PASSWORD_REGEX = "^[\\w\\d._]{7,18}$";
    private static final String NAME_REGEX = "^[А-Я][а-я]{3,20}|^[A-Z][a-z]{3,20}";
    private static final String EMAIL_REGEX = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";
    private static final String PHONE_REGEX = "^(29|33|25)[\\d]{7}$";
    private static final String INCORRECT_USERNAME = "Incorrect username";
    private static final String INCORRECT_PASSWORD = "Incorrect password";
    private static final String INCORRECT_NAME = "Incorrect name";
    private static final String INCORRECT_EMAIL = "Incorrect email";
    private static final String INCORRECT_PHONE = "Incorrect phone";


    public static void isLogin(String login) throws InputException {
        boolean isValid = login == null || !login.matches(LOGIN_REGEX);
        if (isValid) {
            throw new InputException(INCORRECT_USERNAME);
        }
    }

    public static void isPassword(String password) throws InputException {
        boolean isValid = password == null || !password.matches(PASSWORD_REGEX);
        if (isValid) {
            throw new InputException(INCORRECT_PASSWORD);
        }
    }

    public static void isInputDataValid(Object user, String date) throws InputException {
        Validator.isUser(user);
        User currentUser = (User) user;
        isLogin(currentUser.getLogin());
        isPassword(currentUser.getPassword());
        isName(currentUser.getName());
        isName(currentUser.getSurname());
        isEmail(currentUser.getEmail());
        isPhone(currentUser.getPhone());
        Validator.isDate(date);
    }

    public static void isName(String name) throws InputException {
        boolean isValid = name == null || !name.matches(NAME_REGEX);
        if (isValid) {
            throw new InputException(INCORRECT_NAME);
        }
    }

    public static void isEmail(String email) throws InputException {
        boolean isValid = email == null || !email.matches(EMAIL_REGEX);
        if (isValid) {
            throw new InputException(INCORRECT_EMAIL);
        }
    }

    public static void isPhone(String phone) throws InputException {
        boolean isValid = phone == null || !phone.matches(PHONE_REGEX);
        if (isValid) {
            throw new InputException(INCORRECT_PHONE);
        }
    }

}
