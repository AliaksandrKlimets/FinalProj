package com.epam.car_rental.service.validation;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.epam.car_rental.service.validation.validator.*;

public class OrderValidatorTest {
    @Test
    public void truePassport(){
        String number = "MP3269909";
        boolean expected = true;
        boolean actual = true;
        try{
            OrderValidator.isPassport(number);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void wrongPassport(){
        String number = "MP?sd3269909";
        boolean expected = false;
        boolean actual = true;
        try{
            OrderValidator.isPassport(number);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void trueIdNumber(){
        String idNumber = "MP123MP1231234";
        boolean expected = true;
        boolean actual = true;
        try{
            OrderValidator.isIdNumber(idNumber);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void wrongIdNumber(){
        String idNumber = "MP?sd3269909";
        boolean expected = false;
        boolean actual = true;
        try{
            OrderValidator.isIdNumber(idNumber);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }
}
