package codingedge.connect4.logic;

import java.util.Scanner;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();

		game.startGame();

		Scanner reader = new Scanner(System.in);

		boolean playerOne = true;
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
		game.drawBoard();
		while (!game.checkIsGameOver()) {
			String playerStr = playerOne ? "one" : "two";
			System.out.println("Player " + playerStr
					+ "'s turn to move. Enter a column number between 0 and 6");

			int playerMove;
			if (reader.hasNextInt()) {
				playerMove = reader.nextInt();
				try {
					if (playerOne) {
						game.makePlayerOneMove(playerMove);
					} else {
						game.makePlayerTwoMove(playerMove);
					}
					playerOne = !playerOne;

					// System.out.println(test.length);
					System.out.println("\n\n\n\n\n\n\n\n\n\n");
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
