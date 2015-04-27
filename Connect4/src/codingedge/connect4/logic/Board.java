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


	
	// Returns the row in which it was placed in
	// Else -1, if the column is full
	public int addToColumn(int column, int player) throws InvalidColumnException {
		if (column < 0 || column >= width) {
			throw new InvalidColumnException("Column number " + column + " was invalid");
		} else if (!isColumnFull(column)) {
			for (int h = 0; h < this.height; h++) {
				if (board[column][h] == 0) {
					board[column][h] = player;
					return h;
				}
			}
		}
		return -1;
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
	// Return PLAYER_ONE if player one won
	// Return PLAYER_TWO if player two won
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
		String s = "";
		for (int col = 0; col < this.width; col++){
			s = s.concat(Integer.toString(board[col][y]));
		}
		if (checkForSolutionFromString(s, player)) {
			return true;
		}

		s = "";
		for (int row = 0 ; row < this.height; row++){
			s = s.concat(Integer.toString(board[x][row]));
		}
		if (checkForSolutionFromString(s, player)) {
			return true;
		}
		s = Integer.toString(board[x][y]);
		for (int row = y + 1, col = x + 1; row < this.height && col < this.width; row++, col++){
			s = s.concat(Integer.toString(board[col][row]));
		}

		for (int row = y - 1, col = x - 1; row >= 0 && col >= 0; row--, col--){
			s = Integer.toString(board[col][row]).concat(s);
		}
		if (checkForSolutionFromString(s, player)) {
			return true;
		}
		
		s = Integer.toString(board[x][y]);
		for (int row = y - 1, col = x + 1; row >= 0 && col < this.width; row--, col++){
			s = s.concat(Integer.toString(board[col][row]));
		}

		for (int row = y + 1, col = x - 1; row < this.height && col >= 0; row++, col--){
			s = s.concat(Integer.toString(board[col][row]));
		}
		if (checkForSolutionFromString(s, player)) {
			return true;
		}
		/**
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				if (dx != 0 || dy != 0) {
					if (checkForSolutionInDirection(x, y, dx, dy, player, 4)) {
						return true;
					}
				}
			}
		} **/
		
		return false;		
	}
	
	private boolean checkForSolutionFromString(String s, int player){
		String winner = new String(new char[4]).replace("\0", Integer.toString(player));
		if (s.contains(winner)) {
			return true;
		}
		return false;
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

	private boolean isColumnFull(int col) {
		for (int h = this.height; h > 0; h--) {
			if (board[col][h - 1] == 0) { 
				return false;
			}
		}
		return true;
	}

	public void drawBoard(int playerOne, int playerTwo) {
		int[][] boardArr = this.getBoardArray();

		drawIntBoard(boardArr, this.getWidth(), this.getHeight(), playerOne, playerTwo);
	}

	private void drawIntBoard(int[][] boardArr, int width, int height, int playerOne, int playerTwo) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
		String header = "";
		for (int x = 0; x < width; x++) {
			header = header.concat("  " + x + "  ");
		}
		System.out.println(header);
		for (int y = height - 1; y >= 0; y--) {
			String output = "";
			for (int x = 0; x < width; x++) {
				output = output.concat("| ");
				int boardValue = boardArr[x][y];
				if (boardValue == playerOne){
					output = output.concat("o");
				} else if (boardValue == playerTwo){
					output = output.concat("x");
				} else{
					output = output.concat(" ");
				}
				output = output.concat(" |");
			}

			System.out.println(output);
		}
	}

}
