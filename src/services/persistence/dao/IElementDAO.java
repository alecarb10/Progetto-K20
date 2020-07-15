package services.persistence.dao;

import java.sql.SQLException;

import java.util.List;

import domain.element.Day;
import domain.element.TournamentElement;
import domain.match.Match;
import domain.tournament.Tournament;

import services.persistence.dao.impl.ElementDAOImpl;

/**
 * Interface used for defining methods associated with tournament elements and match, that are implemented in ElementDAOImpl
 * @see ElementDAOImpl
 * @see TournamentElement
 * @see Day
 * @see Match
 * @see Tournament
 */

public interface IElementDAO {
	
	public boolean storeGroup(Tournament t) throws SQLException;
	
	public boolean storeBoard(Tournament t) throws SQLException;
	
	public int getBoardIDByTournament(Tournament t) throws SQLException;
	
	public int getLastElementID(TournamentElement t) throws SQLException;
	
	public boolean storeSchedule(List<Day> schedule, Tournament t) throws SQLException;
	
	public boolean storeDay(Day d, Tournament t) throws SQLException;
	
	public List<Day> getGroupSchedule(Tournament t) throws SQLException;
	
	public List<Day> getBoardSchedule(Tournament t) throws SQLException;
	
	public boolean updateMatch(Match match) throws SQLException;

}
