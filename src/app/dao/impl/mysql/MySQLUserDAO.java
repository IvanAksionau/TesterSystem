package app.dao.impl.mysql;


import app.dao.UserDAO;
import app.dao.exception.DAOException;
import app.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUserDAO implements UserDAO {
    @Override
    public int logging(String userName, String password) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        String checkLoginQuery = "select * from users where (user_name, password)=('" + userName + "', '" + password + "');";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(checkLoginQuery);
            if (result.next()) {
                return result.getInt("user_id");
            } else {
                return 0;
            }
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex);
                }
            }
        }

    }

    @Override
    public boolean registration(String fullName, String password) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        String checkLoginQuery = "select * from users where user_name = '" + fullName + "';";
        String registrationQuery = "insert into users (user_name, password) values ('" + fullName + "', '" + password + "');";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(checkLoginQuery);
            if (!result.next()) {
                statement.executeUpdate(registrationQuery);
                return true;
            } else {
                return false;
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

}
