package service;

import bean.entity.Question;
import bean.entity.Test;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface TestAppService {

    void addNewTest(String testName, String subjectType, ArrayList<Question> questions) throws ServiceException;

    Test passTest(int testId) throws ServiceException;

    ArrayList<Test> showAllTests() throws ServiceException;

    int logging(String login, String password) throws ServiceException;

    boolean registration(String login, String password) throws ServiceException;
}
