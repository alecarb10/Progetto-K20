package domain.tournament;

import java.util.List;

import domain.element.Board;
import domain.element.Day;
import domain.element.Group;
import domain.element.TournamentElement;
import domain.match.Match;
import domain.team.Team;

/**
 * Mixed Tournament class.
 * 
 */
public class MixedTournament extends Tournament {

	private TournamentElement group, board;

	/**
	 * Mixed Tournament constructor.
	 * @param name
	 * 
	 */
	public MixedTournament(String name) {
		super(name);
		this.group= new Group();
		this.board= new Board();
	}

	@Override
	public void initGroup(List<Team> teamsList) {
		addTeams(teamsList);
		this.group.initTournamentElement();	
	}
	
	@Override
	public void initBoard(List<Team> teamsList) {
		if (this.isGroupCompleted()) {
			// List<Team> teamsList = getTeamsList();
			for (int i = 0; i < teamsList.size() / 2; i++)
				addTeamInBoard(teamsList.get(i));
			this.board.initTournamentElement();
		}
	}
	
	private boolean addTeamInBoard(Team team) {
			return this.board.addTeam(team);
	}
	
	@Override
	public boolean addTeamInTournament(Team team) {
		return this.group.addTeam(team);
	}
	
	@Override
	public List<Team> getTeamsList() {
		return this.group.getTeamsList();
	}

	private boolean isGroupCompleted() {
		return this.group.isCompleted();
	}

	@Override
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore) {
		return isGroupCompleted() ? board.insertScore(dayNumber, match, homeScore, awayScore) : group.insertScore(dayNumber, match, homeScore, awayScore);
	}
	
	@Override
	public TournamentElement getGroup() {
		return this.group;
	}
	
	@Override
	public TournamentElement getBoard() {
		return this.board;
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
	public void setBoardSchedule(List<Day> schedule) {
		this.board.setSchedule(schedule);
	}
	
	@Override
	public List<Day> getBoardSchedule(){
		return this.board.getSchedule();
	}
	
	@Override
	public TournamentType getTournamentType() {
		return TournamentType.MIXED;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Tournament type: %s\n%s\n%s\n", this.getTournamentType(), this.group.toString(), this.board.toString());
	}
}
