package mvc.model.element;

import java.util.Date;
import java.util.List;

import mvc.model.match.Match;

public class Day {
	
	private int id;
	private int number;
	private List<Match> matchesList;
	private Date date;
	
	public Day(int number, List<Match> matchesList, Date date) {
		this.number = number;
		this.matchesList = matchesList;
		this.date = date;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Match> getMatchesList() {
		return matchesList;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public boolean isCompleted() {
		int n = 0;
		
		for (Match m: matchesList)
			if (m.isPlayed()) 
				n++;
		
		return n == matchesList.size() ? true : false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Day " + number + "(" + date.toString() +")" + " : \n");
		
		for (Match m: matchesList)
			sb.append(m + "\n");
		
		return sb.toString();
	}
}
