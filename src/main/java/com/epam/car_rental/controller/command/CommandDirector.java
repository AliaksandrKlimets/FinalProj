package com.epam.car_rental.controller.command;

import com.epam.car_rental.controller.command.admin_impl.*;
import com.epam.car_rental.controller.command.common_impl.*;
import com.epam.car_rental.controller.command.user_impl.*;
import com.epam.car_rental.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDirector {

    private Map<CommandType, CommandRights> commands = new HashMap<>();
    private Map<CommandType, Command> nonRoleCommands = new HashMap<>();

    private static CommandDirector instance;

    private CommandDirector() {

        List<User.Role> rights = new ArrayList<>();
        rights.add(User.Role.ADMIN);
        commands.put(CommandType.ADD_NEW_CAR, new CommandRights(rights, new AddNewCar()));
        commands.put(CommandType.ADD_USER_FINE, new CommandRights(rights, new AddUserFine()));
        commands.put(CommandType.CAR_DELETING, new CommandRights(rights, new CarDeleting()));
        commands.put(CommandType.CHANGE_FINE_PAYMENT_STATE, new CommandRights(rights, new ChangeFinePaymentState()));
        commands.put(CommandType.CHANGE_ORDER_PAYMENT_STATE, new CommandRights(rights, new ChangeOrderPaymentState()));
        commands.put(CommandType.CHANGE_ORDER_STATE, new CommandRights(rights, new ChangeOrderState()));
        commands.put(CommandType.FINE_DELETING, new CommandRights(rights, new FineDeleting()));
        commands.put(CommandType.SHOW_ALL_FINES, new CommandRights(rights, new ShowAllFines()));
        commands.put(CommandType.SHOW_ALL_ORDERS, new CommandRights(rights, new ShowAllOrders()));
        commands.put(CommandType.SHOW_NEW_ORDERS, new CommandRights(rights, new ShowNewOrders()));
        commands.put(CommandType.SHOW_ALL_USERS, new CommandRights(rights, new ShowAllUsers()));
        commands.put(CommandType.SHOW_CAR_ORDERS, new CommandRights(rights, new ShowCarOrders()));
        commands.put(CommandType.SHOW_UNPAID_FINES, new CommandRights(rights, new ShowUnpaidFines()));

        rights = new ArrayList<>();
        rights.add(User.Role.USER);
        rights.add(User.Role.ADMIN);
        commands.put(CommandType.SHOW_ALL_CARS, new CommandRights(rights, new ShowAllCars()));
        commands.put(CommandType.SHOW_ALL_CARS_BY_TYPE, new CommandRights(rights, new ShowAllCarsByType()));
        commands.put(CommandType.LOG_OFF, new CommandRights(rights, new LogOff()));
        commands.put(CommandType.ADDING_HELP, new CommandRights(rights, new AddingHelper()));

        rights = new ArrayList<>();
        rights.add(User.Role.USER);
        commands.put(CommandType.ORDER_DELETING, new CommandRights(rights, new OrderDeleting()));
        commands.put(CommandType.CHANGING_EMAIL, new CommandRights(rights, new ChangingEmail()));
        commands.put(CommandType.CHANGING_PASSWORD, new CommandRights(rights, new ChangingPassword()));
        commands.put(CommandType.CHANGING_PHONE, new CommandRights(rights, new ChangingPhone()));
        commands.put(CommandType.ORDER_ADDING, new CommandRights(rights, new OrderAdding()));
        commands.put(CommandType.USER_DELETING, new CommandRights(rights, new UserDeleting()));
        commands.put(CommandType.USER_FINES, new CommandRights(rights, new UserFines()));
        commands.put(CommandType.USER_ORDERS, new CommandRights(rights, new UserOrders()));

        nonRoleCommands.put(CommandType.LOCALE_CHANGING, new LocaleChanging());
        nonRoleCommands.put(CommandType.AUTHORIZATION, new Authorization());
        nonRoleCommands.put(CommandType.REGISTRATION, new Registration());


    }

    public static CommandDirector getInstance() {
        if (instance == null) {
            instance = new CommandDirector();
        }
        return instance;
    }

    public Command getCommand(String command) {
        CommandType commandValue = CommandType.valueOf(command);
        if (isNonRole(command)) {
            return nonRoleCommands.get(commandValue);
        } else {
            return commands.get(commandValue).getCommand();
        }
    }

    public void checkAccess(String commandName, User user) throws AccessNotAllowedException {
        final boolean changeLocale = commandName.equalsIgnoreCase(CommandType.LOCALE_CHANGING.toString());
        if (changeLocale) {
            return;
        }
        final boolean showCars = commandName.equalsIgnoreCase(CommandType.SHOW_ALL_CARS.toString());
        if (showCars) {
            return;
        }

        final boolean showCarsByType = commandName.equalsIgnoreCase(CommandType.SHOW_ALL_CARS_BY_TYPE.toString());
        if (showCarsByType) {
            return;
        }

        if (isNonRole(commandName) && user == null) {
            return;
        }

        if (!isNonRole(commandName) && user != null) {
            CommandType type = CommandType.valueOf(commandName);
            CommandRights commandRights = commands.get(type);
            List<User.Role> rights = commandRights.getRights();
            boolean accessGranted = rights.contains(user.getRole());
            if (accessGranted) {
                return;
            }
        }
        throw new AccessNotAllowedException("Access is not allowed");
    }


    private boolean isNonRole(String value) {
        CommandType commandType = CommandType.valueOf(value);
        return nonRoleCommands.containsKey(commandType);
    }

}
