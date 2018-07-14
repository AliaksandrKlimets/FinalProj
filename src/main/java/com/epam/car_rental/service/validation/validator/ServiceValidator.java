package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.service.validation.InputException;

public class ServiceValidator {
    public static void isServiceDataValid(String hour, String oneToSeven, String sevenToFifteen, String more)throws InputException{
        Validator.isDouble(hour);
        Validator.isDouble(oneToSeven);
        Validator.isDouble(sevenToFifteen);
        Validator.isDouble(more);
    }

}
