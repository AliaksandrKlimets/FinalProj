package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.NotDateException;
import com.epam.car_rental.service.validation.NotNumberException;


public class Validator {
    private static final String NUMBER = "\\d+";
    private static final String DATE = "[1-9][\\d]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])";
    private static final String DOUBLE_REGEX = "[\\d]+\\.{0,1}[\\d]{0,1}";

    /**
     *
     * @param user User entity
     * @throws InputException if input data is incorrect
     */

    public static void isUser(Object user) throws InputException {
        if (user == null) {
            throw new InputException("User is null");
        }

        if (!(user instanceof User)) {
            throw new InputException("User is invalid");
        }

    }

    /**
     *
     * @param number Number
     * @throws NotNumberException if input data is incorrect
     */

    public static void isNumber(String number) throws NotNumberException {
        if (!number.matches(NUMBER)) {
            throw new NotNumberException(String.format("%s - is not a right number", number));
        }
    }

    /**
     *
     * @param date Date
     * @throws NotDateException if input data is incorrect
     */

    public static void isDate(String date) throws NotDateException {
        if (!date.matches(DATE)) {
            throw new NotDateException(String.format("%s - is not a right date", date));
        }
    }

    /**
     *
     * @param number Double number
     * @throws NotNumberException if input data is incorrect
     */

    public static  void isDouble(String number) throws InputException{
        if(!number.matches(DOUBLE_REGEX)){
            throw new InputException("Not double");
        }
    }
}