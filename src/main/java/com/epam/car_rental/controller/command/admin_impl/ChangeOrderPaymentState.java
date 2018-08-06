package com.epam.car_rental.controller.command.admin_impl;

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
import static com.epam.car_rental.controller.constant.EntityAttributes.ID;

public class ChangeOrderPaymentState implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeOrderPaymentState.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ID);
        String number = request.getParameter(NUMBER);

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        try{
            Validator.isNumber(number);
            orderService.changePaymentState(id, "PAID");
            String address = ControllerUtil.createAddressWithPaging(request,CommandType.SHOW_ALL_ORDERS.toString(),number);
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
