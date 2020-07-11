package model.tournament;

import java.util.List;

import model.element.Day;
import model.element.TournamentElement;
import model.match.Match;
import model.team.Team;

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
