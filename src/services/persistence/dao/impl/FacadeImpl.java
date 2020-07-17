package services.persistence.dao.impl;

import java.sql.SQLException;

import java.util.List;

import domain.element.Day;
import domain.element.TournamentElement;
import domain.match.Match;
import domain.team.Player;
import domain.team.Stadium;
import domain.team.Team;
import domain.tournament.Tournament;

import services.persistence.dao.*;

/**
 * Singleton class.
 * It groups all DAO's methods, to have only one access point to the persistence level
 * @see IFacade
 * @see ManagerDAOImpl
 * @see TeamDAOImpl
 * @see TournamentDAOImpl
 * @see ElementDAOImpl
 */

public class FacadeImpl implements IFacade {

	private static FacadeImpl facade;

	private FacadeImpl() {
	}

	public static synchronized FacadeImpl getInstance() {
		if (facade == null)
			facade = new FacadeImpl();
		return facade;
	}
	
	// ----------------------------------- Manager methods ------------------------------
	
	@Override
	public boolean storeManager(String username, String name, String surname, String password) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.storeManager(username, name, surname, password);
	}

	@Override
	public boolean updateManager(String username, String name, String surname) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.updateManager(username, name, surname);
	}
	
	@Override
	public boolean removeManager(String username) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.removeManager(username);
	}

	@Override
	public boolean checkManagerLogin(String username, String password) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.checkManagerLogin(username, password);
	}

	@Override
	public boolean checkUnique(String username) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.checkUnique(username);
	}
	
	@Override
	public List<String> getManagerByUsername(String username) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.getManagerByUsername(username);
	}
	
	// ----------------------------------- Tournament methods ------------------------------

	@Override
	public boolean storeTournament(Tournament t, String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.storeTournament(t, username);
	}
	
	@Override
	public boolean checkUnique(String name, String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.checkUnique(name, username);
	}
	
	@Override
	public int getLastTournamentID() throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getLastTournamentID();
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

	// ----------------------------------- Team methods ------------------------------
	
	@Override
	public boolean storeTeam(Team team, Tournament t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storeTeam(team, t);
	}
	
	@Override
	public boolean updateTeam(Team t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.updateTeam(t);
	}
	
	@Override
	public boolean updateTeam(Tournament t,Team team) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.updateTeam(t,team);
	}

	@Override
	public boolean storePlayer(Player p, Team t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storePlayer(p, t);
	}
	
	@Override
	public int getLastTeamID() throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getLastTeamID();
	}

	@Override
	public int getLastPlayerID() throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getLastPlayerID();
	}

	@Override
	public boolean storeStadium(Stadium s) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storeStadium(s);
	}
	
	@Override
	public boolean checkUniqueStadium(Stadium s) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.checkUniqueStadium(s);
	}
	
	@Override
	public boolean updateStadium(Stadium s) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.updateStadium(s);
	}

	@Override
	public boolean updatePlayer(Player p) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.updatePlayer(p);
	}
	
	@Override
	public boolean removePlayer(Player p) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.removePlayer(p);
	}

	@Override
	public List<Team> getTeamsByTournament(Tournament t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getTeamsByTournament(t);
	}
	
	@Override
	public List<Stadium> getStadiums() throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getStadiums();
	}
	
	// ----------------------------------- Element methods ------------------------------
	
	@Override
	public boolean storeGroup(Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeGroup(t);
	}
	
	@Override
	public boolean storeBoard(Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeBoard(t);
	}
	
	@Override
	public int getBoardIDByTournament(Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.getBoardIDByTournament(t);
	}
	
	@Override
	public int getLastElementID(TournamentElement t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.getLastElementID(t);
	}

	@Override
	public boolean storeSchedule(List<Day> schedule, Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeSchedule(schedule, t);
	}
	
	@Override
	public boolean storeDay(Day d, Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeDay(d, t);
	}

	@Override
	public List<Day> getGroupSchedule(Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.getGroupSchedule(t);
	}
	
	@Override
	public List<Day> getBoardSchedule(Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.getBoardSchedule(t);
	}

	@Override
	public boolean updateMatch(Match match) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.updateMatch(match);
	}
}
