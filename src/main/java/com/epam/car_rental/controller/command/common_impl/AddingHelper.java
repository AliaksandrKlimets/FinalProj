package com.epam.car_rental.controller.command.common_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.validator.UserValidator;
import com.epam.car_rental.service.validation.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.ControlConst.ADD_PARAM;
import static com.epam.car_rental.controller.constant.EntityAttributes.ID;
import static com.epam.car_rental.controller.constant.EntityAttributes.NAME;

public class AddingHelper implements Command {

    private static final Logger LOGGER = Logger.getLogger(Authorization.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addParam = request.getParameter(ADD_PARAM);
        String id = request.getParameter(ID);
        String name = request.getParameter(NAME);
        try {

            request.setAttribute(ADD_PARAM, addParam);
            if (id != null) {
                Validator.isNumber(id);
                request.setAttribute(ID, id);
            }
            if (name != null) {
                request.setAttribute(NAME, name);
            }
            request.getRequestDispatcher("/adding").forward(request, response);
        }catch (InputException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
