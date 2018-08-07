package com.epam.car_rental.controller.command.admin_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.entity.Order;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.car.CarService;
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

public class ChangeOrderState implements Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeOrderState.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String change = request.getParameter(CHANGE);
        String number = request.getParameter(NUMBER);
        String reason = request.getParameter(REASON);
        String id = request.getParameter(ID);

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        CarService carService = ServiceFactory.getInstance().getCarService();
        try{
            Validator.isNumber(number);
            orderService.changeOrderState(id,change);
            if(change.equalsIgnoreCase("DECLINE")){
                Order order = orderService.getOrder(id);
                carService.deleteCarFromOrderedCarList(order.getCarId()+"", order.getServiceStart().toString(),order.getServiceEnd().toString());
            }
            if(reason != null){
                orderService.addDeclineReason(id,reason);
            }
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
