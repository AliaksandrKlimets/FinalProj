package com.epam.car_rental.controller.command.admin_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.car.CarService;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.EntityAttributes.*;

public class AddNewCar implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddNewCar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter(MODEL);
        String year = request.getParameter(YEAR);
        String consumption = request.getParameter(CONSUMPTION);
        String capacity = request.getParameter(CAPACITY);
        String carType = request.getParameter(CAR_TYPE);
        String transmission = request.getParameter(TRANSMISSION);
        String fuelType = request.getParameter(FUEL_TYPE);
        String image = request.getParameter(IMAGE);
        String addInfo = request.getParameter(ADD_INFO);
        String day = request.getParameter(COST_PER_DAY);
        String twoToSeven = request.getParameter(TWO_TO_SEVEN);
        String eightToFifteen = request.getParameter(EIGHT_TO_FIFTEEN);
        String more = request.getParameter(MORE);

        CarService carService = ServiceFactory.getInstance().getCarService();
        try{
            Car car = new Car();
            car.setModel(model);
            car.setYear(year);
            car.setConsumption(consumption);
            car.setTransmission(transmission);
            car.setImage(image);
            car.setAddInfo(addInfo);
            carService.addCar(car,capacity,carType,fuelType,day,twoToSeven,eightToFifteen,more);
            System.out.println("HERE");
            response.sendRedirect("/adding");
        }catch (InvalidParametersException e){
            ControllerUtil.updateWithMessage(request,response,e.getMessage(),"/rental?command=ADDING_HELP&add_param=car");
        }catch (ServiceException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
