package dao.impl.mysql.getter;

import dao.exception.DAOException;
import dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ivan on 27.10.2016.
 */
public class AnswerVariant {

    public static String[] getAnswerVariants(int questionID) throws DAOException {
        String[] variants = new String[3];
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String questionVariantsQuery = "select * from variants where question_id=" + questionID + ";";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(questionVariantsQuery);
            int variantsIndex = 0 ;
            while (resultSet.next()) {
                String variant = resultSet.getString("variant");
                variants[variantsIndex] = variant;
                variantsIndex++;
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
        return variants;
    }
}
