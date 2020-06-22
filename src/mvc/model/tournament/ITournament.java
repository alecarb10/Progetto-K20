package mvc.model.tournament;

import java.util.List;

import mvc.model.element.Day;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Team;

public interface ITournament {

	public TournamentType getTournamentType();

	public void initTournament(List<Team> teamsList);

	public boolean addTeamInTournament(Team t);
	
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore);
	
	public List<Day> getSchedule();
	
	public void setSchedule(List<Day> schedule);
	
	public TournamentElement getTournamentElement();
}
