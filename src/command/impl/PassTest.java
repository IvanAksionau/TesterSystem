package command.impl;

import bean.PassTestRequest;
import bean.PassTestResponse;
import bean.Request;
import bean.Response;
import bean.entity.Test;
import command.Command;
import command.exception.CommandException;
import service.ServiceFactory;
import service.TestAppService;
import service.exception.ServiceException;

/**
 * Created by Ivan on 26.10.2016.
 */
public class PassTest implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        PassTestRequest req = null;
        if (request instanceof PassTestRequest) {
            req = (PassTestRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        int testId = req.getTestId();
        Test test;
        ServiceFactory service = ServiceFactory.getInstance();
        TestAppService appService = service.getTestAppService();

        try {
            test = appService.passTest(testId);
        } catch (ServiceException e) {
            throw new CommandException();
        }

        PassTestResponse response = new PassTestResponse();
        response.setTest(test);
        response.setErrorStatus(false);
        if (test.getTestName() == null) {
            response.setResultMessage("Test with ID - " + testId + " don't exist !");
        } else response.setResultMessage("Now you will pass test - " + test.getTestName() + " !");
        return response;
    }
}
