package com.epam.car_rental.util;

import com.epam.car_rental.entity.PaginationHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public static String createAddressWithPaging(HttpServletRequest request, String command, String page){
        return request.getContextPath() + CONTROLLER_COMMAND + command + PAGE + page;
    }
}
