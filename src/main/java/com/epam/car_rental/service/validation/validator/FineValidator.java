package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.NotDateException;

public class FineValidator {

    private static final String PAYMENT_STATE_REGEX = "^PAID$|^UNPAID$";

    /**
     *
     * @param state payment state
     * @throws InputException if input data is incorrect
     */

    public static void isPaymentState(String state) throws InputException {
        boolean isValid = state == null || !state.matches(PAYMENT_STATE_REGEX);
        if (isValid) {
            throw new InputException("Payment state is invalid");
        }
    }
}
