package com.epam.car_rental.controller.command.admin_impl;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.info.FineService;
import com.epam.car_rental.service.validation.InvalidParametersException;
import com.epam.car_rental.util.ControllerUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.EntityAttributes.ID;
import static com.epam.car_rental.controller.constant.EntityAttributes.NAME;

public class AddUserFine implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddUserFine.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter(ID);
        String name = request.getParameter(NAME);
        String carId = request.getParameter("carId");
        String cause = request.getParameter("cause");
        String bill = request.getParameter("bill");
        String date = request.getParameter("dueDate");

        FineService fineService = ServiceFactory.getInstance().getFineService();
        try{
            fineService.addFine(userId,carId,cause,bill,date);
            response.sendRedirect("/adding");
        }catch (InvalidParametersException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateWithMessage(request,response,"Проверьте введенные данные","/rental?command=ADDING_HELP&add_param=fine&id="+userId+"&name="+name);
        }catch (ServiceException e){
            LOGGER.error(e.getMessage());
            ControllerUtil.updateWithMessage(request,response,"Произошла ошибка, повторите попытку.","/rental?command=ADDING_HELP&add_param=fine&id="+userId+"&name="+name);
        }
    }
}
