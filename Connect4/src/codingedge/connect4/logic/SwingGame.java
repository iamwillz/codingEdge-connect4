package codingedge.connect4.logic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import codingedge.connect4.ui.BoardPanel;
import codingedge.connect4.ui.ColumnButton;

public class SwingGame extends Game {

//	Game game;
//	Board board;
//	int playerOne, playerTwo;
	JFrame frame;
	BoardPanel boardPanel;
	JLabel text;
	JLabel messageText;
	
	public SwingGame() {
		super();
//		Board b = game.getBoard();
		boardPanel = new BoardPanel(this);
		initFrame();
	}
	
	public void showTurnText(){
		String playerStr = this.getCurrentPlayer() == Game.PLAYER_ONE_INT ? "one" : "two";	
		text.setText("Player " + playerStr + "'s turn to move. ");
	}
	
	public void showMessage(String s){
		messageText.setText(s);
	}
	
	public void clearMessage(){
		messageText.setText("");
	}
	
	private void initFrame(){
		JFrame frame = new JFrame("Connect 4");
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;
		text = new JLabel("CONNECT 4");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 8;
		frame.add(text,c);
		
		messageText = new JLabel("Click on a number below to start player one's turn");
		c.gridy = 1;
		frame.add(messageText, c);
		
		c.ipadx = 400;
		c.ipady = 330;
		c.gridx = 1;
		c.gridy = 2;
		frame.add(boardPanel, c);

		frame.setSize(450, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int i = 0; i < 7; i++) {
			final int colNum = i;
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.CENTER;
			d.gridx = i + 1;
			d.gridy = 3;
			d.ipadx = 3;
			JButton button = new ColumnButton(this, colNum);
			frame.add(button, d);
		}
	}
	
	private void showGameOver() {
		switch (currentState) {
		default:
		case GAME_OVER_DRAW:
			text.setText("Draw");
			break;
		case GAME_OVER_ONE:
			text.setText("PLAYER ONE WINS");
			break;
		case GAME_OVER_TWO:
			text.setText("PLAYER TWO WINS");
			break;
		}
	}
	
	@Override
	public void drawBoard() {
		boardPanel.paint(boardPanel.getGraphics());
	}
	
	public static void main(String[] args) {
		SwingGame game = new SwingGame();
		JLabel text = game.text;
		
		game.startGame();
		game.drawBoard();
		
		while (!game.checkIsGameOver()) {
			//game.showTurnText();
			//game.drawBoard();
		}
		
		game.drawBoard();
		game.showGameOver();
	}
}
