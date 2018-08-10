package com.epam.car_rental.controller.command.common_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.PaginationHelper;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.car.CarService;
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

import static com.epam.car_rental.controller.constant.ControlConst.CARLIST;
import static com.epam.car_rental.controller.constant.ControlConst.NUMBER;
import static com.epam.car_rental.controller.constant.ControlConst.PAGE;
import static com.epam.car_rental.controller.constant.EntityAttributes.TYPE;

public class ShowAllCarsByType implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAllCarsByType.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter(NUMBER);
        String type = request.getParameter(TYPE);

        CarService carService = ServiceFactory.getInstance().getCarService();
        try {
            Validator.isNumber(number);
            int currentPage = Integer.parseInt(number);
            int size = carService.itemsByTypeCount(type);
            if (size != 0) {
                String command = CommandType.SHOW_ALL_CARS_BY_TYPE.toString();
                PaginationHelper helper = ControllerUtil.createPagination(request, currentPage, size, command, 7);
                request.setAttribute(PAGE, helper);
                List<Car> carList = carService.getCarsByType(type,helper.getBegin(), 7);

                request.setAttribute(CARLIST, carList);

                request.getRequestDispatcher("/cars").forward(request, response);
            }else {
                request.setAttribute(CARLIST, new ArrayList<>());
                request.getRequestDispatcher("/cars").forward(request, response);
            }
        } catch (InputException e) {
            LOGGER.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
