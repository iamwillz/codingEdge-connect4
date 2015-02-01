package codingedge.connect4.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import codingedge.connect4.logic.Board;
import codingedge.connect4.logic.Game;
import codingedge.connect4.logic.GameNotActiveException;
import codingedge.connect4.logic.InvalidColumnException;
import codingedge.connect4.logic.InvalidMoveException;
import codingedge.connect4.logic.NotValidPlayerException;

public class DrawGame extends JPanel {

	Game game;
	Board board;
	int playerOne, playerTwo;

	static final int CIRCLE_DISTANCE = 45;
	static final int CIRCLE_RADIUS = 35;

	public DrawGame() {

	}

	public DrawGame(Game g) {
		this.game = g;
		this.board = g.getBoard();
		this.playerOne = g.getPlayerOneInt();
		this.playerTwo = g.getPlayerTwoInt();
	}

	@Override
	public void paint(Graphics g) {
		int[][] boardArray = board.getBoardArray();

		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < board.getWidth(); i++) {
			int maxHeight = board.getHeight();
			for (int j = 0; j < maxHeight; j++) {
				if (boardArray[i][j] == playerOne) {
					g2d.setColor(Color.RED);
				} else if (boardArray[i][j] == playerTwo) {
					g2d.setColor(Color.BLACK);
				} else {
					g2d.setColor(Color.WHITE);
				}
				g2d.fillOval(i * CIRCLE_DISTANCE, (maxHeight - j)
						* CIRCLE_DISTANCE, CIRCLE_RADIUS, CIRCLE_RADIUS);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Connect 4");
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		Game game = new Game();
		Board b = game.getBoard();
		DrawGame dg = new DrawGame(game);
		game.startGame();

		boolean playerOne = true;

		c.fill = GridBagConstraints.CENTER;
		JLabel text = new JLabel("CONNECT 4");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 8;
		frame.add(text,c);
		
		c.ipadx = 400;
		c.ipady = 330;
		c.gridx = 0;
		c.gridy = 1;

		frame.add(dg, c);
		for (int i = 0; i < b.getWidth(); i++) {
			final int colNum = i;
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.CENTER;
			d.gridx = i;
			d.gridy = 2;
			d.ipadx = 3;
			// JButton button = new JButton("");
			JButton button = new ColumnButton(game, colNum);
			
			frame.add(button, d);
			
		}

		frame.setSize(450, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println("lol\n");
		// game.drawBoard();
		while (!game.checkIsGameOver()) {
			String playerStr = game.getCurrentPlayer() == Game.PLAYER_ONE_INT ? "one" : "two";
			
			text.setText("Player " + playerStr + "'s turn to move. ");
			
			dg.paint(dg.getGraphics());
			if (game.getCurrentPlayer() == Game.PLAYER_ONE_INT) {

			} else {

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

		// reader.close();
	}
}
