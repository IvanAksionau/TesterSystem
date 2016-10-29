package controller;

import command.Command;
import command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandHelper() {

        commands.put("REGISTERED_USER", new UserRegistration());
        commands.put("LOGIN_USER", new UserLogging());
        commands.put("ADD_NEW_TEST", new AddNewTest());
        commands.put("SHOW_All_TESTS", new ShowAllTests());
        commands.put("PASS_TEST", new PassTest());
    }

    public Command getCommand(String commandName) {
        Command command;
        command = commands.get(commandName);
        return command;
    }

}
