package com.epam.car_rental.util;

import com.epam.car_rental.entity.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DAOUtil {

    public static User createUserFromDB(ResultSet resultSet) throws SQLException {
        User user = new User();
        while (resultSet.next()) {
            int column = 1;
            user.setUserId(resultSet.getInt(column++));
            user.setLogin(resultSet.getString(column++));
            user.setPassword(resultSet.getString(column++));
            user.setRole(resultSet.getString(column));
        }
        return user;
    }

    public static List<User> createUserListFromBD(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            int column = 1;
            user.setUserId(resultSet.getInt(column++));
            user.setLogin(resultSet.getString(column++));
            user.setPassword(resultSet.getString(column++));
            user.setRole(resultSet.getString(column));
            users.add(user);
        }
        return users;
    }

    public static UserInfo createUserInfoFromDB(ResultSet resultSet) throws SQLException {
        UserInfo userInfo = new UserInfo();
        while (resultSet.next()) {
            int column = 1;
            userInfo.setUserId(resultSet.getInt(column++));
            userInfo.setName(resultSet.getString(column++));
            userInfo.setSurname(resultSet.getString(column++));
            userInfo.setEmail(resultSet.getString(column++));
            userInfo.setPhone(resultSet.getString(column++));
            userInfo.setBirthDate(resultSet.getDate(column++));
            userInfo.setRegistrationDate(resultSet.getDate(column));
        }
        return userInfo;
    }

    public static List<UserInfo> createUserInfoListFromDB(ResultSet resultSet) throws SQLException {
        List<UserInfo> userInfoList = new ArrayList<>();
        while (resultSet.next()) {
            UserInfo userInfo = new UserInfo();
            int column = 1;
            userInfo.setUserId(resultSet.getInt(column++));
            userInfo.setName(resultSet.getString(column++));
            userInfo.setSurname(resultSet.getString(column++));
            userInfo.setEmail(resultSet.getString(column++));
            userInfo.setPhone(resultSet.getString(column++));
            userInfo.setBirthDate(resultSet.getDate(column++));
            userInfo.setRegistrationDate(resultSet.getDate(column));
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    public static Car createCarFromDB(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        while (resultSet.next()) {
            int column = 1;
            car.setCarId(resultSet.getInt(column++));
            car.setModel(resultSet.getString(column++));
            car.setYear(String.valueOf(resultSet.getString(column++)));
            car.setConsumption(resultSet.getString(column++));
            car.setEngineCapacity(resultSet.getDouble(column++));
            car.setType(resultSet.getString(column++).toUpperCase());
            car.setTransmission(resultSet.getString(column++));
            car.setFuelType(resultSet.getString(column++).toUpperCase());
            car.setImage(resultSet.getString(column++));
            car.setAddInfo(resultSet.getString(column));
        }
        return car;
    }

    public static List<Car> getCarListFromDB(ResultSet resultSet) throws SQLException {
        List<Car> carList = new ArrayList<>();
        while (resultSet.next()) {
            Car car = new Car();
            int column = 1;
            car.setCarId(resultSet.getInt(column++));
            car.setModel(resultSet.getString(column++));
            car.setYear(String.valueOf(resultSet.getInt(column++)));
            car.setConsumption(resultSet.getString(column++));
            car.setEngineCapacity(resultSet.getDouble(column++));
            car.setType(resultSet.getString(column++).toUpperCase());
            car.setTransmission(resultSet.getString(column++));
            car.setFuelType(resultSet.getString(column++).toUpperCase());
            car.setImage(resultSet.getString(column++));
            car.setAddInfo(resultSet.getString(column));
            carList.add(car);
        }
        return carList;
    }

    public static Order createOrderFromDB(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        while (resultSet.next()) {
            int column = 1;
            order.setOrderId(resultSet.getInt(column++));
            order.setUserId(resultSet.getInt(column++));
            order.setServiceId(resultSet.getInt(column++));
            order.setPassportNumber(resultSet.getString(column++));
            order.setIdentificationNumber(resultSet.getString(column++));
            order.setDateOfExpiry(resultSet.getDate(column++));
            order.setServiceStart(resultSet.getDate(column++));
            order.setServiceEnd(resultSet.getDate(column++));
            order.setServiceCost(resultSet.getDouble(column++));
            order.setOrderState(resultSet.getString(column++).toUpperCase());
            order.setDeclineReason(resultSet.getString(column++));
            order.setPaymentState(resultSet.getString(column).toUpperCase());
        }
    return order;
    }

    public static List<Order> createOrderListFromDB(ResultSet resultSet) throws SQLException{
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            int column = 1;
            Order order = new Order();
            order.setOrderId(resultSet.getInt(column++));
            order.setUserId(resultSet.getInt(column++));
            order.setServiceId(resultSet.getInt(column++));
            order.setPassportNumber(resultSet.getString(column++));
            order.setIdentificationNumber(resultSet.getString(column++));
            order.setDateOfExpiry(resultSet.getDate(column++));
            order.setServiceStart(resultSet.getDate(column++));
            order.setServiceEnd(resultSet.getDate(column++));
            order.setServiceCost(resultSet.getDouble(column++));
            order.setOrderState(resultSet.getString(column++).toUpperCase());
            order.setDeclineReason(resultSet.getString(column++));
            order.setPaymentState(resultSet.getString(column).toUpperCase());
            orderList.add(order);
        }
        return orderList;
    }

    public static Fine createFineFromDB(ResultSet resultSet) throws SQLException{
        Fine fine = new Fine();
        while (resultSet.next()){
            int column = 1;
         fine.setFineId(resultSet.getInt(column++));
         fine.setUserId(resultSet.getInt(column++));
         fine.setCarId(resultSet.getInt(column++));
         fine.setCause(resultSet.getString(column++));
         fine.setRepairBill(resultSet.getDouble(column++));
         fine.setState(resultSet.getString(column++).toUpperCase());
         fine.setDueDate(resultSet.getDate(column));
        }
        return fine;
    }

    public static List<Fine> createFineListFromDB(ResultSet resultSet) throws SQLException{
        List<Fine> fineList = new ArrayList<>();
        while (resultSet.next()){
            int column = 1;
            Fine fine = new Fine();
            fine.setFineId(resultSet.getInt(column++));
            fine.setUserId(resultSet.getInt(column++));
            fine.setCarId(resultSet.getInt(column++));
            fine.setCause(resultSet.getString(column++));
            fine.setRepairBill(resultSet.getDouble(column++));
            fine.setState(resultSet.getString(column++).toUpperCase());
            fine.setDueDate(resultSet.getDate(column));
            fineList.add(fine);
        }
        return fineList;
    }

    public static List<OrderedCar> createOrderedCarListFromDB(ResultSet resultSet) throws SQLException{
        List<OrderedCar> orderedCarList = new ArrayList<>();
        while(resultSet.next()){
            int column =1;
            OrderedCar orderedCar = new OrderedCar();
            orderedCar.setCarOrderId(resultSet.getInt(column++));
            orderedCar.setCarId(resultSet.getInt(column++));
            orderedCar.setUserId(resultSet.getInt(column++));
            orderedCar.setBeginDate(resultSet.getDate(column++));
            orderedCar.setEndDate(resultSet.getDate(column));
            orderedCarList.add(orderedCar);
        }
        return orderedCarList;
    }

    public static List<Service> createServiceListFromDB(ResultSet resultSet)throws SQLException{
        List<Service> serviceList = new ArrayList<>();
        while (resultSet.next()){
            int column = 1;
            Service service = new Service();
            service.setServiceId(resultSet.getInt(column++));
            service.setService(resultSet.getString(column++).toUpperCase());
            service.setCarId(resultSet.getInt(column++));
            service.setCostPerHour(resultSet.getDouble(column++));
            service.setOneToSevenDays(resultSet.getDouble(column++));
            service.setEightToFifteen(resultSet.getDouble(column++));
            service.setSixteenAndMore(resultSet.getDouble(column));
            serviceList.add(service);
        }
        return serviceList;
    }

    public static String createPassword(String password) throws NoSuchAlgorithmException {
        StringBuilder code = new StringBuilder();
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance("MD5");
        byte bytes[] = password.getBytes();
        byte digest[] = messageDigest.digest(bytes);
        for (byte aDigest : digest) {
            code.append(Integer.toHexString(0x0100 + (aDigest & 0x00FF)).substring(1));
        }
        password = code.toString();
        return password;
    }

}
