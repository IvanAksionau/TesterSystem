package dao;


import dao.impl.mysql.MySQLUserDAO;

/**
 * Created by Ivan on 09.10.2016.
 */
public class UserDAOFactory {

    private static final UserDAOFactory instance = new UserDAOFactory();
    private UserDAO userDAO = new MySQLUserDAO();

    public static UserDAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
