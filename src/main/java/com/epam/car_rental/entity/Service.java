package com.epam.car_rental.entity;

import java.util.Objects;

public class Service {
    private int serviceId;
    private Services service;
    private int carId;
    private double costPerHour;
    private double oneToSevenDays;
    private double eightToFifteen;
    private double sixteenAndMore;

    public Service() {

    }

    public enum Services {
        LEASE("Аренда"), LEASE_WITH_DRIVER("Аренда с водителем");

        Services(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private String value;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Services getService() {
        return service;
    }

    public void setService(String service) {
        this.service = Services.valueOf(service);
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public double getOneToSevenDays() {
        return oneToSevenDays;
    }

    public void setOneToSevenDays(double oneToSevenDays) {
        this.oneToSevenDays = oneToSevenDays;
    }

    public double getEightToFifteen() {
        return eightToFifteen;
    }

    public void setEightToFifteen(double eightToFifteen) {
        this.eightToFifteen = eightToFifteen;
    }

    public double getSixteenAndMore() {
        return sixteenAndMore;
    }

    public void setSixteenAndMore(double sixteenAndMore) {
        this.sixteenAndMore = sixteenAndMore;
    }

    @Override
    public String toString() {
        return "id " + serviceId
                + " info " + service
                + " car id " + carId
                + " 1 hour " + costPerHour
                + " 1 - 7 " + oneToSevenDays
                + " 8 - 15 " + eightToFifteen
                + " 16+ " + sixteenAndMore;
    }

    @Override
    public int hashCode() {
        return serviceId * 32
                + service.hashCode()
                + carId * 33
                + (int) costPerHour * 34
                + (int) oneToSevenDays * 35
                + (int) eightToFifteen * 36
                + (int) sixteenAndMore * 37;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service ser = (Service) o;
        return Objects.equals(serviceId, ser.serviceId) &&
                Objects.equals(service, ser.service) &&
                Objects.equals(carId, ser.carId) &&
                Objects.equals(costPerHour, ser.costPerHour) &&
                Objects.equals(oneToSevenDays, ser.oneToSevenDays) &&
                Objects.equals(eightToFifteen, ser.eightToFifteen) &&
                Objects.equals(sixteenAndMore, ser.eightToFifteen);
    }
}
