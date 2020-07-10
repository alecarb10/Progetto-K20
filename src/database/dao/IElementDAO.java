package database.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.model.element.Day;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.tournament.Tournament;

public interface IElementDAO {
	
	public boolean storeGroup(TournamentElement t) throws SQLException;
	
	public boolean storeBoard(TournamentElement t) throws SQLException;
	
	public int getBoardIDByTournament(Tournament t) throws SQLException;
	
	public int getLastElementID(TournamentElement t) throws SQLException;
	
	public boolean storeSchedule(List<Day> schedule, Tournament t) throws SQLException;
	
	public boolean storeDay(Day d, Tournament t) throws SQLException;
	
	public List<Day> getGroupSchedule(Tournament t) throws SQLException;
	
	public List<Day> getBoardSchedule(Tournament t) throws SQLException;
	
	public boolean updateMatch(Match match) throws SQLException;

}
