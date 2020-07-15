package domain.exception;

public class SameTeamNameException extends Exception {

	private static final long serialVersionUID = 1L;

	public SameTeamNameException(String msg) {
		super(msg);
	}	
	
}
