package domain.tournament;

import java.util.List;

import domain.element.Day;
import domain.element.TournamentElement;
import domain.exception.IllegalTeamsSizeException;
import domain.exception.SameTeamNameException;
import domain.match.Match;
import domain.team.Team;

/**
 * Used to define common features to all tournament types.
 */

public interface ITournament {

	/**
	 * @return TournamentType
	 * 
	 * @see {@link TournamentType}
	 */
	public TournamentType getTournamentType();

	/**
	 * @param teamsList
	 * 
	 * @see {@link Team}
	 * @see {@link Group}
	 */
	public void initGroup(List<Team> teamsList);
	
	/**
	 * @param teamsList
	 * 
	 * @see {@link Team}
	 * @see {@link Board}
	 */
	public void initBoard(List<Team> teamsList);

	public boolean addTeamInTournament(Team t);
	
	public List<Team> getTeamsList();
	
	/**
	 * Method to insert the final result of a match played in a specific day.
	 * @param dayNumber
	 * @param match
	 * @param homeScore
	 * @param awayScore
	 * 
	 * @see {@link Board}
	 * @see {@link Group}
	 * 
	 */
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore);
	
	public List<Day> getGroupSchedule();
	
	public void setGroupSchedule(List<Day> schedule);
	
	public List<Day> getBoardSchedule();
	
	public void setBoardSchedule(List<Day> schedule);
	
	public TournamentElement getGroup();
	
	public TournamentElement getBoard();
	
	/**
	 * Checks if the tournament's teams list size matches with the wanted size
	 * @param size
	 * @param teamsList
	 * @return true or false
	 * @throws IllegalTeamsSizeException
	 */
	public boolean checkTournamentSize(int size,List<Team> teamsList) throws IllegalTeamsSizeException;
	
	/**
	 * Checks if there are two teams with the same name in the tournament
	 * @param teamsList
	 * @return true or false
	 * @throws SameTeamNameException
	 */
	public boolean checkNamesInTeams(List<Team> teamsList) throws SameTeamNameException;
}
