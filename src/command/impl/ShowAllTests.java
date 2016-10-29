package command.impl;


import bean.Request;
import bean.Response;
import bean.ShowAllTestRequest;
import bean.ShowAllTestResponse;
import bean.entity.Test;
import command.Command;
import command.exception.CommandException;
import service.ServiceFactory;
import service.TestAppService;
import service.exception.ServiceException;

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
        ArrayList<Test> tests;

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
