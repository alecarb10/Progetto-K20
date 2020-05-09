package database.dao.impl;

import java.sql.SQLException;

import database.dao.*;

public class FacadeImpl implements IFacade {

	@Override
	public boolean storeManager(String username, String name, String surname, String password) throws SQLException {
		IManagerDAO m = new ManagerDAOImpl();
		return m.storeManager(username, name, surname, password);
	}

	@Override
	public boolean removeManager(String username) throws SQLException {
		IManagerDAO m = new ManagerDAOImpl();
		return m.removeManager(username);
	}

	@Override
	public boolean checkManagerLogin(String username, String password) throws SQLException {
		IManagerDAO m = new ManagerDAOImpl();
		return m.checkManagerLogin(username, password);
	}

	@Override
	public boolean checkUnique(String username) throws SQLException {
		IManagerDAO m = new ManagerDAOImpl();
		return m.checkUnique(username);
	}

}
