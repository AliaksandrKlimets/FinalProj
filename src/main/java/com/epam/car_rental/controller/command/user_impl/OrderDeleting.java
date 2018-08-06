package com.epam.car_rental.controller.command.user_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.info.OrderService;
import com.epam.car_rental.service.validation.NotNumberException;
import com.epam.car_rental.service.validation.validator.Validator;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.ControlConst.NUMBER;
import static com.epam.car_rental.controller.constant.EntityAttributes.CHANGE;
import static com.epam.car_rental.controller.constant.EntityAttributes.ID;
import static com.epam.car_rental.controller.constant.EntityAttributes.REASON;

public class OrderDeleting implements Command {
    private static final Logger LOGGER = Logger.getLogger(OrderDeleting.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter(NUMBER);
        String id = request.getParameter(ID);
        String userId = request.getParameter("userId");

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        try{
            Validator.isNumber(number);
            orderService.deleteOrder(id, userId);
            String address = ControllerUtil.createAddressWithPaging(request,CommandType.USER_ORDERS.toString(),number);
            response.sendRedirect(address);
        }catch (NotNumberException e){
            LOGGER.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }catch (ServiceException e){
            LOGGER.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
