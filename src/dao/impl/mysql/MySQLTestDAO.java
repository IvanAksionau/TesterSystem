package dao.impl.mysql;


import bean.entity.Question;
import bean.entity.Test;

import dao.TestDAO;
import dao.exception.DAOException;
import dao.impl.mysql.getter.AnswerVariant;
import dao.impl.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;


public class MySQLTestDAO implements TestDAO {

    @Override
    public void addNewTest(String testName, String subjectType, ArrayList<Question> questions) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        Question currentQuestion;
        String TestInfoQuery = "insert into test_info (test_name, subject) values('"
                + testName + "', '" + subjectType + "');";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            //заполняем таблицу test_info
            statement.executeUpdate(TestInfoQuery);
            //пока в листе есть Question выполняем добавление в test_data
            for (int i = 0; i < questions.size(); i++) {
                currentQuestion = questions.get(i);
                statement.executeUpdate("insert into test_data values ("
                        + "(select test_id from test_info where test_name = '" + testName + "'), DEFAULT, "
                        + "'" + currentQuestion.getDescription() + "', '"
                        + currentQuestion.getCorrectAnswer() + "');");
            }

            for (int i = 0; i < questions.size(); i++) {
                currentQuestion = questions.get(i);
                //берём question() из списка "questions" и достаём из него список variants
                String[] variants = questions.get(i).getVariants();
                //добавляем в базу варианты ответа,связывая их с соотв. вопросом по question_id
                for (int j = 0; j < variants.length; j++) {
                    String variant = variants[j];
                    statement.executeUpdate("insert into variants values ("
                            + "(select question_id from test_data where correct_answer = '"
                            + currentQuestion.getCorrectAnswer() + "'), '" + variant + "');");
                }
            }
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }

    }

    @Override
    public Test passTest(int testId) throws DAOException {
        Test test = new Test();
        ArrayList<Question> questions = new ArrayList<>();
        Question question;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        String testInfoQuery = "select * from test_info where test_id=" + testId + ";";
        String testDataQuery = "select * from test_data where test_id=" + testId + ";";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            //получаем имя теста и предметную область
            resultSet = statement.executeQuery(testInfoQuery);
            //если теста нет в базе возвращаем тест с пустыми полями
            if (resultSet.isLast()) {
                return test;
            }
            while (resultSet.next()) {
                test.setTestName(resultSet.getString("test_name"));
                test.setSubject(resultSet.getString("subject"));
            }
            //получаем все вопросы теста по его уникальному id
            resultSet = statement.executeQuery(testDataQuery);
            while (resultSet.next()) {
                question = new Question();
                question.setDescription(resultSet.getString("question_desc"));
                question.setCorrectAnswer(resultSet.getString("correct_answer"));
                //для каждого вопроса получаем список вариантов ответа
                question.setVariants(AnswerVariant.getAnswerVariants(resultSet.getInt("question_id")));
                questions.add(question);
            }
            test.setQuestions(questions);
            return test;
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public ArrayList<Test> showAllTests() throws DAOException {
        ArrayList<Test> tests = new ArrayList<>();
        Test test;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        String getAllTestQuery = "select * from test_info;";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllTestQuery);
            while (resultSet.next()) {
                test = new Test();
                test.setTestId(resultSet.getInt("test_id"));
                test.setTestName(resultSet.getString("test_name"));
                test.setSubject(resultSet.getString("subject"));
                tests.add(test);
            }

        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }
        return tests;
    }
}
