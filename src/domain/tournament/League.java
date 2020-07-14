package domain.tournament;

import java.util.List;

import domain.element.Day;
import domain.element.Group;
import domain.element.TournamentElement;
import domain.match.Match;
import domain.team.Team;

/**
 * League Tournament class.
 * 
 */
public class League extends Tournament {
	private TournamentElement group; 

	/**
	 * League Tournament constructor.
	 * @param name
	 * 
	 */
	public League(String name) {
		super(name);
		this.group = new Group();
	}
	
	@Override
	public void initGroup(List<Team> teamsList) {
		addTeams(teamsList);
		this.group.initTournamentElement();
	}
	
	@Override
	public void initBoard(List<Team> teamsList) {
		
	}

	@Override
	public boolean addTeamInTournament(Team team) {
		return this.group.addTeam(team);
	}

	@Override
	public List<Team> getTeamsList() {
		return this.group.getTeamsList();
	}

	@Override
	public boolean insertScore(int numberDay, Match match, int homeScore, int awayScore) {
		return this.group.insertScore(numberDay, match, homeScore, awayScore);
	}

	@Override
	public TournamentElement getGroup() {
		return group;
	}
	
	@Override
	public TournamentElement getBoard() {
		return null;
	}
	
	@Override
	public List<Day> getGroupSchedule() {
		return this.group.getSchedule();
	}
	
	@Override
	public void setGroupSchedule(List<Day> schedule) {
		this.group.setSchedule(schedule);
	}
	
	@Override
	public List<Day> getBoardSchedule() {
		return null;
	}

	@Override
	public void setBoardSchedule(List<Day> schedule) {
		
	}
	
	@Override
	public TournamentType getTournamentType() {
		return TournamentType.LEAGUE;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Tournament type: %s\n%s", this.getTournamentType(), this.group.toString());
	}
}
