package mvc.view.manager.textui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.dao.impl.FacadeImpl;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.*;

public class ManagerTextUI {

	private Scanner scanner;
	private String inputString;
	private Tournament tournament;
	private String username;
	FacadeImpl facade;

	public ManagerTextUI() {
		scanner = new Scanner(System.in);
		this.facade = new FacadeImpl();
	}

	/* Metodo di avvio. */
	public void start() throws SQLException {
		while (true) {
			System.out.println("** Welcome to the reserved area for managers. **");
			System.out.println("Enter \"1\" to sign up, \"2\" to sign in.");
			System.out.println("Enter \"e\" to exit, \"b\" to go back.\n");

			System.out.println("Input: ");
			inputString = scanner.nextLine();

			if (inputString.equalsIgnoreCase("e")) {
				System.out.println("Closing app...");
				System.exit(0);
			}
			if (inputString.equalsIgnoreCase("b")) {
				System.out.println("You're already on the main menu.\n");
				continue;
			}

			try {
				switch (Integer.parseInt(inputString)) {
				case 1:
					this.login();
					break;
				case 2:
					this.registration();
					break;
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}

	private void login() throws SQLException {
		while (true) {
			System.out.println("\nSign up.");
			System.out.println("Username: ");
			username = scanner.nextLine();
			System.out.println("Password: ");
			String password = scanner.nextLine();

			if (facade.checkManagerLogin(username, password)) {
				System.out.println("Signed up\n");
				break;
			}	
			else
				System.out.println("Invalid username or password");
		}
		this.menu();
	}

	private void registration() throws SQLException {
		while (true) {
			System.out.println("\nSign in.");
			System.out.println("Name: ");
			String managerName = scanner.nextLine();
			System.out.println("Surname: ");
			String managerSurname = scanner.nextLine();
			System.out.println("Username: ");
			String managerUsername = scanner.nextLine();
			System.out.println("Password:");
			String managerPassword = scanner.nextLine();
			System.out.println("Repeat password: ");
			String managerRepeatPassword = scanner.nextLine();

			if (!(managerPassword.equals(managerRepeatPassword))) {
				System.out.println("Passwords were different. Please retry. ");
				continue;
			}

			if (!this.facade.checkUnique(managerUsername)) {
				System.out.println("Username already used. Please change it. ");
				continue;
			}

			if (this.facade.storeManager(managerUsername, managerName, managerSurname, managerPassword)) {
				System.out.println("Signed in\n");
				break;
			}
			else
				System.out.println("Registration failed.");
		}
		this.login();
	}

	private void menu() throws SQLException {
		while (true) {
			System.out.println("Welcome " + username + "\n");
			System.out.println("** Main menu. Enter the number relate to your preference. **\n");
			System.out.println("1 - Get tournaments list.");
			System.out.println("2 - Create tournament.");
			System.out.println("Enter \"e\" to exit, \"l\" to log out.\n");

			System.out.print("Input: ");
			inputString = scanner.nextLine();

			if (inputString.contentEquals("e")) {
				System.out.println("Closing app...");
				System.exit(0);
			}
			if (inputString.contentEquals("l"))
				break;

			try {
				switch (Integer.parseInt(inputString)) {
				case 1:
					this.getTournamentsList();
					break;
				case 2:
					this.createTournament();
					break;
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}

	private void getTournamentsList() throws SQLException {
		List<Tournament> tournaments = facade.getAllTournamentsByManager(username);
		
		while(true) {
			System.out.println("\nChoose a tournament: \n");
			for (int i = 0; i < tournaments.size(); i++) 
				System.out.println((i + 1) + ") " + tournaments.get(i).getName());
			System.out.println("Enter \"e\" to exit, \"b\" to go back.\n");
			
			System.out.print("Input: ");
			inputString = scanner.nextLine();

			if (inputString.contentEquals("e")) {
				System.out.println("Closing app...");
				System.exit(0);
			}
			if (inputString.contentEquals("b"))
				break;

			try {
				if (Integer.parseInt(inputString) < 1 || Integer.parseInt(inputString) > tournaments.size())
					System.out.println("Wrong number - tournament doesn't exist");
				else {
					tournament = tournaments.get(Integer.parseInt(inputString) - 1);
					manageTournament();
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void manageTournament() throws SQLException {
		while(true) {
			System.out.println("\nTournament: " + tournament.getName() + "\n");
			
			System.out.println("Enter \"1\" to insert results");
			System.out.println("Enter \"2\" to add stadiums");
			System.out.println("Enter \"3\" to edit teams");
			System.out.println("Enter \"e\" to exit, \"b\" to go back.\n");
			
			System.out.print("Input: ");
			inputString = scanner.nextLine();

			if (inputString.contentEquals("e")) {
				System.out.println("Closing app...");
				System.exit(0);
			}
			if (inputString.contentEquals("b"))
				break;

			try {
				switch (Integer.parseInt(inputString)) {
				case 1:
					insertResult();
					break;
				case 2:
					addStadium();
					break;
				case 3:
					editTeam();
					break;
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.");
			}
		}
	}
	
	private void editTeam() throws SQLException {
		tournament.addTeams(facade.getTeamsByTournament(tournament));
		
		while (true) {
			System.out.println("\nChoose a team: \n");
			for (int i = 0; i < tournament.getTeamsList().size(); i++) 
				System.out.println((i + 1) + ") " + tournament.getTeamsList().get(i).getName());
			System.out.println("Enter \"e\" to exit, \"b\" to go back.\n");
			
			System.out.print("Input: ");
			inputString = scanner.nextLine();
			
			if (inputString.contentEquals("e")) {
				System.out.println("Closing app...");
				System.exit(0);
			}
			if (inputString.contentEquals("b"))
				break;
			
			try {
				if (Integer.parseInt(inputString) < 1 || Integer.parseInt(inputString) > tournament.getTeamsList().size())
					System.out.println("Wrong number - team doesn't exist");
				else {
					manageTeam(Integer.parseInt(inputString) - 1);
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void manageTeam(int index) {
		while(true) {
			System.out.println("\nTeam: " + tournament.getTeamsList().get(index).getName() + "\n");
			
			System.out.println("Enter \"1\" to select a stadium");
			System.out.println("Enter \"2\" to add players");
			System.out.println("Enter \"3\" to edit players");
			System.out.println("Enter \"e\" to exit, \"b\" to go back.\n");
			
			System.out.print("Input: ");
			inputString = scanner.nextLine();

			if (inputString.contentEquals("e")) {
				System.out.println("Closing app...");
				System.exit(0);
			}
			if (inputString.contentEquals("b"))
				break;
			
			try {
				switch (Integer.parseInt(inputString)) {
				case 1:
					selectStadium();
					break;
				case 2:
					addPlayer();
					break;
				case 3:
					editPlayer();
					break;
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void selectStadium() {
		
	}
	
	private void addPlayer() {
		
	}
	
	private void editPlayer() {
		
	}

	private void addStadium() throws SQLException {
		while (true) {
			System.out.println("Add a stadium.");
			System.out.println("Name: ");
			String name = scanner.nextLine();
			System.out.println("City: ");
			String city = scanner.nextLine();
			System.out.println("Capacity: ");
			int capacity = 0;
			try {
				capacity = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
				continue;
			}
			
			Stadium s = new Stadium(name, city, capacity);
			if (facade.checkUniqueStadium(s)) {
				if (facade.storeStadium(s)) {
					System.out.println("\nStadium added");
					break;
				}
				else
					System.out.println("\nStadium not added");
			}
			else
				System.out.println("\nStadium name already exists.\n");		
		}
	}

	private void insertResult() {
				
	}

	private void createTournament() throws SQLException {
		boolean condition = true;
		while (condition) {
			System.out.println("Choose tounament type: \n");
			System.out.println("1 - LEAGUE");
			System.out.println("2 - KNOCKOUT PHASE");
			System.out.println("3 - MIXED\n");
			System.out.println("Enter \"e\" to exit, \"b\" to go back.\n");

			System.out.print("Input: ");
			inputString = scanner.nextLine();

			if (inputString.contentEquals("e")) {
				System.out.println("Closing app...");
				System.exit(0);
			}
			if (inputString.contentEquals("b"))
				break;

			try {
				switch (Integer.parseInt(inputString)) {
				case 1:
					createLeagueTournament();
					condition = false;
					break;
				case 2:
					createKnockoutPhaseTournament();
					condition = false;
					break;
				case 3:
					createMixedTournament();
					condition = false;
					break;
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}

	private void createMixedTournament() throws SQLException {
		System.out.println("Enter tournament name: ");
		String name = scanner.nextLine();

		int size = 0;
		while(true) {
			System.out.println("Enter tournament size (4, 8, 16): ");
			inputString = scanner.nextLine();
			try {
				size = Integer.parseInt(inputString);
				if (size == 4 || size == 8 || size == 16)
					break;
				else
					System.out.println("This size is not accepted\n");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
		
		List<Team> teams = insertTeams(size);
		this.tournament = new MixedTournament(name);
		if (storeTournament(teams))
			System.out.println("Tournament created\n");
		else
			System.out.println("Tournament not created\n");

	}

	private void createKnockoutPhaseTournament() throws SQLException {
		System.out.println("Enter tournament name: ");
		String name = scanner.nextLine();

		int size = 0;
		while(true) {
			System.out.println("Enter tournament size (4, 8, 16): ");
			inputString = scanner.nextLine();
			try {
				size = Integer.parseInt(inputString);
				if (size == 4 || size == 8 || size == 16)
					break;
				else
					System.out.println("This size is not accepted\n");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
		
		List<Team> teams = insertTeams(size);
		this.tournament = new KnockoutPhase(name);
		if (storeTournament(teams))
			System.out.println("Tournament created\n");
		else
			System.out.println("Tournament not created\n");
	}

	private void createLeagueTournament() throws SQLException {
		System.out.println("Enter tournament name: ");
		String name = scanner.nextLine();
		
		int size = 0;
		while(true) {
			System.out.println("Enter tournament size (4, 6, 8, 10, 12, 14, 16): ");
			inputString = scanner.nextLine();
			try {
				size = Integer.parseInt(inputString);
				if (size % 2 == 0 && size >= 4 && size <= 16)
					break;
				else
					System.out.println("This size is not accepted\n");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
		
		List<Team> teams = insertTeams(size);
		this.tournament = new League(name);
		if (storeTournament(teams))
			System.out.println("Tournament created\n");
		else
			System.out.println("Tournament not created\n");
	}
	
	private boolean storeTournament(List<Team> teamsList) throws SQLException {
		if (facade.checkUnique(tournament.getName(), username)) {
			if (facade.storeTournament(tournament, username)) {
				int tournamentId = facade.getLastTournamentID();
				tournament.setId(tournamentId);
				tournament.initTournament(teamsList);
				facade.storeElement(tournament);
				int elementId = facade.getLastElementID(tournament.getTournamentElement());
				tournament.getTournamentElement().setId(elementId);
				for (Team team:teamsList) {
					facade.storeTeam(team, tournament);
					int teamId = facade.getLastTeamID();
					team.setId(teamId);
				}
				facade.storeSchedule(tournament.getSchedule(), tournament);
				return true;
			}
		}
		else
			System.out.println("Tournament name already exists\n");
		
		return false;
	}
	
	private List<Team> insertTeams(int size) {
		List<Team> teams = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			System.out.println("Enter team name: ");
			String teamName = scanner.nextLine();
			Team team = new Team(teamName);
			teams.add(team);
		}
		
		return teams;
	}
}
