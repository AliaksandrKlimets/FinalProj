package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.NotDateException;

public class FineValidator {

    private static final String PAYMENT_STATE_REGEX = "^PAID$|^UNPAID$";

    public static void isFineDataValid(String bill, String dueDate) throws InputException, NotDateException {
        Validator.isDouble(bill);
        Validator.isDate(dueDate);
    }


    public static void isPaymentState(String state) throws InputException {
        boolean isValid = state == null || !state.matches(PAYMENT_STATE_REGEX);
        if (isValid) {
            throw new InputException("Payment state is invalid");
        }
    }
}
