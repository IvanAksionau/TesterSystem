package app.command.impl;


import app.bean.Request;
import app.bean.Response;
import app.bean.ShowAllTestRequest;
import app.bean.ShowAllTestResponse;
import app.bean.entity.StudentTest;
import app.command.Command;
import app.command.exception.CommandException;
import app.service.ServiceFactory;
import app.service.TestAppService;
import app.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Ivan on 29.09.2016.
 */
public class ShowAllTests implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        ShowAllTestRequest req = null;
        if (request instanceof ShowAllTestRequest) {
            req = (ShowAllTestRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        ServiceFactory service = ServiceFactory.getInstance();
        TestAppService appService = service.getTestAppService();
        ArrayList<StudentTest> tests;

        try {
            tests = appService.showAllTests();
        } catch (ServiceException e) {
            throw new CommandException();
        }

        ShowAllTestResponse response = new ShowAllTestResponse();
        response.setAllTests(tests);
        response.setErrorStatus(false);
        response.setResultMessage("Count of founded tests is: " + tests.size());
        return response;
    }
}
