package dao;

import dao.impl.mysql.MySQLTestDAO;

public class TestDAOFactory {

    private static final TestDAOFactory instance = new TestDAOFactory();
    private TestDAO testDAO = new MySQLTestDAO();

    public static TestDAOFactory getInstance() {
        return instance;
    }

    public TestDAO getTestDAO() {
        return testDAO;
    }

}
