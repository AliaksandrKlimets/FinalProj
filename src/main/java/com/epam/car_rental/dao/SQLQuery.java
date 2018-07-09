package com.epam.car_rental.dao;

public class SQLQuery {
    public static final String USER_ALL_USERS = "user.all.users";
    public static final String USER_SEARCH_USER = "user.search.user";
    public static final String USER_UPDATE_LOGIN = "user.update.login";
    public static final String USER_UPDATE_PASSWORD = "user.update.password";
    public static final String USER_ADD_USER = "user.add.user";
    public static final String USER_DELETE_USER = "user.delete.user";
    public static final String USER_CHECK_LOGIN = "user.check.login";

    public static final String USER_INFO_GET_INFO = "user.info.get.info";
    public static final String USER_INFO_ADD_INFO = "user.info.add.info.";
    public static final String USER_INFO_CHANGE_NAME = "user.info.change.name";
    public static final String USER_INFO_CHANGE_SURNAME = "user.info.change.surname";
    public static final String USER_INFO_CHANGE_EMAIL = "user.info.change.email";
    public static final String USER_INFO_CHANGE_PHONE = "user.info.change.phone";
    public static final String USER_INFO_DELETE_INFO = "user.info.delete.info";

    public static final String CAR_GET_CARS = "car.get.cars";
    public static final String CAR_GET_CAR = "car.get.car";
    public static final String CAR_DELETE_CAR = "car.delete.car";
    public static final String CAR_ADD_CAR = "car.add.car";
    public static final String CAR_CHECK_CAR = "car.check.car";
    public static final String CAR_GET_CARS_BY_TYPE = "car.get.cars.by.type";

    public static final String ORDERED_CAR_GET_CARS = "ordered.car.get.cars";
    public static final String ORDERED_CAR_GET_CAR_ORDERS ="ordered.car.get.car.orders";
    public static final String ORDERED_CAR_GET_ACTUAL_CAR_ORDERS ="ordered.car.get.actual.car.orders";
    public static final String ORDERED_CAR_ADD_CAR_ORDER = "ordered.car.add.car.order";
    public static final String ORDERED_CAR_DELETE_CAR_ORDER = "order.car.delete.car.order";

    public static final String FINE_GET_FINES = "fine.get.fines";
    public static final String FINE_GET_FINES_UNPAID = "fine.get.fines.unpaid";
    public static final String FINE_GET_USER_FINES = "fine.get.user.fines";
    public static final String FINE_GET_FINE = "fine.get.fine";
    public static final String FINE_CHANGE_PAYMENT_STATE = "fine.change.payment.state";
    public static final String FINE_DELETE_FINE = "fine.delete.fine";
    public static final String FINE_ADD_FINE = "fine.add.fine";

    public static final String ORDER_GET_ORDER = "order.get.order";
    public static final String ORDER_GET_ORDERS = "order.get.orders";
    public static final String ORDER_GET_USER_ORDERS = "order.get.user.orders";
    public static final String ORDER_CHANGE_PAYMENT_STATE = "order.change.payment.state";
    public static final String ORDER_CHANGE_ORDER_STATE = "order.change.order.state";
    public static final String ORDER_ADD_DECLINE_REASON = "order.add.decline.reason";
    public static final String ORDER_ADD_ORDER = "order.add.order";
    public static final String ORDER_DELETE_ORDER = "order.delete.order";

    public static final String SERVICE_GET_SERVICES ="service.get.services";
    public static final String SERVICE_ADD_SERVICE ="service.add.service";
    public static final String SERVICE_DELETE_SERVICES ="service.delete.service";
    public static final String SERVICE_GET_CARS_BY_SERVICE ="service.get.cars.by.service";
}
