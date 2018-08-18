package com.epam.car_rental.service.validation;

import com.epam.car_rental.service.validation.validator.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserValidatorTest {
    @Test
    public void trueLogin(){
        String login = "alex_klimets";
        boolean expected = true;
        boolean actual = true;
        try{
            UserValidator.isLogin(login);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void wrongLogin(){
        String login = "alex_kli??mets";
        boolean expected = false;
        boolean actual = true;
        try{
            UserValidator.isLogin(login);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void truePassword(){
        String password = "1751907sasha";
        boolean expected = true;
        boolean actual = true;
        try{
            UserValidator.isPassword(password);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void wrongPassword(){
        String password = "1751907sash!!!!a";
        boolean expected = false;
        boolean actual = true;
        try{
            UserValidator.isPassword(password);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void trueName(){
        String name = "Alex";
        boolean expected = true;
        boolean actual = true;
        try{
            UserValidator.isName(name);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void wrongName(){
        String name = "Alex123";
        boolean expected = false;
        boolean actual = true;
        try{
            UserValidator.isName(name);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void trueEmail(){
        String email = "sashaklimets@mail.ru";
        boolean expected = true;
        boolean actual = true;
        try{
            UserValidator.isEmail(email);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void wrongEmail(){
        String email = "sasha@mail@mail.ru";
        boolean expected = false;
        boolean actual = true;
        try{
            UserValidator.isEmail(email);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void truePhone(){
        String phone = "291751907";
        boolean expected = true;
        boolean actual = true;
        try{
            UserValidator.isPhone(phone);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }

    @Test
    public void wrongPhone(){
        String phone = "121751907";
        boolean expected = false;
        boolean actual = true;
        try{
            UserValidator.isPhone(phone);
        }catch (InputException e){
            actual = false;
        }
        assertEquals(expected,actual);
    }
}
