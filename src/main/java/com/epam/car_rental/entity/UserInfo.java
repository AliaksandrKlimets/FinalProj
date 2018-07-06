package com.epam.car_rental.entity;

import java.util.Date;
import java.util.Objects;

public class UserInfo {
    private int userId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Date birthDate;
    private Date registrationDate;


    public UserInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo info = (UserInfo) o;
        return Objects.equals(userId, info.userId) &&
                Objects.equals(name, info.name) &&
                Objects.equals(surname, info.surname) &&
                Objects.equals(email, info.email) &&
                Objects.equals(phone, info.phone) &&
                Objects.equals(birthDate, info.birthDate) &&
                Objects.equals(registrationDate, info.registrationDate);
    }

    @Override
    public String toString() {
        return "id " + userId
                + " name " + name
                + " surname " + surname
                + " email " + email
                + " phone " + phone
                + " birth date " + birthDate
                + " registration date " + registrationDate;
    }

    @Override
    public int hashCode() {
        return userId * 33
                + name.hashCode()
                + surname.hashCode()
                + email.hashCode()
                + phone.hashCode()
                + birthDate.hashCode()
                + registrationDate.hashCode();
    }
}
