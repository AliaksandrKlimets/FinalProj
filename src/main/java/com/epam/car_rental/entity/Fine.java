package com.epam.car_rental.entity;

import java.sql.Date;
import java.util.Objects;

public class Fine {
    private int fineId;
    private int userId;
    private int carId;
    private String cause;
    private double repairBill;
    private State state;
    private Date dueDate;


    public Fine() {

    }

    enum State {
        PAYDED("Оплачено"), NOT_PAYDED("Не оплачено");

        State(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private String value;
    }

    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public double getRepairBill() {
        return repairBill;
    }

    public void setRepairBill(double repairBill) {
        this.repairBill = repairBill;
    }

    public State getState() {
        return state;
    }

    public void setState(String state) {
        this.state = State.valueOf(state);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "id " + fineId
                + " user id " + userId
                + " car id " + carId
                + " cause " + cause
                + " bill " + repairBill
                + " state " + state
                + " due date " + dueDate;
    }

    @Override
    public int hashCode() {
        return fineId * 31
                + userId * 32
                + carId * 33
                + cause.hashCode()
                + (int) repairBill * 34
                + state.hashCode()
                + dueDate.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fine fine = (Fine) o;
        return Objects.equals(fineId, fine.fineId) &&
                Objects.equals(userId, fine.userId) &&
                Objects.equals(carId, fine.carId) &&
                Objects.equals(cause, fine.cause) &&
                Objects.equals(repairBill, fine.repairBill) &&
                Objects.equals(state, fine.state) &&
                Objects.equals(dueDate, fine.dueDate);
    }
}
