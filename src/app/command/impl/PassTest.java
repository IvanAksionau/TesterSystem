package app.command.impl;

import app.bean.PassTestRequest;
import app.bean.PassTestResponse;
import app.bean.Request;
import app.bean.Response;
import app.bean.entity.StudentTest;
import app.command.Command;
import app.command.exception.CommandException;
import app.service.ServiceFactory;
import app.service.TestAppService;
import app.service.exception.ServiceException;

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
        StudentTest test;
        ServiceFactory service = ServiceFactory.getInstance();
        TestAppService appService = service.getTestAppService();

        try {
            test = appService.passTest(testId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        PassTestResponse response = new PassTestResponse();
        response.setTest(test);
        response.setErrorStatus(false);
        if (test.getTestName() == null) {
            response.setResultMessage("StudentTest with ID - " + testId + " don't exist !");
        } else response.setResultMessage("Now you will pass test - " + test.getTestName() + " !");
        return response;
    }
}
