package mvc.model.element;

import java.util.List;

import mvc.model.team.Team;

public interface IElement {

	public void initTournamentElement();
	
	public void endTournamentElement();
	
	public List<Team> getRanking();
	
}
