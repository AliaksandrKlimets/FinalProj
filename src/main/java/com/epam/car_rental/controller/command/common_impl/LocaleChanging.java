package com.epam.car_rental.controller.command.common_impl;

import com.epam.car_rental.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.ControlConst.LANG;
import static com.epam.car_rental.controller.constant.ControlConst.PAGE;

public class LocaleChanging implements Command {
    private static final Logger LOGGER = Logger.getLogger(LocaleChanging.class);

    private static final String PARAM = "param";
    private static final String SERVLET = "/rental";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter(PAGE);
        String lang = request.getParameter(LANG);
        String parameters = request.getParameter(PARAM);
        session.setAttribute(LANG, lang);
        String address = parameters.isEmpty() ? page : SERVLET + "?" + parameters;
        response.sendRedirect(address);
    }
}
