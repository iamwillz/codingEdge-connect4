package codingedge.connect4.logic;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import codingedge.connect4.ui.DrawGame;

public class Main {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();

		game.startGame();

		Scanner reader = new Scanner(System.in);

		JFrame frame = new JFrame("Connect 4");
		Board b = game.getBoard();
		JButton column0Button = new JButton("0");
		JButton column1Button = new JButton("1");
		JButton column2Button = new JButton("2");
		JButton column3Button = new JButton("3");
		JButton column4Button = new JButton("4");
		JButton column5Button = new JButton("5");
		JButton column6Button = new JButton("6");
		
		boolean playerOne = true;
		
		for (int i = 0; i < b.getWidth(); i++) {
		       final int colNum = i;
				JButton button = new JButton() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (e.getActionCommand().equals(Integer.toString(colNum))){
		       			
						}
					}
				};
		}
		
		column0Button.setBounds(0,0,30,300);
		column0Button.setActionCommand("0");
		column0Button.setVisible(false);
		frame.add(column0Button);
		//frame.add(new DrawGame(b ,1,2));
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
		//game.drawBoard();
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
					//game.drawBoard();
					frame.paint(frame.getGraphics());
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
