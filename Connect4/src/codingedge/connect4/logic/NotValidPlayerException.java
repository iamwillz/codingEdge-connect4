package codingedge.connect4.logic;

public class NotValidPlayerException extends Exception {
	
	public NotValidPlayerException() { 
		super();
	}
	
	public NotValidPlayerException(String message) {
		super(message);
	}

}
