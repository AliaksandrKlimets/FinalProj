package com.epam.car_rental.controller.command;

public enum CommandType {

    ADD_NEW_CAR,
    ADD_USER_FINE,
    CAR_DELETING,
    CHANGE_FINE_PAYMENT_STATE,
    CHANGE_ORDER_PAYMENT_STATE,
    CHANGE_ORDER_STATE,
    FINE_DELETING,
    SHOW_ALL_FINES,
    SHOW_ALL_ORDERS,
    SHOW_NEW_ORDERS,
    SHOW_ALL_USERS,
    SHOW_CAR_ORDERS,
    SHOW_ACTUAL_CAR_ORDERS,
    SHOW_UNPAID_FINES,

    AUTHORIZATION,
    LOCALE_CHANGING,
    LOG_OFF,
    ORDER_DELETING,
    REGISTRATION,
    SHOW_ALL_CARS,
    SHOW_ALL_CARS_BY_TYPE,
    ADDING_HELP,

    CHANGING_EMAIL,
    CHANGING_PASSWORD,
    CHANGING_PHONE,
    ORDER_ADDING,
    USER_DELETING,
    USER_FINES,
    USER_ORDERS
}
