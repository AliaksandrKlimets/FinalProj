package com.epam.car_rental.controller.command.user_impl;

import com.epam.car_rental.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderAdding implements Command {
    private static final Logger LOGGER = Logger.getLogger(OrderAdding.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}