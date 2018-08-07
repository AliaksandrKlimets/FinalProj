package com.epam.car_rental.controller.command.common_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.user.UserExistException;
import com.epam.car_rental.service.user.UserService;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.EntityAttributes.*;
import static com.epam.car_rental.controller.constant.PageUrl.HOME_PAGE;
import static com.epam.car_rental.controller.constant.PageUrl.REGISTRATION_PAGE;

public class Registration implements Command {
    private static final Logger LOGGER = Logger.getLogger(Registration.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String phone = request.getParameter(PHONE);
        String birthDate = request.getParameter(BIRTH_DATE);

        UserService userService = ServiceFactory.getInstance().getUserService();
        try{
            User tempUser = new User();
            tempUser.setLogin(login);
            tempUser.setPassword(password);
            tempUser.setName(name);
            tempUser.setSurname(surname);
            tempUser.setEmail(email);
            tempUser.setPhone(phone);
            tempUser.setRole("USER");
            userService.addUser(tempUser,birthDate);
            User user = userService.getUser(login,password);
            session.setAttribute("user", user);
            response.sendRedirect(HOME_PAGE);
        }catch (UserExistException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateWithMessage(request,response,"Пользователь с таким логином или почтой уже зарегистрирован",REGISTRATION_PAGE);
        }catch (ServiceException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateWithMessage(request,response,"Проверьте корректность введенных данных",REGISTRATION_PAGE);
        }
    }
}
