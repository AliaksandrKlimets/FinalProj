package com.epam.car_rental.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {

    private Map<CommandType,CommandRights> commands = new HashMap<>();
    private Map<CommandType,Command> authorizationCommands = new HashMap<>();

    private static CommandDirector instance;

    private CommandDirector(){

    }

    public CommandDirector getInstance(){
        if(instance == null){
            instance = new CommandDirector();
        }
        return instance;
    }

}
