package codingedge.connect4.logic;

public class Game {
	private String playerOne, playerTwo;	
	
	private Board board;
	
	public enum State {
		GAME_OVER_DRAW, GAME_OVER_ONE, GAME_OVER_TWO, GAME_ACTIVE, GAME_INACTIVE
	}
	
	public State currentState;
	
	public Game() {
		this.playerOne = "Player One";
		this.playerTwo = "Player Two";
		
		currentState = State.GAME_INACTIVE;
		board = new Board();
	}
	
	public Game(String playerOne, String playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		
		currentState = State.GAME_INACTIVE;
		board = new Board();
	}
	
	public Game(int width, int height) { 
		this.playerOne = "Player One";
		this.playerTwo = "Player Two";
		
		currentState = State.GAME_INACTIVE;
		board = new Board(width, height);	
	}
	
	public Game(int width, int height, String playerOne, String playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		
		currentState = State.GAME_INACTIVE;
		board = new Board(width, height);	
	}
	
	public void startGame() {
		board.clearBoard();
		currentState = State.GAME_ACTIVE;
	}

	public void makeMove(int col, int player) throws InvalidMoveException, GameNotActiveException, NotValidPlayerException, InvalidColumnException {
		if (currentState == State.GAME_ACTIVE) { 
			if (player == 1 || player == -1) {
				int row = board.addToColumn(col, player);
				if (row < 0) { 
					throw new InvalidMoveException("The move is invalid, the column is full");					
				} else {
					updateGameStateFromPosition(col, row);
				}
			} else {
				throw new NotValidPlayerException("Please use a valid int for player (1 or -1)");		
			}
		} else { 
			throw new GameNotActiveException("Game has not started yet, please start the game.");
		}
	}
	
	public void endGameAsDraw() {
		currentState = State.GAME_OVER_DRAW;
	}
	
	public boolean checkIsGameOver() {
		// Only check for winner if the game is active
		//updateGameState();
		
		return (currentState == State.GAME_OVER_DRAW || 
				currentState == State.GAME_OVER_ONE  || 
				currentState == State.GAME_OVER_TWO);
	}
	
	public State getCurrentGameState() {
		return currentState;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public int[][] getBoardArray() {
		return board.getBoardArray();
	}
	
	// TODO:remove method
	private void updateGameState() { 
		if (currentState == State.GAME_ACTIVE) {
			int winner = board.isGameWon();
			if (winner == 1) {
				currentState = State.GAME_OVER_ONE;
			} else if (winner == -1) {
				currentState = State.GAME_OVER_TWO;
			} else if (board.isBoardFull()) {
				currentState = State.GAME_OVER_DRAW;
			}
		}
	}
	
	private void updateGameStateFromPosition(int col, int row) { 
		if (currentState == State.GAME_ACTIVE) {
			int winner = board.isGameWonFromPosition(col, row);
			if (winner == 1) {
				currentState = State.GAME_OVER_ONE;
			} else if (winner == -1) {
				currentState = State.GAME_OVER_TWO;
			} else if (board.isBoardFull()) {
				currentState = State.GAME_OVER_DRAW;
			}
		}
	}
}
