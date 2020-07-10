package mvc.model.tournament;

import java.util.List;

import mvc.model.element.Day;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Team;

public interface ITournament {

	public TournamentType getTournamentType();

	public void initGroup(List<Team> teamsList);
	
	public void initBoard(List<Team> teamsList);

	public boolean addTeamInTournament(Team t);
	
	public List<Team> getTeamsList();
	
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore);
	
	public List<Day> getGroupSchedule();
	
	public void setGroupSchedule(List<Day> schedule);
	
	public List<Day> getBoardSchedule();
	
	public void setBoardSchedule(List<Day> schedule);
	
	public TournamentElement getGroup();
	
	public TournamentElement getBoard();
}
