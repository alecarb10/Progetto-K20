package mvc.model.tournament;

import java.util.List;

import mvc.model.element.Day;
import mvc.model.element.Group;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Team;

public class League extends Tournament {
	private TournamentElement group; // girone unico

	public League(String name, List<Team> teamsList) {
		super(name, teamsList);
		initTournament(teamsList);
	}

	public TournamentElement getGroup() {
		return this.group;
	}

	@Override
	public TournamentType getTournamentType() {
		return TournamentType.LEAGUE;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Tournament type: %s\n%s", this.getTournamentType(), this.group.toString());
	}

	@Override
	public void initTournament(List<Team> teamsList) {
		this.group = new Group();
		addTeams(teamsList);
		this.group.initTournamentElement();
	}

	@Override
	public boolean addTeamInTournament(Team team) {
		return this.group.addTeam(team);
	}

	@Override
	public boolean removeTeamFromTournament(Team team) {
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
}
