package com.epam.car_rental.controller.command.admin_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.entity.Order;
import com.epam.car_rental.entity.PaginationHelper;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.info.OrderService;
import com.epam.car_rental.service.validation.InputException;
import com.epam.car_rental.service.validation.validator.Validator;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.car_rental.controller.constant.ControlConst.*;

public class ShowAllOrders implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAllOrders.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageNumber = request.getParameter(NUMBER);
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        try{
            Validator.isNumber(pageNumber);
            int currentPage = Integer.parseInt(pageNumber);
            int size = orderService.ordersCount();
            if(size!=0) {
                String command = CommandType.SHOW_ALL_ORDERS.toString();
                PaginationHelper helper = ControllerUtil.createPagination(request, currentPage, size, command);
                request.setAttribute(PAGE, helper);

                List<Order> orderList = orderService.getOrders(helper.getBegin(), 5);
                request.setAttribute(ORDERS, orderList);


                request.getRequestDispatcher("/home").forward(request, response);
            }else {
                request.setAttribute(ORDERS, new ArrayList<>());
                request.getRequestDispatcher("/home").forward(request, response);
            }
        }catch (InputException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }catch (ServiceException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
