package mvc.view.manager.textui;

import java.sql.SQLException;
import java.util.Scanner;

import database.dao.impl.FacadeImpl;
import mvc.model.team.Team;
import mvc.model.tournament.*;

public class ManagerTextUI {

	private Scanner scanner;
	private String inputString;
	private Tournament tournament;
	FacadeImpl facade;

	public ManagerTextUI() {
		scanner = new Scanner(System.in);
		this.facade = new FacadeImpl();
	}

	/* Metodo di avvio. */
	public void start() {
		while (true) {
			System.out.println("** Welcome to the reserved area for managers. **");
			System.out.println("Enter \"1\" to get login, \"2\" to get registration.");
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

	private void registration() {
		while (true) {
			System.out.println("Name: ");
			String managerName = scanner.nextLine();
			System.out.println("Surname: ");
			String managerSurname = scanner.nextLine();
			System.out.println("Username: ");
			String managerUsername = scanner.nextLine();
			System.out.println("Password:");
			String managerPassword = scanner.nextLine();
			// System.out.println("Repeat password: ");
			// String managerRepeatPassword = scanner.nextLine();
			try {
				if (this.facade.storeManager(managerUsername, managerName, managerSurname, managerPassword) && this.facade.checkUnique(managerUsername)) {
					this.menu();
				} else {
					System.out.println("Registration failed. Please retry.");
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}

	private void login() {
		while (true) {
			System.out.println("Username: ");
			String usernameString = scanner.nextLine();
			System.out.println("Password: ");
			String passwordString = scanner.nextLine();
			try {
				facade.checkManagerLogin(usernameString, passwordString);
				this.menu();
			} catch (SQLException e) {
				e.getMessage();
			}

		}
	}

	private void menu() {
		while (true) {
			System.out.println("** Main menu. Enter the number relate to your preference. **\n");
			System.out.println("1 - Get tournaments list.");
			System.out.println("2 - Create tournament.");
			System.out.println("3 - Add team in a tournament.");
			System.out.println("4 - Remove team from a tournament.\n");
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
					this.getTournamentsList();
					break;
				case 2:
					this.createTournament();
					break;
				case 3:
					this.addTeamInTournament();
				case 4:
					this.removeTeamFromTournament();
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}
	}

	private void removeTeamFromTournament() {
		System.out.println("Team Name: ");
		String teamName = scanner.nextLine();
		System.out.println("Team Coach: ");
		String teamCoach = scanner.nextLine();
		// Team team = new Team(teamName, stadium, teamCoach);
		// this.tournament.removeTeamFromTournament(t);
	}

	private void addTeamInTournament() {
		System.out.println("Team Name: ");
		String teamName = scanner.nextLine();
		Team newTeam = new Team(teamName);
		this.tournament.addTeamInTournament(newTeam);
	}

	private void getTournamentsList() {

	}

	private void createTournament() {

		while (true) {
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
					break;
				case 2:
					createKnockoutPhaseTournament();
					break;
				case 3:
					createMixedTournament();
					break;
				default:
					System.out.println("Unavailable input.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.\n");
			}
		}

	}

	private void createMixedTournament() {
		System.out.println("Enter tournament name: ");
		inputString = scanner.nextLine();
		try {
			this.tournament = new MixedTournament(inputString, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void createKnockoutPhaseTournament() {
		System.out.println("Enter tournament name: ");
		inputString = scanner.nextLine();
		try {
			this.tournament = new KnockoutPhase(inputString, null);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void createLeagueTournament() {
		System.out.println("Enter tournament name: ");
		inputString = scanner.nextLine();
		try {
			this.tournament = new League(inputString, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
