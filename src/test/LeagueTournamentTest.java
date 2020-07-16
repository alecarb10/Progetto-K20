package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.element.Day;
import domain.match.Match;
import domain.team.Team;
import domain.tournament.League;
import domain.tournament.Tournament;

/**
 * Class for JUnit tests on the League class
 * @see Tournament
 * @see League
 * @see Day
 * @see Match
 * @see Team
 */

class LeagueTournamentTest {

	private static Tournament league;
	private static List<Team> teams;
	private static List<Day> schedule;

	/**
	 * Method for init the league tournament
	 */
	private static void init() {
		// league creation
		league = new League("Tester League");

		// teams creation
		teams = new ArrayList<Team>();
		Team team1 = new Team("Pavia");
		Team team2 = new Team("Milano");
		Team team3 = new Team("Trapani");
		Team team4 = new Team("Piacenza");
		teams.add(team1);
		teams.add(team2);
		teams.add(team3);
		teams.add(team4);
		league.addTeams(teams);

		// schedule creation with days and matches
		schedule = new ArrayList<>();
		List<Match> matches = new ArrayList<>();
		matches.add(new Match(new Date(), teams.get(0), teams.get(1))); // 1-2 M
		matches.add(new Match(new Date(), teams.get(2), teams.get(3))); // 3-0 T
		schedule.add(new Day(1, matches, matches.get(0).getDate()));
		matches = new ArrayList<>();
		matches.add(new Match(new Date(), teams.get(0), teams.get(2))); // 1-1
		matches.add(new Match(new Date(), teams.get(3), teams.get(1))); // 2-0 P
		schedule.add(new Day(2, matches, matches.get(0).getDate()));
		matches = new ArrayList<>();
		matches.add(new Match(new Date(), teams.get(3), teams.get(0)));
		matches.add(new Match(new Date(), teams.get(1), teams.get(2)));
		schedule.add(new Day(3, matches, matches.get(0).getDate()));
		league.setGroupSchedule(schedule);
	}
	
	/**
	 * Method for counting the matches played by a team
	 * @param team
	 * @return counter
	 */
	private int playedMatchesByTeam(Team team) {
		int counter = 0;
		for (Day d : schedule) {
			for (Match m : d.getMatchesList())
				if (m.isPlayed())
					if (m.getHomeTeam().getName().equals(team.getName())
							|| m.getAwayTeam().getName().equals(team.getName()))
						counter++;
		}
		return counter;
	}
	
	/**
	 * Method used for insert results in matches 
	 */
	private void setMatchesScore() {
		league.insertScore(1, league.getGroupSchedule().get(0).getMatchesList().get(0), 1, 2);
		league.insertScore(1, league.getGroupSchedule().get(0).getMatchesList().get(1), 3, 0);
		league.insertScore(2, league.getGroupSchedule().get(1).getMatchesList().get(0), 1, 1);
		league.insertScore(2, league.getGroupSchedule().get(1).getMatchesList().get(1), 2, 0);
	}
	
	/**
	 * This test checks if the number of the added teams, in tournament, is correct
	 */
	@Test
	public void countTeamsAddedTest() {
		init();
		assertEquals(4, league.getTeamsList().size());
	}

	/**
	 * This test controls if you're trying to insert a score in a day, and the previous day is not completed
	 */
	@Test
	public void wrongDayInsertScoreTest() {
		init();
		assertFalse(league.insertScore(3, league.getGroupSchedule().get(2).getMatchesList().get(0), 2, 2));
	}
	
	/**
	 * This test checks the first team in the ranking
	 */
	@Test
	public void checkFirstTeamOnRankingTest() {
		init();
		setMatchesScore();
		assertEquals(teams.get(2).getName(), league.getTeamsList().get(0).getName());
	}
	
	/**
	 * This test checks the points of the first team in the ranking
	 */
	@Test
	public void checkFirstTeamPointsOnRankingTest() {
		init();
		setMatchesScore();
		assertEquals(4, league.getTeamsList().get(0).getPoints());
	}
	
	/**
	 * This test checks the number of played matches by a team
	 */
	@Test
	public void checkPlayedMatchesByTeamTest() {
		init();
		setMatchesScore();
		assertEquals(2, playedMatchesByTeam(teams.get(3)));
	}

}
