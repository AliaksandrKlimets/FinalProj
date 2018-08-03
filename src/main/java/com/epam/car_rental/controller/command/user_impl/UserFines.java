package com.epam.car_rental.controller.command.user_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.entity.Fine;
import com.epam.car_rental.entity.PaginationHelper;
import com.epam.car_rental.entity.User;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.info.FineService;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.validator.Validator;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.epam.car_rental.controller.constant.ControlConst.*;

public class UserFines implements Command {
    private static final Logger LOGGER = Logger.getLogger(UserFines.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(USER);

        String pageNumber = request.getParameter(NUMBER);
        FineService fineService = ServiceFactory.getInstance().getFineService();
        try{
            Validator.isNumber(pageNumber);
            int currentPage = Integer.parseInt(pageNumber);
            User currentUser = (User) user;
            int size = fineService.userFinesCount(currentUser.getUserId());
            if(size!=0) {
                String command = CommandType.USER_FINES.toString();
                PaginationHelper helper = ControllerUtil.createPagination(request, currentPage, size, command);
                request.setAttribute(PAGE, helper);

                List<Fine> fineList = fineService.getUserFines("" + currentUser.getUserId(), helper.getBegin(), 10);
                request.setAttribute(FINES, fineList);

                request.getRequestDispatcher("/home").forward(request, response);
            }else {
                request.setAttribute("noItems",1);
                request.getRequestDispatcher("/home").forward(request, response);
            }
        }catch (InputException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }catch (ServiceException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
