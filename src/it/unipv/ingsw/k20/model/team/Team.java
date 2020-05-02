package it.unipv.ingsw.k20.model.team;


public class Team implements Comparable<Team>{
	 private String name;
	 private int score;
	    private String stadium;
	    private Player[] players;
	    private int points;
	    
	   public Team(String name, String stadium) {
	        if (name==null)
	            throw new RuntimeException("Il nome della squadra non puo' essere nullo! Oggetto non creato");
	        
	        this.name = name;
	        this.stadium = stadium;
	        this.players = new Player[0];
	    }
	    
	   public void addPlayer(Player newPlayer) {
	        Player[] playersArray = new Player[this.players.length + 1];
	        
	       
	        for (int i=1; i < playersArray.length; i++) {
	            int numeroNuovoGiocatore = newPlayer.getNumber();
	            int numero = this.players[i-1].getNumber();
	            if (numero == numeroNuovoGiocatore)
	                throw new RuntimeException("Esiste gia' un giocatore col numero " + numero +"! Inserimento annullato");
	            playersArray[i] = this.players[i-1];
	        }
	        
	        playersArray[0] = newPlayer;
	        this.players = playersArray;
	    }
	    
	   public void printFormation() {
	        System.out.println("Formazione squadra: " + this.name);
	        for (int i=0; i < this.players.length; i++)
	            if (this.players[i].getHolder())
	                System.out.println(this.players[i].getNumber() + " " + this.players[i].getName());
	    }
	    
	   public  String playerName(int number) {
	        for (int i=0; i < this.players.length; i++)
	            if (this.players[i].getNumber() == number)
	                return this.players[i].getName();
	        throw new RuntimeException("Il giocatore con il numero " + number + " non esiste!");
	    }
	    
	   public  String getName() {
	        return this.name;
	    }
	    public String getStadium() {
	    	return stadium;
	    }
	    public int getScore() {
	    	return score;
	    }
	    public void setScore(int score) {
	    	this.score=score;
	    }
	    public int numberOfPlayers() {
	        return this.players.length;
	    }
	    
	   public  Player getPlayer(int number) {
	        return this.players[number];
	    }
	   public int hashCode() {
		   return name.hashCode();	
	   }

		@Override
		public int compareTo(Team s) {
			 if(name.equals(s.getName()))
				   return 0;
			   int result = score - s.getScore();
			   if(result ==0)
				   result= name.compareTo(s.getName());
			   return result;
		   }
		    public String toString() {
		    	return String.format("-name- %s -stadium- %s ", this.name, this.stadium);
		    }

}
