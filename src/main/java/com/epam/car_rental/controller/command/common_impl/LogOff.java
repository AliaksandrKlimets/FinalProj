package com.epam.car_rental.controller.command.common_impl;

import com.epam.car_rental.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.ControlConst.LANG;
import static com.epam.car_rental.controller.constant.PageUrl.MAIN_PAGE;

public class LogOff implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogOff.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG);
        session.invalidate();
        request.getSession().setAttribute(LANG, lang);
        response.sendRedirect(MAIN_PAGE);
    }
}
