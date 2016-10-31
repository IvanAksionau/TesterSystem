package app.service;

import app.bean.entity.Question;
import app.bean.entity.StudentTest;
import app.service.exception.ServiceException;

import java.util.ArrayList;

public interface TestAppService {

    void addNewTest(String testName, String subjectType, ArrayList<Question> questions) throws ServiceException;

    StudentTest passTest(int testId) throws ServiceException;

    ArrayList<StudentTest> showAllTests() throws ServiceException;

    int logging(String login, String password) throws ServiceException;

    boolean registration(String login, String password) throws ServiceException;
}
