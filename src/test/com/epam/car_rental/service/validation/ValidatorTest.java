package com.epam.car_rental.service.validation;

import com.epam.car_rental.service.validation.validator.Validator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorTest {

    @Test
    public void trueNumber() {
        String number = "123";
        boolean expected = true;
        boolean actual = true;
        try {
            Validator.isNumber(number);
        } catch (NotNumberException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void wrongNumber() {
        String number = "123pht";
        boolean expected = false;
        boolean actual = true;
        try {
            Validator.isNumber(number);
        } catch (NotNumberException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void trueDate() {
        String date = "2018-12-02";
        boolean expected = true;
        boolean actual = true;
        try {
            Validator.isDate(date);
        } catch (NotDateException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void wrongDate() {
        String date = "2018-234-021";
        boolean expected = false;
        boolean actual = true;
        try {
            Validator.isDate(date);
        } catch (NotDateException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void trueDouble(){
        String date = "10.2";
        boolean expected = true;
        boolean actual = true;
        try {
            Validator.isDouble(date);
        } catch (InputException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    public void wrongDouble(){
        String date = "10,2f";
        boolean expected = false;
        boolean actual = true;
        try {
            Validator.isDouble(date);
        } catch (InputException e) {
            actual = false;
        }
        assertEquals(expected, actual);
    }
}
