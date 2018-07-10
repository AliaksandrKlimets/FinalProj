package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.NotDateException;
import com.epam.car_rental.service.validation.NotNumberException;

import java.util.Objects;

public class Validator {
    private static final String NUMBER = "\\d+";
    private static final String DATE = "[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])";

    public static void isUser(Object user) throws InputException {
        if (user == null) {
            throw new InputException("User is null");
        }

        if (!(user instanceof User)) {
            throw new InputException("User is invalid");
        }
    }

    public static void isNumber(String number) throws NotNumberException {
        if (!number.matches(NUMBER)) {
            throw new NotNumberException(String.format("%s - is not a right number", number));
        }
    }

    public static void isDate(String date) throws NotDateException {
        if (!date.matches(DATE)) {
            throw new NotDateException(String.format("%s - is not a right date", date));
        }
    }
}