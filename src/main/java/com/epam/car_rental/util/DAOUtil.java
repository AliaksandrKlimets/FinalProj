package com.epam.car_rental.util;

import com.epam.car_rental.entity.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DAOUtil {

    public static User createUserFromDB(ResultSet resultSet) throws SQLException {
        User user = new User();
        while (resultSet.next()) {
            int column = 1;
            user.setUserId(resultSet.getInt(column++));
            user.setLogin(resultSet.getString(column++));
            user.setPassword(resultSet.getString(column++));
            user.setRole(resultSet.getString(column++));
            user.setName(resultSet.getString(column++));
            user.setSurname(resultSet.getString(column++));
            user.setEmail(resultSet.getString(column++));
            user.setPhone(resultSet.getString(column++));
            user.setBirthDate(resultSet.getDate(column++));
            user.setRegistrationDate(resultSet.getDate(column));
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
            user.setRole(resultSet.getString(column++));
            user.setName(resultSet.getString(column++));
            user.setSurname(resultSet.getString(column++));
            user.setEmail(resultSet.getString(column++));
            user.setPhone(resultSet.getString(column++));
            user.setBirthDate(resultSet.getDate(column++));
            user.setRegistrationDate(resultSet.getDate(column));
            users.add(user);
        }
        return users;
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
//            final String addInfoRegex = "[a-zA-Zа-я-А-Я\\s-\\.]+";
//            Matcher matcher = Pattern.compile(addInfoRegex).matcher(resultSet.getString(column));
//            List<String> resultList = new ArrayList<>();
//            while(matcher.find()){
//                resultList.add(matcher.group().trim());
//            }
//            car.setAddInfo(resultList);
            car.setAddInfo(resultSet.getString(column++));
            car.setCostPerDay(resultSet.getDouble(column++));
            car.setTwoToSevenDays(resultSet.getDouble(column++));
            car.setEightToFifteen(resultSet.getDouble(column++));
            car.setSixteenAndMore(resultSet.getDouble(column));
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
            car.setAddInfo(resultSet.getString(column++));
            car.setCostPerDay(resultSet.getDouble(column++));
            car.setTwoToSevenDays(resultSet.getDouble(column++));
            car.setEightToFifteen(resultSet.getDouble(column++));
            car.setSixteenAndMore(resultSet.getDouble(column));
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
            order.setCarId(resultSet.getInt(column++));
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

    public static List<Order> createOrderListFromDB(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            int column = 1;
            Order order = new Order();
            order.setOrderId(resultSet.getInt(column++));
            order.setUserId(resultSet.getInt(column++));
            order.setCarId(resultSet.getInt(column++));
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

    public static Fine createFineFromDB(ResultSet resultSet) throws SQLException {
        Fine fine = new Fine();
        while (resultSet.next()) {
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

    public static List<Fine> createFineListFromDB(ResultSet resultSet) throws SQLException {
        List<Fine> fineList = new ArrayList<>();
        while (resultSet.next()) {
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

    public static List<OrderedCar> createOrderedCarListFromDB(ResultSet resultSet) throws SQLException {
        List<OrderedCar> orderedCarList = new ArrayList<>();
        while (resultSet.next()) {
            int column = 1;
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

    public static void deleteEntity(int id, String query, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public static void changeInDB(int id, String value, String query, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, value);
        statement.setInt(2, id);
        statement.executeUpdate();
    }

}
