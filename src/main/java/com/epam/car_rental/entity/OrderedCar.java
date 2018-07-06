package com.epam.car_rental.entity;

import java.util.Date;
import java.util.Objects;

public class OrderedCar {
    private int carOrderId;
    private int carId;
    private int userId;
    private Date beginDate;
    private Date endDate;

    public OrderedCar() {

    }

    public int getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(int carOrderId) {
        this.carOrderId = carOrderId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "car order id" + carOrderId
                + " car id " + carId
                + " user id " + userId
                + " begin date " + beginDate
                + " end date " + endDate;
    }

    @Override
    public int hashCode() {
        return carId * 32
                + userId + 33
                + beginDate.hashCode()
                + endDate.hashCode()
                + carOrderId * 34;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedCar orderedCar = (OrderedCar) o;
        return Objects.equals(carId, orderedCar.carId) &&
                Objects.equals(userId, orderedCar.userId) &&
                Objects.equals(beginDate, orderedCar.beginDate) &&
                Objects.equals(endDate, orderedCar.endDate) &&
                Objects.equals(carOrderId, orderedCar.carOrderId);
    }
}
