package com.epam.car_rental.controller.command.user_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.entity.Order;
import com.epam.car_rental.entity.OrderedCar;
import com.epam.car_rental.service.DateNotAvailableServiceException;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.car.CarNotFoundException;
import com.epam.car_rental.service.car.CarService;
import com.epam.car_rental.service.info.OrderService;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.epam.car_rental.controller.constant.EntityAttributes.ID;
import static com.epam.car_rental.controller.constant.EntityAttributes.USER;
import static com.epam.car_rental.controller.constant.PageUrl.LOGIN_PAGE;

public class OrderAdding implements Command {
    private static final Logger LOGGER = Logger.getLogger(OrderAdding.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter(ID);
        String userId = request.getParameter("userId");
        String passport = request.getParameter("passportNumber");
        String idNumber = request.getParameter("idNumber");
        String dateOfExpiry = request.getParameter("dateOfExpiry");
        String serviceStart = request.getParameter("serviceStart");
        String serviceEnd = request.getParameter("serviceEnd");

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        CarService carService = ServiceFactory.getInstance().getCarService();
        try{
            if(Date.valueOf(serviceStart).getTime()>Date.valueOf(serviceEnd).getTime()){
                ControllerUtil.updateWithMessage(request,response,"Данные введены неправильно",
                        "/rental?command=ADDING_HELP&add_param=order&id="+carId);
            }
            carService.isDateAvailable(carId,serviceStart,serviceEnd);
            Order order = new Order();
            order.setServiceCost(ControllerUtil.createServiceCost(carId,serviceStart,serviceEnd));
            order.setPassportNumber(passport);
            order.setIdentificationNumber(idNumber);
            orderService.addOrder(order,userId,carId,dateOfExpiry,serviceStart,serviceEnd);

            OrderedCar orderedCar = new OrderedCar();
            orderedCar.setUserId(Integer.parseInt(userId));
            orderedCar.setCarId(Integer.parseInt(carId));
            orderedCar.setBeginDate(Date.valueOf(serviceStart));
            orderedCar.setEndDate(Date.valueOf(serviceEnd));
            carService.addCarToOrderedCarList(orderedCar);

            response.sendRedirect("/rental?command=USER_ORDERS&number=1");
        }catch (CarNotFoundException e){
            ControllerUtil.updateWithMessage(request,response,"Авто отсутствует",
                    "/rental?command=ADDING_HELP&add_param=order&id="+carId);
        }catch (DateNotAvailableServiceException e){
            ControllerUtil.updateWithMessage(request,response,"Данная дата уже занята",
                    "/rental?command=ADDING_HELP&add_param=order&id="+carId);
        }catch (InvalidParametersException e){
            ControllerUtil.updateWithMessage(request,response,"Данные введены неправильно",
                    "/rental?command=ADDING_HELP&add_param=order&id="+carId);
        }catch (ServiceException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
