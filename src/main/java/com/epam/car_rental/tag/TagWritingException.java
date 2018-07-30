package com.epam.car_rental.tag;

import javax.servlet.jsp.JspException;

public class TagWritingException extends JspException {
    public TagWritingException(String message){
        super(message);
    }
}
