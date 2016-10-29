package dao;

import bean.entity.Question;
import bean.entity.Test;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface TestDAO {

    void addNewTest(String testName, String subjectType, ArrayList<Question> questions) throws DAOException;

    ArrayList<Test> showAllTests() throws DAOException;

    Test passTest(int testId) throws DAOException;

}
