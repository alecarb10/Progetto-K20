package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mvc.model.match.Match;
import mvc.model.team.Team;
import mvc.model.tournament.League;
import mvc.model.tournament.Tournament;

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

		league.initTournament(teams);
	}

	@Test
	public void countTeamsAddedTest() {
		assertEquals(4, league.getTeamsList().size());
	}

	@Test
	public void insertScoreTest() {
		assertTrue(league.insertScore(1, league.getSchedule().get(0).getMatchesList().get(0), 1, 2));
	}

}
