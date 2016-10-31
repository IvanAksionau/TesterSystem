package app.dao;

import app.bean.entity.Question;
import app.bean.entity.StudentTest;
import app.dao.exception.DAOException;

import java.util.ArrayList;

public interface TestDAO {

    void addNewTest(String testName, String subjectType, ArrayList<Question> questions) throws DAOException;

    ArrayList<StudentTest> showAllTests() throws DAOException;

    StudentTest passTest(int testId) throws DAOException;

}
