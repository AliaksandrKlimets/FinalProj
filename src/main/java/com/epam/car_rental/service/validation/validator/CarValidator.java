package com.epam.car_rental.service.validation.validator;

import com.epam.car_rental.entity.Car;
import com.epam.car_rental.service.validation.InputException;

public class CarValidator {
    private static final String MODEL_REGEX = "[a-zA-Z\\d\\.\\s]{3,30}";
    private static final String YEAR_REGEX = "(19|20)[\\d]{2}";
    private static final String CAPACITY_REGEX = "[\\d]+\\.?[\\d]{0,3}";
    private static final String CONSUMPTION_REGEX = "[\\d]+\\.{0,1}[\\d]{0,1}-[\\d]+\\.{0,1}[\\d]{0,1}";
    private static final String INFO_REGEX = "[a-zA-Zа-яА-Я\\d\\s,]*";
    private static final String TRANSMISSION_REGEX = "[a-zA-Zа-яA-Я\\s]+";
    private static final String TYPE_REGEX = "^COUPE$|^HATCHBACK$|^UNIVERSAL$|^PICKUP$|^CROSSOVER$";
    private static final String FUEL_REGEX = "^PETROL$|^DIESEL$|^GAS$";

    /**
     * Validate input date for validity
     * @param car Entity of Car with info
     * @param capacity Capacity
     * @param type Car type
     * @param fuel Fuel type
     * @param day Cost per day
     * @param twoToSeven Cost from two to seven days
     * @param eightToFifteen Cost from eight to fifteen daye
     * @param more Cost from 16+ days
     * @throws InputException if input data is incorrect
     */

    public static void isInputDataValid(Car car, String capacity, String type, String fuel, String day, String twoToSeven, String eightToFifteen, String more) throws InputException {
        isModel(car.getModel());
        isYear(car.getYear());
        isConsumption(car.getConsumption());
        isCapacity(capacity);
        isTransmission(car.getTransmission());
        isAddInfo(car.getAddInfo());
        isCarType(type.toUpperCase());
        isFuelType(fuel.toUpperCase());
        Validator.isDouble(day);
        Validator.isDouble(twoToSeven);
        Validator.isDouble(eightToFifteen);
        Validator.isDouble(more);
    }

    /**
     * @param type Car type
     * @throws InputException if input data is incorrect
     */

    public static void isCarType(String type) throws InputException {
        boolean isValid = type == null || !type.matches(TYPE_REGEX);
        if (isValid) {
            throw new InputException("Car type is not valid");
        }
    }

    /**
     * @param fuel Fuel type
     * @throws InputException if input data is incorrect
     */

    private static void isFuelType(String fuel) throws InputException {
        boolean isValid = fuel == null || !fuel.matches(FUEL_REGEX);
        if (isValid) {
            throw new InputException("Fuel type is not valid");
        }
    }

    /**
     * @param model model
     * @throws InputException if input data is incorrect
     */

    public static void isModel(String model) throws InputException {
        boolean isValid = model == null || !model.matches(MODEL_REGEX);
        if (isValid) {
            throw new InputException("Invalid model");
        }
    }

    /**
     * @param year year
     * @throws InputException if input data is incorrect
     */

    private static void isYear(String year) throws InputException {
        boolean isValid = year == null || !year.matches(YEAR_REGEX);
        if (isValid) {
            throw new InputException("Invalid year");
        }
    }

    /**
     * @param capacity capacity
     * @throws InputException if input data is incorrect
     */

    private static void isCapacity(String capacity) throws InputException {
        boolean isValid = capacity == null || !capacity.matches(CAPACITY_REGEX);
        if (isValid) {
            throw new InputException("Invalid capacity");
        }
    }

    /**
     * @param consumption consumption
     * @throws InputException if input data is incorrect
     */

    public static void isConsumption(String consumption) throws InputException {
        boolean isValid = consumption == null || !consumption.matches(CONSUMPTION_REGEX);
        if (isValid) {
            throw new InputException("Invalid consumption");
        }
    }

    /**
     * @param addInfo additional info
     * @throws InputException if input data is incorrect
     */

    private static void isAddInfo(String addInfo) throws InputException {
        boolean isValid = addInfo == null || !addInfo.matches(INFO_REGEX);
        if (isValid) {
            throw new InputException("Invalid add info");
        }
    }

    /**
     * @param transmission transmission
     * @throws InputException if input data is incorrect
     */

    private static void isTransmission(String transmission) throws InputException {
        boolean isValid = transmission == null || !transmission.matches(TRANSMISSION_REGEX);
        if (isValid) {
            throw new InputException("Invalid transmission");
        }
    }

}
