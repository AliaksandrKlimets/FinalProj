package com.epam.car_rental.controller.command.admin_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.entity.PaginationHelper;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.info.FineService;
import com.epam.car_rental.service.user.UserService;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.validator.Validator;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.car_rental.controller.constant.ControlConst.*;

public class ShowAllUsers implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAllUsers.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNumber = request.getParameter(NUMBER);
        UserService userService = ServiceFactory.getInstance().getUserService();
        try{
            Validator.isNumber(pageNumber);
            int currentPage = Integer.parseInt(pageNumber);
            int size = userService.usersCount();
            if(size!=0) {
                String command = CommandType.SHOW_ALL_USERS.toString();
                PaginationHelper helper = ControllerUtil.createPagination(request, currentPage, size, command, 10);
                request.setAttribute(PAGE, helper);

                List<User> users = userService.getUsers(helper.getBegin(), 10);
                request.setAttribute(USERS, users);

                request.getRequestDispatcher("/home").forward(request, response);
            }else {
                request.setAttribute("noItems",0);
                request.getRequestDispatcher("/home").forward(request, response);
            }
        }catch (InputException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }catch (ServiceException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
