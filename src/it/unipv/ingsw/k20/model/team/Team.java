package it.unipv.ingsw.k20.model.team;

import java.util.ArrayList;

public class Team {
	private String name;
	private String coach;
	private int goalsScored;
	private int goalsConceded;
	private int points;
	private String stadium;
	private ArrayList<Player> players;
   
  public Team(String name, String stadium,int goalsScored,int goalsConceded) {
       if (name==null)
           throw new RuntimeException("Il nome della squadra non puo' essere nullo! Oggetto non creato");
       
       this.name = name;
       this.points=0;
       this.stadium = stadium;
       this.players = new ArrayList<>();
       this.goalsScored=goalsScored;
		this.goalsConceded=goalsConceded;
  }
  // copy the players from the old array to the new one, checking that there is not already one with the same name.
  // start from position 1, leaving 0 free to put the new player in first position
  public void addPlayer(Player newPlayer) {
	   for(int i=1; i < players.size(); i++) {
           int numeroNuovoGiocatore = newPlayer.getNumber();
           int numero = this.players.get(i-1).getNumber();
           if (numero == numeroNuovoGiocatore)
               throw new RuntimeException("Esiste gia' un giocatore col numero " + numero +"! Inserimento annullato");
         players.remove(i-1);
       }
       
       Player p = newPlayer;
   }
   
  public void printFormation() {
       System.out.println("Formazione squadra: " + this.name);
       for (Player p : players)
           if (p.getHolder())
               System.out.println(p.getNumber() + " " + p.getName());
   }
  public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public int getPoints(){
		return points;
	}

	public void setPoints(int points){
		this.points=points;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}

	public int getGoalsConceded() {
		return goalsConceded;
	}
	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}
  public  String playerName(int number) {
       for (Player p : players)
           if (p.getNumber() == number)
               return p.getName();
       throw new RuntimeException("Il giocatore con il numero " + number + " non esiste!");
   }
   
  public  String getName() {
       return this.name;
   }
   public String getStadium() {
   	return stadium;
   }
   public int numberOfPlayers() {
       return this.players.size();
       
   }
   
  public int hashCode() {
	   return name.hashCode();	
  }
  @Override
  public boolean equals(Object obj) {
	  if (obj == null) return false;
	  if (obj == this) return true;
	  Team team = (Team) obj;
	  return team.getName() == this.getName();
  }

  public String toString() {
	  return "-name-" +this.name+ "-stadium-" + this.stadium+"-coach-"+this.coach+"-goalsScored-"+this.goalsScored+
	"-goalsConceded-"+this.goalsConceded;
  }

}

