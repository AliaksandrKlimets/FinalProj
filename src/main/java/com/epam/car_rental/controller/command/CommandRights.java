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

    /**
     * Returns current {@link Command}.
     *
     * @return command
     */

    public Command getCommand() {
        return command;
    }

    /**
     * Change current {@link Command}.
     *
     * @param command
     * new command
     */

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Returns {@link User} rights.
     *
     * @return users rights {@link List}
     */

    public List<User.Role> getRights() {
        return rights;
    }

}
