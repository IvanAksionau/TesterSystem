package command.impl;


import bean.AddNewTestRequest;
import bean.Request;
import bean.Response;
import bean.entity.Question;
import command.Command;
import command.exception.CommandException;
import service.TestAppService;
import service.ServiceFactory;
import service.exception.ServiceException;

import java.util.ArrayList;

public class AddNewTest implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        AddNewTestRequest req = null;
        if (request instanceof AddNewTestRequest) {
            req = (AddNewTestRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String testName = req.getTestName();
        String subjectType = req.getSubjectType();
        ArrayList<Question> questions = req.getQuestions();
        ServiceFactory service = ServiceFactory.getInstance();
        TestAppService appService = service.getTestAppService();

        try {
            appService.addNewTest(testName, subjectType, questions);
        } catch (ServiceException e) {
            throw new CommandException();
        }

        Response response = new Response();
        response.setErrorStatus(false);
        response.setResultMessage("Test " + testName + " added !");
        return response;
    }
}
