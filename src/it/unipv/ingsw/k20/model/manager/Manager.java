package it.unipv.ingsw.k20.model.manager;

import java.util.HashMap;

import it.unipv.ingsw.k20.model.tournament.Tournament;

public class Manager {
	
	private String id; //username
	private String name;
	private String surname;
	private String password;
	private HashMap<Integer, Tournament> tournaments;

	public Manager(String id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.tournaments = new HashMap<>();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public HashMap<Integer, Tournament> getTournaments() {
		return tournaments;
	}
	
	/* Metodo che restituisce il Tournament relativo all'ID passato come parametro. */
	private Tournament getTournament(Integer tournamentID) {
		if(this.tournaments.containsKey(tournamentID)) {
			return this.tournaments.get(tournamentID);
		}
		else return null;
	}
	
	/* Metodo che rimuove il Tournament relativo all'ID passato come parametro. */
	private void removeTournament(Integer tournamentID) {
		if(this.tournaments.containsKey(tournamentID)) {
			this.tournaments.remove(tournamentID);
		}
	}
	
	/* Metodo che aggiunge alla mappa il Tournament passato come parametro. */
	public void addTournament(Tournament tournament) throws NullPointerException {
		this.tournaments.put(tournament.getTournamentID(), tournament);
	}

	@Override
	public String toString() {
		return "Manager ID: " + id + "\n\tName: " + name + "\n\tSurname: " + surname;
	}

	public boolean register() { 
		return false;
	}
	
	public boolean loign() { 
		return false;
	} 
}
