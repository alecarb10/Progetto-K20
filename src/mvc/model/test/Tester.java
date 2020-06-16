package mvc.model.test;

import java.util.ArrayList;
import java.util.List;

import mvc.model.element.Board;
import mvc.model.element.Day;
import mvc.model.team.Player;
import mvc.model.team.PlayerPositionType;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.KnockoutPhase;
import mvc.model.tournament.League;
import mvc.model.tournament.MixedTournament;
import mvc.model.tournament.Tournament;

public class Tester {
	public static void main(String[] args) {
		
		//Teams
		List<Team> teamsList= new ArrayList<>();
		teamsList.add(new Team("Milan"));
		teamsList.add(new Team("Inter"));
		teamsList.add(new Team("Juventus"));
		teamsList.add(new Team("Roma"));
		teamsList.add(new Team("Barcelona"));
		teamsList.add(new Team("Real Madrid"));
		teamsList.add(new Team("Juventus B"));
		teamsList.add(new Team("Lazio"));
		
		//LEAGUE
//		Tournament league= new League("Prova League");
//		league.initTournament(teamsList);
//		for(Day day:league.getSchedule())
//			System.out.println(day.toString());
//		teamsList.get(0).setStadium(new Stadium("Giuseppe Meazza", "Milano", 80000));
//		for(Team team:league.getTournamentElement().getTeamsList()) 
//			System.out.println(team.toString());
//		teamsList.get(1).insertPlayer(new Player("Zlatan", "Ibrahimovich", 21, PlayerPositionType.CF));
//		Player dybalaPlayer=new Player("Paulo", "Dybala", 10, PlayerPositionType.CF);
//		teamsList.get(2).insertPlayer(dybalaPlayer);
//		//teamsList.get(2).removePlayer(dybalaPlayer);
//		for(Team team:league.getTournamentElement().getTeamsList())
//			for(Player player:team.getPlayers())
//				System.out.println(player.toString());
//		league.insertScore(1,league.getSchedule().get(0).getMatchesList().get(0),1 , 0);
//		league.insertScore(1,league.getSchedule().get(0).getMatchesList().get(1),1 , 1);
//		league.insertScore(2,league.getSchedule().get(1).getMatchesList().get(0),3 , 1);
//		league.insertScore(3,league.getSchedule().get(2).getMatchesList().get(0),1 , 0);
//		System.out.println(league.getTournamentElement().toString());
//		for(Day day:league.getSchedule())
//			System.out.println(day.toString());
//		System.out.println(league.getTournamentElement().toString());
//		
//		//KnockoutPhase
//		KnockoutPhase kno= new KnockoutPhase("Prova KnockoutPhase");
//		kno.initTournament(teamsList);
//		for(Day day:kno.getSchedule())
//			System.out.println(day.toString());
//		kno.insertScore(1, kno.getSchedule().get(0).getMatchesList().get(0), 1, 2);
//		kno.insertScore(1, kno.getSchedule().get(0).getMatchesList().get(1), 1, 2);
//		for(Day day:kno.getSchedule())
//			System.out.println(day.toString());
//		System.out.println("----------");
//		((Board)kno.getTournamentElement()).addNextDay();
//		for(Day day:kno.getSchedule())
//			System.out.println(day.toString());
//		kno.insertScore(2, kno.getSchedule().get(1).getMatchesList().get(0), 1, 2);
//		for(Day day:kno.getSchedule())
//			System.out.println(day.toString());
//		System.out.println("----------");
//		((Board)kno.getTournamentElement()).addNextDay();
//		for(Day day:kno.getSchedule())
//			System.out.println(day.toString());	
		
		//Mixed
		MixedTournament mixedTournament= new MixedTournament("Prova Mixed");
		mixedTournament.initTournament(teamsList);
		for(Day day:mixedTournament.getSchedule())
			System.out.println(day.toString());
		mixedTournament.insertScore(1, mixedTournament.getSchedule().get(0).getMatchesList().get(0), 1, 1);
		mixedTournament.insertScore(1, mixedTournament.getSchedule().get(0).getMatchesList().get(1), 2, 1);
		mixedTournament.insertScore(1, mixedTournament.getSchedule().get(0).getMatchesList().get(2), 3, 1);
		mixedTournament.insertScore(1, mixedTournament.getSchedule().get(0).getMatchesList().get(3), 2, 3);
		mixedTournament.insertScore(2, mixedTournament.getSchedule().get(1).getMatchesList().get(0), 2, 4);
		mixedTournament.insertScore(2, mixedTournament.getSchedule().get(1).getMatchesList().get(1), 3, 1);
		mixedTournament.insertScore(2, mixedTournament.getSchedule().get(1).getMatchesList().get(2), 2, 4);
		mixedTournament.insertScore(2, mixedTournament.getSchedule().get(1).getMatchesList().get(3), 3, 1);
		mixedTournament.insertScore(3, mixedTournament.getSchedule().get(2).getMatchesList().get(0), 3, 1);
		mixedTournament.insertScore(3, mixedTournament.getSchedule().get(2).getMatchesList().get(1), 2, 4);
		mixedTournament.insertScore(3, mixedTournament.getSchedule().get(2).getMatchesList().get(2), 3, 1);
		mixedTournament.insertScore(3, mixedTournament.getSchedule().get(2).getMatchesList().get(3), 2, 4);
		mixedTournament.insertScore(4, mixedTournament.getSchedule().get(3).getMatchesList().get(0), 3, 1);
		mixedTournament.insertScore(4, mixedTournament.getSchedule().get(3).getMatchesList().get(1), 2, 4);
		mixedTournament.insertScore(4, mixedTournament.getSchedule().get(3).getMatchesList().get(2), 3, 1);
		mixedTournament.insertScore(4, mixedTournament.getSchedule().get(3).getMatchesList().get(3), 2, 4);
		mixedTournament.insertScore(5, mixedTournament.getSchedule().get(4).getMatchesList().get(0), 3, 1);
		mixedTournament.insertScore(5, mixedTournament.getSchedule().get(4).getMatchesList().get(1), 2, 4);
		mixedTournament.insertScore(5, mixedTournament.getSchedule().get(4).getMatchesList().get(2), 3, 1);
		mixedTournament.insertScore(5, mixedTournament.getSchedule().get(4).getMatchesList().get(3), 2, 4);
		mixedTournament.insertScore(6, mixedTournament.getSchedule().get(5).getMatchesList().get(0), 3, 1);
		mixedTournament.insertScore(6, mixedTournament.getSchedule().get(5).getMatchesList().get(1), 3, 1);
		mixedTournament.insertScore(6, mixedTournament.getSchedule().get(5).getMatchesList().get(2), 3, 1);
		mixedTournament.insertScore(6, mixedTournament.getSchedule().get(5).getMatchesList().get(3), 3, 1);
		mixedTournament.insertScore(7, mixedTournament.getSchedule().get(6).getMatchesList().get(0), 3, 1);
		mixedTournament.insertScore(7, mixedTournament.getSchedule().get(6).getMatchesList().get(1), 3, 1);
		mixedTournament.insertScore(7, mixedTournament.getSchedule().get(6).getMatchesList().get(2), 3, 1);
		mixedTournament.insertScore(7, mixedTournament.getSchedule().get(6).getMatchesList().get(3), 3, 1);
		for(Day day:mixedTournament.getSchedule())
			System.out.println(day.toString());
		System.out.println(mixedTournament.getTournamentElement().toString());
		if(mixedTournament.isGroupCompleted()) {
			mixedTournament.initKnockoutPhase();
			for(Day day:mixedTournament.getBoardSchedule())
				System.out.println(day.toString());
			mixedTournament.insertScore(1, mixedTournament.getBoardSchedule().get(0).getMatchesList().get(0), 1, 2);
			mixedTournament.insertScore(1, mixedTournament.getBoardSchedule().get(0).getMatchesList().get(1), 3, 0);
			((Board)mixedTournament.getBoard()).addNextDay();
			mixedTournament.insertScore(2, mixedTournament.getBoardSchedule().get(1).getMatchesList().get(0), 2, 3);
			for(Day day:mixedTournament.getBoardSchedule())
				System.out.println(day.toString());
		}
		
	}
}
