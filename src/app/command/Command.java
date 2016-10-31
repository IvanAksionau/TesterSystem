package app.command;


import app.bean.Request;
import app.bean.Response;
import app.command.exception.CommandException;

public interface Command {
	Response execute(Request request) throws CommandException;
}
