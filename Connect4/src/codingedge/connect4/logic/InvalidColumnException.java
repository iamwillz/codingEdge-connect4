package codingedge.connect4.logic;

public class InvalidColumnException extends Exception {
	public InvalidColumnException() {
		super();
	}
	
	public InvalidColumnException(String message) {
		super(message);
	}
}
