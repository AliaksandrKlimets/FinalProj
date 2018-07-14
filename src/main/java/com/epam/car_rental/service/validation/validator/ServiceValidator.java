package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.service.validation.InputException;

public class ServiceValidator {

    private static final String SERVICE_REGEX = "^LEASE$|^LEASE_WITH_DRIVER$";

    public static void isServiceDataValid(String service, String hour, String oneToSeven, String eightToFifteen, String more) throws InputException {
        isService(service);
        Validator.isDouble(hour);
        Validator.isDouble(oneToSeven);
        Validator.isDouble(eightToFifteen);
        Validator.isDouble(more);
    }

    public static void isService(String service) throws InputException {
        boolean isValid = service == null || !service.matches(SERVICE_REGEX);
        if (isValid) {
            throw new InputException("Service is not valid");
        }
    }

}
