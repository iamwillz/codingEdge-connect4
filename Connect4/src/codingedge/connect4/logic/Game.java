package codingedge.connect4.logic;

import java.util.Scanner;

public class Game {
	
	public String playerOne, playerTwo;	
	
	public enum State {
		GAME_OVER_DRAW, GAME_OVER_ONE, GAME_OVER_TWO, GAME_ACTIVE, GAME_INACTIVE
	}
	
	public State currentState;
	
	public static int PLAYER_ONE_INT = 1, PLAYER_TWO_INT = 2;

	private Board board;
	
	private int currentPlayer;

	
	public Game() {
		this.playerOne = "Player One";
		this.playerTwo = "Player Two";
		
		currentPlayer = PLAYER_ONE_INT;
		currentState = State.GAME_INACTIVE;
		board = new Board();
	}
	
	public void startGame() {
		board.clearBoard();
		currentState = State.GAME_ACTIVE;
	}
	
	public void makeNextMove(int col) throws InvalidMoveException, GameNotActiveException, NotValidPlayerException, InvalidColumnException {
		makeMove(col, currentPlayer);
		currentPlayer = currentPlayer == PLAYER_ONE_INT? PLAYER_TWO_INT : PLAYER_ONE_INT;
	}
	
	private void makeMove(int col, int player) throws InvalidMoveException, GameNotActiveException, NotValidPlayerException, InvalidColumnException {
		if (currentState == State.GAME_ACTIVE) { 
			if (player == PLAYER_ONE_INT || player == PLAYER_TWO_INT) {
				int row = board.addToColumn(col, player);
				if (row < 0) { 
					throw new InvalidMoveException("The move is invalid, the column is full");					
				} else {
					updateGameStateFromPosition(col, row);
				}
			} else {
				throw new NotValidPlayerException("Please use a valid int for player (" + PLAYER_ONE_INT + " or " + PLAYER_TWO_INT + ")");		
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
	
	public int getPlayerOneInt(){
		return PLAYER_ONE_INT;
	}
	
	public int getPlayerTwoInt(){
		return PLAYER_TWO_INT;
	}
	
	public int[][] getBoardArray() {
		return board.getBoardArray();
	}
	
	public int getCurrentPlayer(){
		return currentPlayer;
	}
	
	public void drawBoard(){ 
		board.drawBoard(PLAYER_ONE_INT, PLAYER_TWO_INT);
	}
	
	private void updateGameStateFromPosition(int col, int row) { 
		if (currentState == State.GAME_ACTIVE) {
			int winner = board.isGameWonFromPosition(col, row);
			if (winner == PLAYER_ONE_INT) {
				currentState = State.GAME_OVER_ONE;
			} else if (winner == PLAYER_TWO_INT) {
				currentState = State.GAME_OVER_TWO;
			} else if (board.isBoardFull()) {
				currentState = State.GAME_OVER_DRAW;
			}
		}
	}
	
	private void showGameOver() {
		System.out.println("GAME OVER");
		switch (currentState) {
		default:
		case GAME_OVER_DRAW:
			System.out.println("DRAW");
			break;
		case GAME_OVER_ONE:
			System.out.println("PLAYER ONE WINS");
			break;
		case GAME_OVER_TWO:
			System.out.println("PLAYER TWO WINS");
			break;
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		Scanner reader = new Scanner(System.in);

		game.startGame();
		game.drawBoard();
		
		while (!game.checkIsGameOver()) {
			String playerStr = (game.currentPlayer == PLAYER_ONE_INT) ? game.playerOne : game.playerTwo;
			System.out.println(playerStr + "'s turn to move. Enter a column number between 0 and 6");

			int playerMove;
			if (reader.hasNextInt()) {
				playerMove = reader.nextInt();
				try {
					game.makeNextMove(playerMove);
					game.drawBoard();
				} catch (InvalidMoveException e) {
					System.out.println(e.getMessage());
				} catch (GameNotActiveException e) {
					System.out.println(e.getMessage());
				} catch (NotValidPlayerException e) {
					System.out.println(e.getMessage());
				} catch (InvalidColumnException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Please enter a valid number");
				reader.next();
			}
		}
		
		game.showGameOver();
		reader.close();
	}

}
