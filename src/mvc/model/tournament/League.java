package mvc.model.tournament;

import java.util.List;

import mvc.model.element.Day;
import mvc.model.element.Group;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Team;

public class League extends Tournament {
	private TournamentElement group; 

	public League(String name) {
		super(name);
		this.group = new Group();
	}
	
	@Override
	public void initTournament(List<Team> teamsList) {
		addTeams(teamsList);
		this.group.initTournamentElement();
	}

	@Override
	public boolean addTeamInTournament(Team team) {
		return this.group.addTeam(team);
	}

	@Override
	public boolean insertScore(int numberDay, Match match, int homeScore, int awayScore) {
		return this.group.insertScore(numberDay, match, homeScore, awayScore);
	}

	@Override
	public List<Day> getSchedule() {
		return this.group.getSchedule();
	}
	
	@Override
	public void setSchedule(List<Day> schedule) {
		this.group.setSchedule(schedule);
	}
	
	@Override
	public TournamentType getTournamentType() {
		return TournamentType.LEAGUE;
	}
	
	@Override
	public TournamentElement getTournamentElement() {
		return group;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Tournament type: %s\n%s", this.getTournamentType(), this.group.toString());
	}
}
