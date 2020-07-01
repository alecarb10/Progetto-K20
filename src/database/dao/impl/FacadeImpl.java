package database.dao.impl;

import java.sql.SQLException;

import java.util.List;

import database.dao.*;
import mvc.model.element.Day;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Player;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;

public class FacadeImpl implements IFacade {

	private static FacadeImpl facade;

	private FacadeImpl() {
	}

	public static synchronized FacadeImpl getInstance() {
		if (facade == null)
			facade = new FacadeImpl();
		return facade;
	}
	
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
	
	@Override
	public boolean storeElement(Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeElement(t);
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
	public List<Day> getSchedule(Tournament t, boolean wantsBoard) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.getSchedule(t, wantsBoard);
	}

	@Override
	public boolean updateMatch(Match oldMatch, Match newMatch) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.updateMatch(oldMatch, newMatch);
	}
}
