package command;


import bean.Request;
import bean.Response;
import command.exception.CommandException;

public interface Command {
	Response execute(Request request) throws CommandException;
}
