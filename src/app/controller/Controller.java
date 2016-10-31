package app.controller;


import app.bean.Request;
import app.bean.Response;
import app.command.Command;
import app.command.exception.CommandException;

public class Controller {
	private CommandHelper helper = new CommandHelper();
	
	
	public Controller(){}
	
	public Response doRequest(Request request){
		String commandName = request.getCommandName();
		
		Command command = helper.getCommand(commandName);
		
		Response response;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			// logging
			response = new Response();
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
		}
		return response;
		
	}

}
