package codingedge.connect4.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import codingedge.connect4.logic.Game;
import codingedge.connect4.logic.GameNotActiveException;
import codingedge.connect4.logic.InvalidColumnException;
import codingedge.connect4.logic.InvalidMoveException;
import codingedge.connect4.logic.NotValidPlayerException;

public class ColumnButton extends JButton implements ActionListener {

	int columnNumber;
	
	Game game;
	
	public ColumnButton(Game g, int i) {
		super(Integer.toString(i));
//		super("WTAFFF");
		this.game = g;
		this.columnNumber = i;
//		this.setBounds(0,0,30,30);
//		this.setSize(10, 20);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	             try {
					game.makeNextMove(columnNumber);
				} catch (InvalidMoveException | GameNotActiveException
						| NotValidPlayerException | InvalidColumnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	          }          
	       });
		
//		this.setBorderPainted(false);
//		//column0Button.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		if (Integer.toString(columnNumber).equals(e.getActionCommand())) {
//			try {
//				game.makeNextMove(columnNumber);
//			} catch (InvalidMoveException | GameNotActiveException
//					| NotValidPlayerException | InvalidColumnException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		
	}

}
