package codingedge.connect4.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import codingedge.connect4.logic.Game;
import codingedge.connect4.logic.GameNotActiveException;
import codingedge.connect4.logic.InvalidColumnException;
import codingedge.connect4.logic.InvalidMoveException;
import codingedge.connect4.logic.NotValidPlayerException;
import codingedge.connect4.logic.SwingGame;

public class ColumnButton extends JButton implements ActionListener {

	int columnNumber;

	SwingGame game;

	public ColumnButton(SwingGame g, int i) {
		super(Integer.toString(i));
		this.game = g;
		this.columnNumber = i;
		// this.setBounds(0,0,30,30);
		// this.setSize(10, 20);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.makeNextMove(columnNumber);
					game.drawBoardAndUpdateText();
				} catch (InvalidMoveException | GameNotActiveException
						| NotValidPlayerException | InvalidColumnException ex) {
					game.showMessage(ex.getMessage());
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
