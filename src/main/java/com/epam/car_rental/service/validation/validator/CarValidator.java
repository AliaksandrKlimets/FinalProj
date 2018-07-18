package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.entity.Car;
import com.epam.car_rental.service.validation.InputException;

public class CarValidator {
    private static final String MODEL_REGEX = "[\\d]+\\.{0,1}[\\d]{1}";
    private static final String YEAR_REGEX = "(19|20)[\\d]{2}";
    private static final String CAPACITY_REGEX = "[\\d]+\\.{0,1}[\\d]{0,1}";
    private static final String CONSUMPTION_REGEX = "[\\d]+\\.{0,1}[\\d]{0,1}-[\\d]+\\.{0,1}[\\d]{0,1}";
    private static final String INFO_REGEX = "[a-zA-Zа-я-Я\\d\\s,]*";
    private static final String TRANSMISSION_REGEX = "[a-zA-Zа-я-Я]+";
    private static final String TYPE_REGEX = "^COUPE$|^HATCHBACK$|^UNIVERSAL$|^PICKUP$|^CROSSOVER$";
    private static final String FUEL_REGEX = "^PETROL$|^DIESEL$|^GAS$";

    public static void isInputDataValid(Car car, String capacity, String type, String fuel, String hour, String oneToSeven, String eightToFifteen, String more) throws InputException {
        isModel(car.getModel());
        isYear(car.getYear());
        isConsumption(car.getConsumption());
        isCapacity(capacity);
        isTransmission(car.getTransmission());
        isAddInfo(car.getAddInfo());
        isCarType(type.toUpperCase());
        isFuelType(fuel.toUpperCase());
        Validator.isDouble(hour);
        Validator.isDouble(oneToSeven);
        Validator.isDouble(eightToFifteen);
        Validator.isDouble(more);
    }

    public static void isCarType(String type) throws InputException {
        boolean isValid = type == null || !type.matches(TYPE_REGEX);
        if (isValid) {
            throw new InputException("Car type is not valid");
        }
    }

    private static void isFuelType(String fuel) throws InputException {
        boolean isValid = fuel == null || !fuel.matches(FUEL_REGEX);
        if (isValid) {
            throw new InputException("Fuel type is not valid");
        }
    }


    public static void isModel(String model) throws InputException {
        boolean isValid = model == null || !model.matches(MODEL_REGEX);
        if (isValid) {
            throw new InputException("Invalid model");
        }
    }

    private static void isYear(String year) throws InputException {
        boolean isValid = year == null || !year.matches(YEAR_REGEX);
        if (isValid) {
            throw new InputException("Invalid year");
        }
    }

    private static void isCapacity(String capacity) throws InputException {
        boolean isValid = capacity == null || !capacity.matches(CAPACITY_REGEX);
        if (isValid) {
            throw new InputException("Invalid capacity");
        }
    }

    private static void isConsumption(String consumption) throws InputException {
        boolean isValid = consumption == null || !consumption.matches(CONSUMPTION_REGEX);
        if (isValid) {
            throw new InputException("Invalid consumption");
        }
    }

    private static void isAddInfo(String addInfo) throws InputException {
        boolean isValid = addInfo == null || !addInfo.matches(INFO_REGEX);
        if (isValid) {
            throw new InputException("Invalid add info");
        }
    }

    private static void isTransmission(String transmission) throws InputException {
        boolean isValid = transmission == null || !transmission.matches(TRANSMISSION_REGEX);
        if (isValid) {
            throw new InputException("Invalid transmission");
        }
    }

}
