package it.unipv.ingsw.k20.model.element;

import java.util.List;

import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.team.Team;

public interface IElement {

	public void initTournamentElement(int maxDays) throws OddTeamsSizeException;
	
	public void endTournamentElement();
	
	public List<Team> getRanking();
	
}
