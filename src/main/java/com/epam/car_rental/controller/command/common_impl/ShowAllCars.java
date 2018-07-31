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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.epam.car_rental.controller.constant.ControlConst.CARLIST;
import static com.epam.car_rental.controller.constant.ControlConst.NUMBER;
import static com.epam.car_rental.controller.constant.ControlConst.PAGE;

public class ShowAllCars implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAllCars.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String number = request.getParameter(NUMBER);

        CarService carService = ServiceFactory.getInstance().getCarService();
        try {
            Validator.isNumber(number);
            int currentPage = Integer.parseInt(number);
            int size = carService.itemsCount();

            String command = CommandType.SHOW_ALL_CARS.toString();
            PaginationHelper helper = ControllerUtil.createPagination(request,currentPage,size,command);
            request.setAttribute(PAGE,helper);
            List<Car> carList = carService.getCars(helper.getBegin(),10);


            request.setAttribute(CARLIST,carList);

            request.getRequestDispatcher("/cars").forward(request, response);
        }catch (InputException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }catch (ServiceException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
