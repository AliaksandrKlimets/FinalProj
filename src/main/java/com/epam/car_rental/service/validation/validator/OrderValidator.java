package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.NotDateException;


public class OrderValidator {

    private static final String PASSPORT_REGEX = "[A-Z]{2}[\\d]{7}";
    private static final String ID_NUMBER_REGEX = "[A-Z\\d]{14}";
    private static final String ORDER_STATE_REGEX = "^WAITING$|^ACCEPT$|^DECLINE$";
    private static final String PAYMENT_STATE_REGEX = "^PAID$|^UNPAID$";


    public static void isInputDataValid(Order order,String userId, String dateOfExpiry, String start, String end) throws InputException, NotDateException {
        Validator.isNumber(userId);
        isPassport(order.getPassportNumber());
        isIdNumber(order.getIdentificationNumber());
        Validator.isDate(dateOfExpiry);
        Validator.isDate(start);
        Validator.isDate(end);
    }

    private static void isPassport(String passport) throws InputException {
        boolean isValid = passport == null || !passport.matches(PASSPORT_REGEX);
        if (isValid) {
            throw new InputException("Passport number is invalid");
        }
    }

    private static void isIdNumber(String idNumber) throws InputException {
        boolean isValid = idNumber == null || !idNumber.matches(ID_NUMBER_REGEX);
        if (isValid) {
            throw new InputException("Id number is invalid");
        }
    }

    public static void isOrderState(String state) throws InputException {
        boolean isValid = state == null || !state.matches(ORDER_STATE_REGEX);
        if (isValid) {
            throw new InputException("Order state is invalid");
        }
    }

    public static void isPaymentState(String state) throws InputException {
        boolean isValid = state == null || !state.matches(PAYMENT_STATE_REGEX);
        if (isValid) {
            throw new InputException("Payment state is invalid");
        }
    }
}
