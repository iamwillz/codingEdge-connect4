package codingedge.connect4.logic;

public class Board {

	private static int DEFAULT_BOARD_WIDTH = 7;

	private static int DEFAULT_BOARD_HEIGHT = 6;

	private int width, height;
	// Board of ints representing the state of the board
	// 0 signifies no piece is in that cell
	// 1 signifies player one has a piece in that cell
	// -1 signifies player two has a piece in that cell
	// NOTE: we are assuming the players are either -1 or 1
	private int[][] board;

	// Initialize a default board of size 7x6 with all zeros (empty spaces)
	public Board() {
		this.width = DEFAULT_BOARD_WIDTH;
		this.height = DEFAULT_BOARD_HEIGHT;

		board = new int[this.width][this.height];
	}

	// Initialize a board of size width x height with all zeros
	public Board(int width, int height) {
		this.width = width;
		this.height = height;

		board = new int[this.width][this.height];
	}

	// Clear the board to all zeroes
	public void clearBoard() {
		board = new int[this.width][this.height];
	}

	// 
	// Returns false if the column is full, else true
	public boolean addToColumn(int column, int player) throws InvalidColumnException, NotValidPlayerException {
		if (column < 0 || column >= width) {
			throw new InvalidColumnException("Column number " + column + " was invalid");
		} else if (player != -1 && player != 1) { 
			throw new NotValidPlayerException("Please use a valid int for player (1 or -1)");	
		} else if (!isColumnFull(column)) {
			for (int h = 0; h < this.height; h++) {
				if (board[column][h] == 0) {
					board[column][h] = player;
					return true;
				}
			}
		}
		return false;
	}

	// TODO: Do I want this as an enum? Maybe a game state, enum?
	// GAME_NOT_OVER, GAME_OVER_BOARD_FULL, GAME_OVER_PLAYER_ONE_WINS,
	// GAME_OVER_PLAYER_TWO_WINS?
	// Check if game is over
	public boolean isGameOver() {
		// Check if the board is full or if there is a winner
		return isBoardFull() || (isGameWon() != 0);
	}
	
	// Scans the board from bottom left to top right
	// Looks for a possible 'winning' pattern
	// Returns 0 if the board is still playable
	// Return 1 if player one won
	// Return -1 if player two won
	public int isGameWon() {
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				// Check if there's a solution from this tile
				if (checkForSolutionFromPosition(x, y, board[x][y])) { 
					return board[x][y]; // Should be either 1 or -1
				}
			}
		}
		return 0;
	}

	public int isGameWonFromColumn(int col) { 
		for (int y = this.height - 1; y >= 0; y--) {
			if (board[col][y] == 0){
				continue;
			} else {
				return isGameWonFromPosition(col, y);
			}
		}
		return 0;
	}

	public int isGameWonFromPosition(int x, int y) { 
		if (checkForSolutionFromPosition(x, y, board[x][y])) { 
			return board[x][y];
		} else { 
			return 0;
		}
	}
	
	public int[][] getBoardArray() {
		return board.clone();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth(){
		return width;
	}

	// Return true if there is a set of 4 consecutive pieces from the same player at board[x][y]
	private boolean checkForSolutionFromPosition(int x, int y, int player) { 
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				if (dx != 0 || dy != 0) {
					if (checkForSolutionInDirection(x, y, dx, dy, player, 4)) {
						return true;
					}
				}
			}
		}
		
		return false;
		
		/**
		if (checkForSolutionInDirection(x, y, 0, 1, player, 4)) {			// Check above
			return true; 
		} else if (checkForSolutionInDirection(x, y, 0, -1, player, 4)) {	// Check below
			return true;
		} else if (checkForSolutionInDirection(x, y, -1, 0, player, 4)) {	// Check left
			return true;
		} else if (checkForSolutionInDirection(x, y, -1, 0, player, 4)) {	// Check right
			return true;
		} else if (checkForSolutionInDirection(x, y, 1, 1, player, 4)) {	// Check top right
			return true;
		} else if (checkForSolutionInDirection(x, y, -1, 1, player, 4)) {	// Check top left
			return true;
		} else if (checkForSolutionInDirection(x, y, 1, -1, player, 4)) {	// Check bottom right
			return true;
		} else if (checkForSolutionInDirection(x, y, -1, -1, player, 4)) {	// Check bottom left
			return true;
		} else {
			return false;
		}
		**/
		
	}

	public boolean isBoardFull() {
		for (int i = 0; i < this.width; i++) {
			for (int h = this.height; h > 0; h--) {
				if (board[i][h - 1] == 0) { 
					return false;
				}
			}	
		}
		return true;
	}
	// Recursive function that checks the board at that width, and height
	// Returns true if there are consecutive 'remaining' number of pieces
	// that are dx/dy away (ie dx = 1, dy = -1 means bottom right)
	// When remaining == 0, return true
	// player is either -1 or 1.. should have some enum for less confusion
	private boolean checkForSolutionInDirection(int x, int y, int dx, int dy, int player, int remaining) {
		if (remaining == 0) {
			return true;
		} else if (x >= 0 && x < this.width && y >=0 && y < this.height) {
			if (board[x][y] == player) {
				return checkForSolutionInDirection(x + dx, y + dy, dx, dy, player, remaining - 1);
			}
		}
		return false;
	}


	private boolean isColumnFull(int col) {
		for (int h = this.height; h > 0; h--) {
			if (board[col][h - 1] == 0) { 
				return false;
			}
		}
		return true;
	}

}