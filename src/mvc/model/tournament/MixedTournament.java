package mvc.model.tournament;

import java.util.List;

import mvc.model.element.Board;
import mvc.model.element.Day;
import mvc.model.element.Group;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Team;

public class MixedTournament extends Tournament {

	private TournamentElement group,board;

	public MixedTournament(String name) {
		super(name);
	}

	public TournamentElement getBoard() {
		return this.board;
	}

	@Override
	public void initTournament(List<Team> teamsList) {
		this.group= new Group("Group");
		addTeams(teamsList);
		this.group.initTournamentElement();	
	}

	
	private boolean isGroupCompleted() {
		return group.isCompleted();
	}

	@Override
	public boolean addTeamInTournament(Team team) {
		return group.addTeam(team);
	}
	
	public void initKnockoutPhase() {
		if (this.isGroupCompleted()) {
			this.board= new Board("Board");
			this.board.initTournamentElement();
		}
	}

	@Override
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore) {
//		if (isEachGroupCompleted())
//			return this.board.insertScore(dayNumber, match, homeScore, awayScore);
//		else 
//			for (TournamentElement te : this.groupsList)
//				if (te.insertScore(dayNumber, match, homeScore, awayScore))
//					return true;
//		
//		return false;
		return isGroupCompleted()?board.insertScore(dayNumber, match, homeScore, awayScore):group.insertScore(dayNumber, match, homeScore, awayScore);
	}

	@Override
	public List<Day> getSchedule() {
//		List<Day> schedule = new ArrayList<>();
//		
//		if (isEachGroupCompleted())
//			schedule = this.board.getSchedule();
//		else 
//			for (TournamentElement te : this.groupsList)
//				schedule.addAll(te.getSchedule());
//		
//		return schedule;	
		return isGroupCompleted()?board.getSchedule():group.getSchedule();
	}
	
	@Override
	public TournamentType getTournamentType() {
		return TournamentType.MIXED;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Tournament type: %s\n%s\n%s\n", this.getTournamentType(), this.group.toString(), this.board.toString());
	}

	@Override
	public TournamentElement getTournamentElement() {
		return group;
	}
}
