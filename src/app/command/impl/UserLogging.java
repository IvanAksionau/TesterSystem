package app.command.impl;


import app.bean.Request;
import app.bean.Response;
import app.bean.UserLoggingRequest;
import app.bean.UserLoggingResponse;
import app.command.Command;
import app.command.exception.CommandException;
import app.service.TestAppService;
import app.service.ServiceFactory;
import app.service.exception.ServiceException;

/**
 * Created by Ivan on 12.10.2016.
 */
public class UserLogging implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        UserLoggingRequest req = null;
        if (request instanceof UserLoggingRequest) {
            req = (UserLoggingRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String login = req.getLogin();
        String password = req.getPassword();
        int resultId;
        ServiceFactory service = ServiceFactory.getInstance();
        TestAppService appService = service.getTestAppService();

        try {
            resultId = appService.logging(login,password);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        UserLoggingResponse response = new UserLoggingResponse();
        response.setUserId(resultId);
        response.setErrorStatus(false);
        if (resultId == 0){
            response.setResultMessage("Incorrect data! Check you login or password.");
        }
        else response.setResultMessage("You entered login - " + login + " !");
        return response;
    }
}
