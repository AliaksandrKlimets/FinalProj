package com.epam.car_rental.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerUtil {

    private static final int ROWS_ON_PAGE = 15;
    private static final String ERROR = "error";
    private static final String CONTROLLER_COMMAND = "/hostel_system?command=";
    private static final String PAGE = "&number=";

    public static void updateWithMessage(HttpServletRequest request, HttpServletResponse response, String message, String errorPageUrl) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute(ERROR, message);
        requestDispatcher = request.getRequestDispatcher(errorPageUrl);
        requestDispatcher.forward(request, response);
    }

}
