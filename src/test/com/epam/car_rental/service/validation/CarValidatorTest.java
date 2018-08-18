package com.epam.car_rental.service.validation;


import com.epam.car_rental.service.validation.validator.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarValidatorTest {

    @Test
    public void trueModel() {
        String model = "Opel astra";
        boolean expected = true;
        boolean actual = true;
        try {
            CarValidator.isModel(model);
        } catch (InputException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void wrongModel() {
        String model = "Model????????";
        boolean expected = false;
        boolean actual = true;
        try {
            CarValidator.isModel(model);
        } catch (InputException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void trueConsumption() {
        String consumption = "12-13";
        boolean expected = true;
        boolean actual = true;
        try {
            CarValidator.isConsumption(consumption);
        } catch (InputException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void wrongConsumption() {
        String consumption = "100=12";
        boolean expected = false;
        boolean actual = true;
        try {
            CarValidator.isConsumption(consumption);
        } catch (InputException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }
}
