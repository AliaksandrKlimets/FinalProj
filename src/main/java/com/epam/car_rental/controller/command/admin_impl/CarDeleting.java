package com.epam.car_rental.controller.command.admin_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.car.CarService;
import com.epam.car_rental.service.validation.InvalidParametersException;
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
import static com.epam.car_rental.controller.constant.PageUrl.CAR_PAGE;

public class CarDeleting implements Command {
    private static final Logger LOGGER = Logger.getLogger(CarDeleting.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter(ID);
        String number = request.getParameter(NUMBER);
        String address = CAR_PAGE;
        CarService carService = ServiceFactory.getInstance().getCarService();
        try{
            Validator.isNumber(number);
            address = ControllerUtil.createAddressWithPaging(request,CommandType.SHOW_ALL_CARS.toString(),number);
            carService.deleteCar(carId);
            response.sendRedirect(address);
        }catch (NotNumberException e){
            LOGGER.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }catch (ServiceException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateWithMessage(request,response,"Error", address);
        }
    }
}
