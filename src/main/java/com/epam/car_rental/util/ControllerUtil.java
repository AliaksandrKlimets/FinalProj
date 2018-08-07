package com.epam.car_rental.util;

import com.epam.car_rental.dao.DAOException;
import com.epam.car_rental.entity.Car;
import com.epam.car_rental.entity.PaginationHelper;
import com.epam.car_rental.service.ServiceException;
import com.epam.car_rental.service.ServiceFactory;
import com.epam.car_rental.service.car.CarNotFoundException;
import com.epam.car_rental.service.car.CarService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class ControllerUtil {

    private static final int ROWS_ON_PAGE = 10;
    private static final String ERROR = "error";
    private static final String CONTROLLER_COMMAND = "/rental?command=";
    private static final String PAGE = "&number=";

    public static PaginationHelper createPagination(HttpServletRequest request, int current, int size, String command) {
        String controllerURL = request.getContextPath() + CONTROLLER_COMMAND + command + PAGE;
        int first = 1;

        PaginationHelper page = new PaginationHelper();

        int lastPage = size / ROWS_ON_PAGE;
        if (size % ROWS_ON_PAGE != 0) {
            lastPage++;
        }
        page.setLast(lastPage);

        current = lastPage < current ? lastPage : current;
        page.setCurrent(current);

        int prev = current - 1;
        page.setPrev(prev);

        int next = current + 1;
        page.setNext(next);


        int begin = ROWS_ON_PAGE * prev;
        page.setBegin(begin);

        int end = ROWS_ON_PAGE * current;
        end = end > size ? size : end;
        page.setEnd(end);

        page.setFirstPage(controllerURL + first);
        page.setPrevPage(controllerURL + prev);
        page.setNextPage(controllerURL + next);
        page.setLastPage(controllerURL + lastPage);
        return page;
    }


    public static void updateWithMessage(HttpServletRequest request, HttpServletResponse response, String message, String errorPageUrl) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute(ERROR, message);
        requestDispatcher = request.getRequestDispatcher(errorPageUrl);
        requestDispatcher.forward(request, response);
    }

    public static void updateParamWithMessage(HttpServletRequest request, HttpServletResponse response, String message, String errorPageUrl, String param) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute(param, message);
        requestDispatcher = request.getRequestDispatcher(errorPageUrl);
        requestDispatcher.forward(request, response);
    }

    public static String createAddressWithPaging(HttpServletRequest request, String command, String page){
        return request.getContextPath() + CONTROLLER_COMMAND + command + PAGE + page;
    }

    public static double createServiceCost(String carId, String serviceStart, String serviceEnd) throws  ServiceException {
        CarService carService = ServiceFactory.getInstance().getCarService();
        Car car = carService.getCar(carId);
        final long day = 86_400_000L;

        long difference = Date.valueOf(serviceEnd).getTime()-Date.valueOf(serviceStart).getTime();
        long fullDays = difference/day;

        if(fullDays<2){
            return fullDays * car.getCostPerDay();
        }else if(fullDays >= 2 && fullDays<8){
            return fullDays * car.getTwoToSevenDays();
        }else if(fullDays >= 8 && fullDays < 16){
            return fullDays * car.getEightToFifteen();
        }else {
            return fullDays * car.getSixteenAndMore();
        }


    }
}
