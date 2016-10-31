package app.command.impl;


import app.bean.AddNewTestRequest;
import app.bean.Request;
import app.bean.Response;
import app.bean.entity.Question;
import app.command.Command;
import app.command.exception.CommandException;
import app.service.TestAppService;
import app.service.ServiceFactory;
import app.service.exception.ServiceException;

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
            throw new CommandException(e);
        }

        Response response = new Response();
        response.setErrorStatus(false);
        response.setResultMessage("StudentTest " + testName + " added !");
        return response;
    }
}
