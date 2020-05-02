package it.unipv.ingsw.k20.view.textui;

import java.util.Scanner;

import it.unipv.ingsw.k20.model.manager.Manager;
import it.unipv.ingsw.k20.model.tournament.League;
import it.unipv.ingsw.k20.model.tournament.Tournament;

public class ManagerTextUI {

	private Scanner scanner;
	private String inputString;
	private String usernameString;
	private String passwordString;
	private Tournament tournament;
	//private Manager manager;

	public ManagerTextUI() {
		scanner = new Scanner(System.in);
	}

	/* Metodo di avvio. */
	public void login() {
		System.out.println("Welcome to the reserved area for managers. Please login.");
		System.out.println("Username: ");
		usernameString = scanner.nextLine();
		System.out.println("Password: ");
		passwordString = scanner.nextLine();
		// gestione login in sospeso..
		this.start();
	}

	private void start() {
		System.out.println("Main menu. Enter the number relate to your preference.");
		System.out.println("1 - Get tournaments list.");
		System.out.println("2 - Create tournament.");
		System.out.print("Input: ");
		inputString = scanner.nextLine();

		try {

			switch (Integer.parseInt(inputString)) {
			case 1:
				this.getTournamentsList();
				break;
			case 2:
				this.createTournament();
				break;
			default:
				System.out.println("Unavailable input.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}

	}

	private void getTournamentsList() {
		;
	}

	private void createTournament() {

		System.out.println("Choose tounament type: \n");
		System.out.println("1 - LEAGUE");
		System.out.println("2 - KNOCKOUT PHASE");
		System.out.println("3 - MIXED\n");

		System.out.print("Input: ");
		inputString = scanner.nextLine();

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
				System.out.println("Unavailable input.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}

	}

	private void createMixedTournament() {
		;
	}

	private void createKnockoutPhaseTournament() {
		;
	}

	// Da rivedere..
	private void createLeagueTournament() {
		// this.tournament = new League(name, manager, maxDays);
		System.out.println("Enter maximum days number: ");
		inputString = scanner.nextLine();
		this.tournament.initTournament(Integer.parseInt(inputString));
	}

}
