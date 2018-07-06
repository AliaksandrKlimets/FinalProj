package com.epam.car_rental.service;

import com.epam.car_rental.service.car.CarService;
import com.epam.car_rental.service.car.CarServiceImpl;
import com.epam.car_rental.service.info.*;
import com.epam.car_rental.service.user.UserService;
import com.epam.car_rental.service.user.UserServiceImpl;

public class ServiceFactory {
    private static volatile ServiceFactory instance;

    private final CarService carService = new CarServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final FineService fineService = new FineServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final ServiceService serviceService = new ServiceServiceImpl();

    private ServiceFactory() {
    }

    public ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public CarService getCarService() {
        return carService;
    }

    public UserService getUserService() {
        return userService;
    }

    public FineService getFineService() {
        return fineService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }
}
