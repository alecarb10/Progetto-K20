package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.team.Team;
import model.tournament.League;
import model.tournament.Tournament;

class LeagueTournamentTest {
	
	private static Tournament league;
	private static ArrayList<Team> teams;

	@BeforeAll
	public static void init() {

		league = new League("Tester League");
		teams = new ArrayList<Team>();
		Team team1 = new Team("Team 1");
		Team team2 = new Team("Team 2");
		Team team3 = new Team("Team 3");
		Team team4 = new Team("Team 4");

		teams.add(team1);
		teams.add(team2);
		teams.add(team3);
		teams.add(team4);

		league.initGroup(teams);
	}

	@Test
	public void countTeamsAddedTest() {
		assertEquals(4, league.getTeamsList().size());
	}

	@Test
	public void insertScoreTest() {
		assertTrue(league.insertScore(1, league.getGroupSchedule().get(0).getMatchesList().get(0), 1, 2));
	}

}
