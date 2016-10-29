package command.impl;


import bean.Request;
import bean.Response;
import bean.UserRegistrationRequest;
import bean.UserRegistrationResponse;
import command.Command;
import command.exception.CommandException;
import service.TestAppService;
import service.ServiceFactory;
import service.exception.ServiceException;

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
            throw new CommandException(e.getMessage());
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
