package app.dao.impl.mysql.getter;

import app.dao.exception.DAOException;
import app.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Ivan on 27.10.2016.
 */
public class AnswerVariant {

    public static ArrayList<String> getAnswerVariants(int questionID) throws DAOException {
        ArrayList<String> variants = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String questionVariantsQuery = "select * from variants where question_id=" + questionID + ";";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(questionVariantsQuery);
            while (resultSet.next()) {
                String variant = resultSet.getString("variant");
                variants.add(variant);
            }

        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new DAOException(ex);
                }
            }
            try {
                ConnectionPool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e);
            }
        }
        return variants;
    }
}
