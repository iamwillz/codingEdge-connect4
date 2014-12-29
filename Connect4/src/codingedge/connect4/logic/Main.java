package codingedge.connect4.logic;

import java.util.Scanner;

public class Main {

	public static void drawBoard(Board board) {
		String output = "";

		int[][] boardArr = board.getBoardArray();

		drawIntBoard(boardArr, board.getWidth(), board.getHeight());
	}

	public static void drawIntBoard(int[][] boardArr, int width, int height) {
		String header = "";
		for (int x = 0; x < width; x++) {
			header = header.concat("  " + x + "  ");
		}
		System.out.println(header);
		for (int y = height - 1; y >= 0; y--) {
			String output = "";
			for (int x = 0; x < width; x++) {
				output = output.concat("| ");
				switch (boardArr[x][y]) {
				case 0:
				default:
					output = output.concat(" ");
					break;
				case 1:
					output = output.concat("o");
					break;
				case -1:
					output = output.concat("x");
					break;
				}
				output = output.concat(" |");
			}

			System.out.println(output);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();

		game.startGame();

		Scanner reader = new Scanner(System.in);

		boolean playerOne = true;

		while (!game.checkIsGameOver()) {
			String playerStr = playerOne ? "one" : "two";
			System.out.println("Player " + playerStr
					+ "'s turn to move. Enter a column number between 0 and 6");

			int playerMove;
			if (reader.hasNextInt()) {
				playerMove = reader.nextInt();
				try {
					game.makeMove(playerMove, playerOne ? 1 : -1);
					playerOne = !playerOne;

					// System.out.println(test.length);
					System.out.println("\n\n\n\n\n\n\n\n\n\n");
					drawBoard(game.getBoard());
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

		System.out.println("GAME OVER");
		switch (game.currentState) {
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
		
		reader.close();
	}

}
