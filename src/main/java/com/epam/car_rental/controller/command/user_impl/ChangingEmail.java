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
import static com.epam.car_rental.controller.constant.PageUrl.LOGIN_PAGE;

public class ChangingEmail implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangingEmail.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter(ID);
        String newEmail = request.getParameter("newEmail");

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            userService.changeEmail(userId, newEmail);
            User user = (User)request.getSession().getAttribute(USER);
            user.setEmail(newEmail);
            request.getSession().setAttribute(USER,user);
            response.sendRedirect(HOME_PAGE);
        }catch (InvalidParametersException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateParamWithMessage(request,response,"Данные введены неверно","/settings","emailError");
        }catch (UserExistException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateParamWithMessage(request,response,"Почта уже занята","/settings","emailError");
        }catch (ServiceException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateParamWithMessage(request,response,"Данные введены неверно","/settings","emailError");
        }
    }
}
