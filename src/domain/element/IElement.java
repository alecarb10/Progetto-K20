package domain.element;

import domain.match.Match;
import domain.team.Team;

/**
 * Used to define common features of tournament elements: group or boards.
 */
public interface IElement {
	
	/**
	 * @return ElementType
	 * 
	 * @see {@link ElementType}
	 */
	public ElementType getTournamentElementType();

	/**
	 * Generates the schedule.
	 * 
	 */
	public void initTournamentElement();
	
	public boolean addTeam(Team team);
	
	public boolean removeTeam(Team team);
	
	public boolean isCompleted();
	
	/**
	 * Method to insert the final result of a match played in a specific day.
	 * @param dayNumber
	 * @param match
	 * @param homeScore
	 * @param awayScore
	 * 
	 */
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore);
	
	public Day getDayByNumber(int number);
	
}
