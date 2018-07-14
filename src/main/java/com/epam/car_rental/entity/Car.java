package com.epam.car_rental.entity;

import java.util.Objects;

public class Car {
    private int carId;
    private String model;
    private String year;
    private String consumption;
    private Double engineCapacity;
    private Type type;
    private String transmission;
    private FuelType fuelType;
    private String image;
    private String addInfo;
    //private List<String> addInfo;


    public enum Type {
        COUPE("Купе"), HATCHBACK("Хэтчбек"), UNIVERSAL("Универсал"), PICKUP("Пикап"), CROSSOVER("Сроссовер");

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private String value;
    }

    public enum FuelType {
        PETROL("Бензин"), DIESEL("Дизель"), GAS("Газ");

        FuelType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private String value;
    }

    public Car() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = FuelType.valueOf(fuelType);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    @Override
    public String toString() {
        return "id " + carId
                + " model " + model
                + " year " + year
                + " consumption " + consumption
                + " engine capacity " + engineCapacity
                + " car type " + type
                + " transmission " + transmission
                + " fuel type " + fuelType
                + " additional info " + addInfo;
    }

    @Override
    public int hashCode() {
        return carId * 32
                + model.hashCode()
                + year.hashCode()
                + consumption.hashCode()
                + engineCapacity.hashCode()
                + type.hashCode()
                + transmission.hashCode()
                + fuelType.hashCode()
                + addInfo.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) &&
                Objects.equals(model, car.model) &&
                Objects.equals(consumption, car.consumption) &&
                Objects.equals(engineCapacity, car.engineCapacity) &&
                Objects.equals(type, car.type) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(fuelType, car.fuelType) &&
                Objects.equals(image, car.image) &&
                Objects.equals(addInfo, car.addInfo);
    }
}