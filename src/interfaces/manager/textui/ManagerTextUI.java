package interfaces.manager.textui;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.element.Day;
import model.match.Match;
import model.team.Player;
import model.team.PlayerPositionType;
import model.team.Stadium;
import model.team.Team;
import model.tournament.*;
import services.persistence.dao.impl.FacadeImpl;

public class ManagerTextUI {

	private Scanner scanner;
	private String inputString;
	private Tournament tournament;
	private String username;
	FacadeImpl facade;

	public ManagerTextUI() {
		scanner = new Scanner(System.in);
		this.facade = FacadeImpl.getInstance();
	}

	/* -------------------------------- Home ----------------------------------------------------------------*/
	
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

	/* -------------------------------- Sign Up + Sign In ----------------------------------------------------------------*/
	
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

	/* -------------------------------- Main Menu ----------------------------------------------------------------*/
	
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
	
	/* --------------------- Visualizzazione Tornei + Gestione -------------------------------------------------------------------*/

	private void getTournamentsList() throws SQLException {
		while(true) {
			List<Tournament> tournaments = facade.getAllTournamentsByManager(username);
			
			if (tournaments.size() == 0) {
				System.out.println("\nNo tournaments found\n");
				break;
			}
			
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
					tournament.addTeams(facade.getTeamsByTournament(tournament));
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
					if (tournament.getTournamentType() == TournamentType.MIXED)
						insertResultForMixed();
					else {
						tournament.setSchedule(facade.getSchedule(tournament, false));
						insertResult();
					}
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
	
	private void insertResult() throws SQLException {
		while(true) {
			System.out.println("\nChoose a day: \n");
			for (Day d: tournament.getSchedule())
				System.out.println("Day " + d.getNumber() + " - " + d.getDate());
			
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
				int indexDay = Integer.parseInt(inputString);
				if (indexDay < 1 || indexDay > tournament.getSchedule().size())
					System.out.println("Wrong number - day doesn't exist");
				else {
					selectMatch(indexDay - 1);
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void selectMatch(int indexDay) throws SQLException {
		while (true) {
			System.out.println("\nDay " + (indexDay + 1) + ". Choose a match: \n");
			for (int i = 0; i < tournament.getSchedule().get(indexDay).getMatchesList().size(); i++)
				System.out.println((i + 1) + "° match: " + tournament.getSchedule().get(indexDay).getMatchesList().get(i));
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
				int indexMatch = Integer.parseInt(inputString);
				if (indexMatch < 1 || indexMatch > tournament.getSchedule().get(indexDay).getMatchesList().size())
					System.out.println("Wrong number - match doesn't exist");
				else {
					int scheduleSize = tournament.getSchedule().size();
					Match match = tournament.getSchedule().get(indexDay).getMatchesList().get(indexMatch - 1);
					System.out.println(match);
					System.out.println("Enter Home Score: ");
					int homeScore = Integer.parseInt(scanner.nextLine());
					System.out.println("Enter Away Score: ");
					int awayScore = Integer.parseInt(scanner.nextLine());
					
					if (tournament.insertScore(indexDay + 1, match, homeScore, awayScore)) {
						if (facade.updateMatch(tournament.getSchedule().get(indexDay).getMatchesList().get(indexMatch - 1))) {
							System.out.println("\nResult entered");
							
							if (tournament.getSchedule().size() > scheduleSize) {
								tournament.getTournamentElement().setId(facade.getBoardIDByTournament(tournament));
								if (facade.storeDay(tournament.getSchedule().get(tournament.getSchedule().size() - 1), tournament))
									System.out.println("Day added");
							}
								
							tournament.setSchedule(facade.getSchedule(tournament, false));
							break;
						}
					}	
					else 
						System.out.println("\nResult not entered - maybe previous day is not completed or match is already played");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void insertResultForMixed() throws SQLException {
		while(true) {
			System.out.println("\nChoose: \n");
			System.out.println("1) Manage group");
			System.out.println("2) Manage board");
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
				int choose = Integer.parseInt(inputString);
				
				tournament.setSchedule(facade.getSchedule(tournament, false));
				
				if (choose == 1) {
					if (tournament.getTournamentElement().isCompleted())
						System.out.println("Group is already completed");
					else
					System.out.println(tournament.getTournamentElement());
						insertResult();
				}
				else if (choose == 2) {
					if (!tournament.getTournamentElement().isCompleted())
						System.out.println("Group is not completed, board is not started yet");
					else {
						System.out.println("You can manage board");
						
						if (((MixedTournament)tournament).getBoardSchedule() == null) {
							((MixedTournament)tournament).initKnockoutPhase();
							for(Day day : ((MixedTournament)tournament).getBoardSchedule())
								System.out.println(day);
						}
						else {
							/* setSchedule da db */
						}
					}
				}
				else
					System.out.println("Unavailable input.\n");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
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
	
	private void editTeam() throws SQLException {
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
	
	private void manageTeam(int index) throws SQLException {
		while(true) {
			System.out.println("\nTeam: " + tournament.getTeamsList().get(index).getName() + "\n");
			
			System.out.println("Enter \"1\" to select a stadium");
			System.out.println("Enter \"2\" to add players");
			System.out.println("Enter \"3\" to edit players");
			System.out.println("Enter \"4\" to remove players");
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
					selectStadium(index);
					break;
				case 2:
					addPlayer(index);
					break;
				case 3:
					editPlayer(index);
					break;
				case 4:
					removePlayer(index);
					break;
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void selectStadium(int index) throws SQLException {
		List<Stadium> stadiums = facade.getStadiums();
		
		while (true) {
			System.out.println("\nChoose a stadium: \n");
			for (int i = 0; i < stadiums.size(); i++) 
				System.out.println((i + 1) + ") " + stadiums.get(i).getName());
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
				if (Integer.parseInt(inputString) < 1 || Integer.parseInt(inputString) > stadiums.size())
					System.out.println("Wrong number - stadium doesn't exist");
				else {
					tournament.getTeamsList().get(index).setStadium(stadiums.get(Integer.parseInt(inputString) - 1));
					if (facade.updateTeam(tournament.getTeamsList().get(index))) {
						System.out.println("\nStadium updated");
						break;
					}
					else
						System.out.println("\nStadium not updated");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void addPlayer(int index) throws SQLException {
		while (true) {
			System.out.println("\nAdd a player.");
			System.out.println("Name: ");
			String name = scanner.nextLine();
			System.out.println("Surname: ");
			String surname = scanner.nextLine();
			System.out.println("Number: ");
			int number = 0;
			try {
				number = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nInvalid input.");
				continue;
			}
			System.out.println("Position (GK, CB, MF, CF): ");
			PlayerPositionType position = null;
			try {
				position = PlayerPositionType.valueOf(scanner.nextLine());
			}
			catch (IllegalArgumentException e) {
				System.out.println("\nPosition not valid");
				continue;
			}
			
			Player p = new Player(name, surname, number, position);
			if (facade.storePlayer(p, tournament.getTeamsList().get(index))) {
				p.setId(facade.getLastPlayerID());
				tournament.getTeamsList().get(index).insertPlayer(p);
				System.out.println("\nPlayer added");
				break;
			}
			else
				System.out.println("\nPlayer not added");
		}	
	}
	
	private void editPlayer(int index) throws SQLException {
		while (true) {
			System.out.println("\nChoose a player: \n");
			for (int i = 0; i < tournament.getTeamsList().get(index).getPlayers().size(); i++) 
				System.out.println((i + 1) + ") " + tournament.getTeamsList().get(index).getPlayers().get(i).getSurname() + 
								   ". N° " + tournament.getTeamsList().get(index).getPlayers().get(i).getNumber());
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
				int indexPlayer = Integer.parseInt(inputString);
				if (indexPlayer < 1 || indexPlayer > tournament.getTeamsList().get(index).getPlayers().size())
					System.out.println("Wrong number - player doesn't exist");
				else {
					System.out.println();
					System.out.println(tournament.getTeamsList().get(index).getPlayers().get(indexPlayer - 1));
					int id = tournament.getTeamsList().get(index).getPlayers().get(indexPlayer - 1).getId();
					tournament.getTeamsList().get(index).removePlayer(tournament.getTeamsList().get(index).getPlayers().get(indexPlayer - 1));
					
					System.out.println("\nNew Name: ");
					String name = scanner.nextLine();
					System.out.println("New Surname: ");
					String surname = scanner.nextLine();
					System.out.println("New Number: ");
					int number = 0;
					try {
						number = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("\nInvalid input.");
						continue;
					}
					System.out.println("New Position (GK, CB, MF, CF): ");
					PlayerPositionType position = null;
					try {
						position = PlayerPositionType.valueOf(scanner.nextLine());
					}
					catch (IllegalArgumentException e) {
						System.out.println("\nPosition not valid");
						continue;
					}
					Player p = new Player(name, surname, number, position);
					p.setId(id);
					
					tournament.getTeamsList().get(index).insertPlayer(p);
					if (facade.updatePlayer(p)) {
						System.out.println("\nPlayer updated");
						break;
					}
					else
						System.out.println("\nPlayer not updated");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	private void removePlayer(int index) throws SQLException {
		while (true) {
			System.out.println("\nChoose a player: \n");
			for (int i = 0; i < tournament.getTeamsList().get(index).getPlayers().size(); i++) 
				System.out.println((i + 1) + ") " + tournament.getTeamsList().get(index).getPlayers().get(i).getSurname() + 
								   ". N° " + tournament.getTeamsList().get(index).getPlayers().get(i).getNumber());
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
				int indexPlayer = Integer.parseInt(inputString);
				if (indexPlayer < 1 || indexPlayer > tournament.getTeamsList().get(index).getPlayers().size())
					System.out.println("Wrong number - player doesn't exist");
				else {
					Player p = tournament.getTeamsList().get(index).getPlayers().get(indexPlayer - 1);
					if (facade.removePlayer(p)) {
						tournament.getTeamsList().get(index).removePlayer(p);
						System.out.println("\nPlayer removed");
						break;
					}
					else
						System.out.println("\nPlayer not removed");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}

	/* -------------------------------- Creazione Torneo ----------------------------------------------------------------*/
	
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
