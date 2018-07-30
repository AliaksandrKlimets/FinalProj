package com.epam.car_rental.tag;

import com.epam.car_rental.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FullNameTag extends TagSupport {

    private final static Logger LOGGER = Logger.getLogger(FullNameTag.class);

    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int doStartTag() throws JspException {
        String fullName = name + " " +surname;
        JspWriter out = pageContext.getOut();
        pageContext.setAttribute("fullName",fullName);
        return TagUtil.writeTag(out,fullName,"Cannot write user status tag to page", LOGGER);
    }
}
