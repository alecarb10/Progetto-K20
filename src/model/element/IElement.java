package model.element;

import model.match.Match;
import model.team.Team;

public interface IElement {
	
	public ElementType getTournamentElementType();

	public void initTournamentElement();
	
	public boolean addTeam(Team team);
	
	public boolean removeTeam(Team team);
	
	public boolean isCompleted();
	
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore);
	
	public Day getDayByNumber(int number);
	
}