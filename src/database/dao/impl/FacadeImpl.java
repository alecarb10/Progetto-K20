package database.dao.impl;

import java.sql.SQLException;
import java.util.List;

import database.dao.*;
import mvc.model.team.Player;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;

public class FacadeImpl implements IFacade {

	@Override
	public boolean storeManager(String username, String name, String surname, String password) throws SQLException {
		IManagerDAO m = new ManagerDAOImpl();
		return m.storeManager(username, name, surname, password);
	}

	@Override
	public boolean updateManager(String username, String name, String surname, String password) throws SQLException {
		IManagerDAO m = new ManagerDAOImpl();
		return m.updateManager(username, name, surname, password);
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

	@Override
	public boolean storeTournament(Tournament t, String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.storeTournament(t, username);
	}
	
	@Override
	public List<Tournament> getAllTournaments() throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getAllTournaments();
	}
	
	@Override
	public List<Tournament> getAllTournamentsByManager(String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getAllTournamentsByManager(username);
	}

	@Override
	public boolean storeTeam(Team t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storePlayer(Player p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeStadium(Stadium s) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeTeam(Team t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePlayer(Player p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeStadium(Stadium s) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
