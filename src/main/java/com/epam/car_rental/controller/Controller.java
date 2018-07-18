package com.epam.car_rental.controller;

import com.epam.car_rental.controller.command.Command;
import com.epam.car_rental.controller.command.CommandDirector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.car_rental.controller.constant.ControlConst.COMMAND;

public class Controller extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html";
    private final CommandDirector director = CommandDirector.getInstance();

    public Controller() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(CONTENT_TYPE);
        String commandName = request.getParameter(COMMAND);
        Command command = director.getCommand(commandName);
        command.execute(request, response);

    }
}
