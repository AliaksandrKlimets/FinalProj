package com.epam.car_rental.entity;

import java.sql.Date;
import java.util.Objects;

public class User {
    private int userId;
    private String login;
    private String password;
    private Role role;

    private String name;
    private String surname;
    private String email;
    private String phone;
    private Date birthDate;
    private Date registrationDate;


    public User() {
    }

    public enum Role {
        USER, ADMIN;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
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
    public String toString() {
        return "id " + userId
                + " login " + login
                + " password " + password
                + " role " + role
                + " name " + name
                + " surname " + surname
                + " email " + email
                + " phone " + phone
                + " birth date " + birthDate
                + " registration date " + registrationDate;
    }

    @Override
    public int hashCode() {
        return userId * 32 +
                + login.hashCode()
                + password.hashCode()
                + role.hashCode()
                + name.hashCode()
                + surname.hashCode()
                + email.hashCode()
                + phone.hashCode()
                + birthDate.hashCode()
                + registrationDate.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(registrationDate, user.registrationDate);
    }
}
