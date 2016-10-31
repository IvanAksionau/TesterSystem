package app.command.impl;


import app.bean.Request;
import app.bean.Response;
import app.bean.UserRegistrationRequest;
import app.bean.UserRegistrationResponse;
import app.command.Command;
import app.command.exception.CommandException;
import app.service.TestAppService;
import app.service.ServiceFactory;
import app.service.exception.ServiceException;

/**
 * Created by Ivan on 12.10.2016.
 */
public class UserRegistration implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        UserRegistrationRequest req = null;
        if (request instanceof UserRegistrationRequest) {
            req = (UserRegistrationRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String login = req.getLogin();
        String password = req.getPassword();
        boolean resultId;
        ServiceFactory service = ServiceFactory.getInstance();
        TestAppService nbService = service.getTestAppService();

        try {
            resultId = nbService.registration(login, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setRegistrationStatus(resultId);
        response.setErrorStatus(false);
        if (resultId == true) {
            response.setResultMessage("Hello " + login + " ! " + "You was registered !");
        } else response.setResultMessage("User with name " + login + " already exist !");
        return response;
    }
}
