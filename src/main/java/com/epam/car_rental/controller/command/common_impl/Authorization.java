package com.epam.car_rental.controller.command.common_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.user.UserNotFoundException;
import com.epam.car_rental.service.user.UserService;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.ControlConst.DEFAULT_LANG;
import static com.epam.car_rental.controller.constant.ControlConst.LANG;
import static com.epam.car_rental.controller.constant.EntityAttributes.LOGIN;
import static com.epam.car_rental.controller.constant.EntityAttributes.PASSWORD;
import static com.epam.car_rental.controller.constant.EntityAttributes.USER;
import static com.epam.car_rental.controller.constant.PageUrl.HOME_PAGE;
import static com.epam.car_rental.controller.constant.PageUrl.LOGIN_PAGE;

public class Authorization implements Command {
    private static final Logger LOGGER = Logger.getLogger(Authorization.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String sessionLang = (String) session.getAttribute(LANG);
        String lang = sessionLang != null ? sessionLang : DEFAULT_LANG;
        session.setAttribute(LANG, lang);
        UserService userService = ServiceFactory.getInstance().getUserService();
        try{
            User user = userService.getUser(login,password);
            session.setAttribute(USER,user);
            if(user.getRole().toString().equals("ADMIN")){
                String address = ControllerUtil.createAddressWithPaging(request,CommandType.SHOW_NEW_ORDERS.toString(),""+1);
                response.sendRedirect(address);
            }else {
                String address = ControllerUtil.createAddressWithPaging(request,CommandType.USER_ORDERS.toString(),1+"");
                response.sendRedirect(address);
            }
        }catch (UserNotFoundException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateWithMessage(request,response,e.getMessage(),LOGIN_PAGE);
        }catch (ServiceException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateWithMessage(request,response,e.getMessage(),LOGIN_PAGE);
        }
    }
}
