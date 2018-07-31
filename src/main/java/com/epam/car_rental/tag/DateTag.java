package com.epam.car_rental.tag;

import com.epam.car_rental.util.TagUtil;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Locale;

public class DateTag extends TagSupport {
    private final static Logger LOGGER = Logger.getLogger(AgeTag.class);

    private Date date;
    private String locale;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        Locale loc = new Locale(locale);
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, loc);
        String formatDate = format.format(date);
        JspWriter out = pageContext.getOut();
        pageContext.setAttribute("date",formatDate);
        return TagUtil.writeTag(out,formatDate,"Cannot write date", LOGGER);
    }
}
