package com.epam.car_rental.controller.command;

import com.epam.car_rental.entity.User;

import java.util.List;

public class CommandRights {

    private Command command;
    private List<User.Role> rights;

    public CommandRights( List<User.Role> rights, Command command) {
        this.command = command;
        this.rights = rights;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public List<User.Role> getRights() {
        return rights;
    }

}
