package app.service.impl;


import app.bean.entity.Question;
import app.bean.entity.StudentTest;
import app.dao.TestDAOFactory;
import app.dao.UserDAOFactory;
import app.dao.exception.DAOException;
import app.service.TestAppService;
import app.service.exception.ServiceException;

import java.util.ArrayList;

public class TestAppServiceImpl implements TestAppService {
    @Override
    public void addNewTest(String testName, String subjectType, ArrayList<Question> questions) throws ServiceException {
        // parameters validation
        if (testName == null || "".equals(testName) || subjectType == null
                || "".equals(subjectType) || questions.size() == 0) {
            throw new ServiceException("Wrong parameter!");
        }
        try {
            TestDAOFactory.getInstance().getTestDAO().addNewTest(testName, subjectType, questions);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public StudentTest passTest(int testId) throws ServiceException {
        if (testId == 0) {
            throw new ServiceException("There no test with id " + testId + " !");
        }
        StudentTest test;
        try {
            test = TestDAOFactory.getInstance().getTestDAO().passTest(testId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return test;
    }


    @Override
    public ArrayList<StudentTest> showAllTests() throws ServiceException {
        ArrayList<StudentTest> result;
        try {
            result = TestDAOFactory.getInstance().getTestDAO().showAllTests();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        if (login == null || "".equals(login) || password == null || "".equals(password)) {
            throw new ServiceException("Wrong parameter!");
        }
        boolean result;
        try {
            result = UserDAOFactory.getInstance().getUserDAO().registration(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return result;
    }

    @Override
    public int logging(String login, String password) throws ServiceException {
        if (login == null || "".equals(login) || password == null || "".equals(password)) {
            throw new ServiceException("Wrong parameter!");
        }
        int result;
        try {
            result = UserDAOFactory.getInstance().getUserDAO().logging(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return result;
    }
}
