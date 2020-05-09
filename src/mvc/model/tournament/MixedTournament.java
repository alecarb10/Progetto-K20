package it.unipv.ingsw.k20.model.tournament;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.k20.model.element.Board;
import it.unipv.ingsw.k20.model.element.Day;
import it.unipv.ingsw.k20.model.element.Group;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.match.Match;
import it.unipv.ingsw.k20.model.team.Team;

public class MixedTournament extends Tournament {

	private List<TournamentElement> groupsList;
	private TournamentElement board;

	public MixedTournament(String name, List<Team> teamsList) {
		super(name, teamsList);
		this.groupsList = new ArrayList<>();
		initTournament(teamsList);
	}

	public TournamentElement getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		if (board != null)
			this.board = board;
	}

	public List<TournamentElement> getGroupsList() {
		return this.groupsList;
	}

	public boolean addGroup(Group group) {
		return this.groupsList.add(group);
	}

	@Override
	public TournamentType getTournamentType() {
		return TournamentType.MIXED;
	}

	@Override
	public String toString() {
		char groupLetter = 65; // A
		StringBuilder sb = new StringBuilder().append("Gironi\n");
		for (TournamentElement te : this.groupsList) {
			sb.append("Group ").append(groupLetter).append("\n").append(te).append("\n");
			groupLetter++;
		}
		return super.toString() + String.format("Tournament type: %s\n%s\n%s\n", this.getTournamentType(), sb.toString(), this.board.toString());
	}

	@Override
	public void initTournament(List<Team> teamsList) {
		addTeams(teamsList);
		for (TournamentElement te : this.groupsList)
			te.initTournamentElement();		
	}

	/**
	 * Il metodo verifica se tutti i gironi hanno terminato.
	 */
	private boolean isEachGroupCompleted() {
		int n = 0;
		for (TournamentElement te : this.groupsList)
			if (te.isCompleted())
				n++;
		return n == this.groupsList.size() ? true : false;
	}

	@Override
	public boolean addTeamInTournament(Team team) {
		for (TournamentElement te : this.groupsList)
			if (te.getTeamsList().size() < 4)
				return te.addTeam(team);
		return false; // torneo al completo
	}

	@Override
	public boolean removeTeamFromTournament(Team team) {
		for (TournamentElement te : this.groupsList)
			return te.removeTeam(team);
		return false;
	}

	/**
	 * Il metodo viene invocato quando la fase a gironi è terminata.
	 */
	public void initKnockoutPhase() {
		if (this.isEachGroupCompleted())
			this.board.initTournamentElement();
	}

	@Override
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore) {
		if (isEachGroupCompleted())
			return this.board.insertScore(dayNumber, match, homeScore, awayScore);
		else 
			for (TournamentElement te : this.groupsList)
				if (te.insertScore(dayNumber, match, homeScore, awayScore))
					return true;
		
		return false;
	}

	@Override
	public List<Day> getSchedule() {
		List<Day> schedule = new ArrayList<>();
		
		if (isEachGroupCompleted())
			schedule = this.board.getSchedule();
		else 
			for (TournamentElement te : this.groupsList)
				schedule.addAll(te.getSchedule());
		
		return schedule;	
	}
}
