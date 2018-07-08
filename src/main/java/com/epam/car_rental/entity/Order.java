package com.epam.car_rental.entity;

import com.epam.car_rental.service.info.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private int orderId;
    private int userId;
    private int serviceId;
    private String passportNumber;
    private String identificationNumber;
    private Date dateOfExpiry;
    private Date serviceStart;
    private Date serviceEnd;
    private double serviceCost;
    private OrderState orderState;
    private String declineReason;
    private PaymentState paymentState;


    enum OrderState {
        WAITING("В обработке"), ACCEPT("Одобрено"), DECLINE("Отклонено");

        OrderState(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private String value;
    }

    public enum PaymentState {
        PAID("Оплачено"), UNPAID("Не оплачено");

        PaymentState(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private String value;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public Date getServiceStart() {
        return serviceStart;
    }

    public void setServiceStart(Date serviceStart) {
        this.serviceStart = serviceStart;
    }

    public Date getServiceEnd() {
        return serviceEnd;
    }

    public void setServiceEnd(Date serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = OrderState.valueOf(orderState);
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = PaymentState.valueOf(paymentState);
    }

    @Override
    public String toString() {
        return "id " + orderId
                + " user id " + userId
                + " info id " + serviceId
                + " passport number " + passportNumber
                + " id number " + identificationNumber
                + " date of expiry " + dateOfExpiry
                + " info start " + serviceStart
                + " info end " + serviceEnd
                + " info cost " + serviceCost
                + " order state " + orderState
                + " recline reason " + declineReason
                + " payment state " + paymentState;
    }

    @Override
    public int hashCode() {
        return orderId * 33
                + userId * 34
                + serviceId * 35
                + passportNumber.hashCode()
                + identificationNumber.hashCode()
                + dateOfExpiry.hashCode()
                + serviceStart.hashCode()
                + serviceEnd.hashCode()
                + (int) serviceCost * 36
                + orderState.hashCode()
                + declineReason.hashCode()
                + paymentState.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(userId, order.orderId) &&
                Objects.equals(serviceId, order.serviceId) &&
                Objects.equals(passportNumber, order.passportNumber) &&
                Objects.equals(identificationNumber, order.identificationNumber) &&
                Objects.equals(dateOfExpiry, order.dateOfExpiry) &&
                Objects.equals(serviceStart, order.serviceStart) &&
                Objects.equals(serviceEnd, order.serviceEnd) &&
                Objects.equals(serviceCost, order.serviceCost) &&
                Objects.equals(orderState, order.orderState) &&
                Objects.equals(declineReason, order.declineReason) &&
                Objects.equals(paymentState, order.paymentState);

    }
}
