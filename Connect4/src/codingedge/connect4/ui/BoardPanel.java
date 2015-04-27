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

public class BoardPanel extends JPanel {

	Game game;
	Board board;
	int playerOne, playerTwo;

	static final int CIRCLE_DISTANCE = 45;
	static final int CIRCLE_RADIUS = 35;

	JFrame frame;
	
	public BoardPanel() {

	}

	public BoardPanel(Game g) {
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
}
