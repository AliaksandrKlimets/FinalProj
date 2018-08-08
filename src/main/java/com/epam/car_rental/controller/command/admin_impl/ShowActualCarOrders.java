package com.epam.car_rental.controller.command.admin_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandType;
import com.epam.car_rental.entity.OrderedCar;
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

import static com.epam.car_rental.controller.constant.ControlConst.NUMBER;
import static com.epam.car_rental.controller.constant.ControlConst.PAGE;
import static com.epam.car_rental.controller.constant.EntityAttributes.ID;
import static com.epam.car_rental.controller.constant.PageUrl.HOME_PAGE;


public class ShowActualCarOrders implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowActualCarOrders.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter(ID);
        String page = request.getParameter(NUMBER);
        String pageNumber = request.getParameter("numberPage");

        CarService carService = ServiceFactory.getInstance().getCarService();
        try{
            Validator.isNumber(page);
            int currentPage = Integer.parseInt(page);
            Validator.isNumber(carId);
            request.setAttribute(ID, carId);
            Validator.isNumber(pageNumber);
            request.setAttribute("numberPage",pageNumber);
            int sizeAct = carService.actualCarOrdersCount(Integer.parseInt(carId));
            if(sizeAct!=0){
                String command = CommandType.SHOW_ACTUAL_CAR_ORDERS.toString();
                PaginationHelper helper = ControllerUtil.createPagination(request, currentPage, sizeAct, command, 10);
                request.setAttribute(PAGE, helper);

                List<OrderedCar> orderedCarList = carService.getActualCarOrders(carId, helper.getBegin(), 10);
                request.setAttribute("carOrders",orderedCarList);
                request.getRequestDispatcher(HOME_PAGE).forward(request,response);
            }else {
                request.setAttribute("carOrders",new ArrayList<>());
                request.getRequestDispatcher(HOME_PAGE).forward(request,response);
            }
        }catch (InputException e) {
            LOGGER.error(e.getMessage());
            response.sendRedirect(HOME_PAGE);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendRedirect(HOME_PAGE);
        }
    }
}
