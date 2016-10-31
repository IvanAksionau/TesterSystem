package app.controller;

import app.command.Command;
import app.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private final String REGISTERED_USER = "REGISTERED_USER";
    private final String LOGIN_USER = "LOGIN_USER";
    private final String ADD_NEW_TEST = "ADD_NEW_TEST";
    private final String SHOW_All_TESTS = "SHOW_All_TESTS";
    private final String PASS_TEST = "PASS_TEST";

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandHelper() {

        commands.put(REGISTERED_USER, new UserRegistration());
        commands.put(LOGIN_USER, new UserLogging());
        commands.put(ADD_NEW_TEST, new AddNewTest());
        commands.put(SHOW_All_TESTS, new ShowAllTests());
        commands.put(PASS_TEST, new PassTest());
    }

    public Command getCommand(String commandName) {
        Command command;
        command = commands.get(commandName);
        return command;
    }

}
