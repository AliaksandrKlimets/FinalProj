package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.NotDateException;

public class FineValidator {
    public static void isFineDataValid(String bill, String dueDate)throws InputException, NotDateException {
        Validator.isDouble(bill);
        Validator.isDate(dueDate);
    }
}
