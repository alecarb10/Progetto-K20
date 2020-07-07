package database.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.model.element.Day;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.tournament.Tournament;

public interface IElementDAO {
	
	public boolean storeElement(Tournament t) throws SQLException;
	
	public int getBoardIDByTournament(Tournament t) throws SQLException;
	
	public int getLastElementID(TournamentElement t) throws SQLException;
	
	public boolean storeSchedule(List<Day> schedule, Tournament t) throws SQLException;
	
	public boolean storeDay(Day d, Tournament t) throws SQLException;
	
	public List<Day> getSchedule(Tournament t, boolean wantsBoard) throws SQLException;
	
	public boolean updateMatch(Match match) throws SQLException;

}
