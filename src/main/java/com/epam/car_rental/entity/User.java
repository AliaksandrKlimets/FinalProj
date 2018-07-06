package com.epam.car_rental.entity;

import java.util.Objects;

public class User {
    private int userId;
    private String login;
    private String password;
    private Role role;


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

    @Override
    public String toString() {
        return "id " + userId
                + " login " + login
                + " password " + password
                + " role " + role;
    }

    @Override
    public int hashCode() {
        return userId * 32 + login.hashCode() + password.hashCode() + role.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }
}
