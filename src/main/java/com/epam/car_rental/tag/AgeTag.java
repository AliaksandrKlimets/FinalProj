package com.epam.car_rental.tag;

import com.epam.car_rental.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.Date;

public class AgeTag extends TagSupport {
    private final static Logger LOGGER = Logger.getLogger(AgeTag.class);

    private  Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        java.util.Date now = new java.util.Date();
        long mills = now.getTime() - date.getTime();
        long age = mills/(31536000000L);
        JspWriter out = pageContext.getOut();
        pageContext.setAttribute("age",age);
        return TagUtil.writeTag(out,""+age,"Cannot write age tag", LOGGER);
    }
}
