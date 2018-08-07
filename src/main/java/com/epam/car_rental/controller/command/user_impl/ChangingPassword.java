package com.epam.car_rental.controller.command.user_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.user.UserExistException;
import com.epam.car_rental.service.user.UserNotFoundException;
import com.epam.car_rental.service.user.UserService;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.EntityAttributes.ID;
import static com.epam.car_rental.controller.constant.EntityAttributes.USER;
import static com.epam.car_rental.controller.constant.PageUrl.HOME_PAGE;

public class ChangingPassword implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangingPassword.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter(ID);
        String newPass = request.getParameter("newPass");
        String oldPass = request.getParameter("oldPass");

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            userService.changePassword(userId, oldPass, newPass);
            response.sendRedirect(HOME_PAGE);
        } catch (InvalidParametersException e) {
            LOGGER.error(e.getMessage());
            ControllerUtil.updateParamWithMessage(request, response, "Данные введены неверно", "/settings", "passError");
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage());
            ControllerUtil.updateParamWithMessage(request, response, "Неверный пароль", "/settings", "passError");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            ControllerUtil.updateParamWithMessage(request, response, "Данные введены неверно", "/settings", "passError");
        }
    }
}
