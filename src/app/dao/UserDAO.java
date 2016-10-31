package app.dao;


import app.dao.exception.DAOException;

public interface UserDAO {
	int logging(String fullName, String password) throws DAOException;
	boolean registration(String fullName, String password) throws DAOException;
}
