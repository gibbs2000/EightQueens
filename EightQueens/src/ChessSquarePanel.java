import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * A class representing an individual panel on a ChessBoard. The two main fields
 * are the background color and whether or not it has a queen on it
 * 
 * @author Sean Gibbons
 *
 */
@SuppressWarnings("serial")
public class ChessSquarePanel extends JPanel {

	/**
	 * The boolean field that stores whether or not this panel has a queen
	 */
	private boolean isQueen;
	/**
	 * The field for the background color of the ChessSquarePanel
	 */
	private Color backColor;
	/**
	 * A constant int value for the size of the label to be displayed
	 */
	private static final int FONTSIZE = 30;
	/**
	 * A constant for the font of the label to be displayed
	 */
	private static final Font title = new Font("Comic Sans MS", Font.BOLD, FONTSIZE);

	/**
	 * The default no args constructor that sets the background color and isQueen
	 * methods to basic values
	 */
	ChessSquarePanel() {
		backColor = Color.BLUE;
		isQueen = false;
	}

	/**
	 * A constructor that sets the fields of the ChessSquarePanel to given
	 * parameters
	 * 
	 * @param c
	 *            the new backgroundColor
	 * @param q
	 *            the boolean on whether or not this ChessSquarePanel contains a
	 *            Queen
	 */
	ChessSquarePanel(Color c, boolean q) {
		backColor = c;
		isQueen = q;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setBackground(backColor);

		// removeAll(); // Someone mentioned panels not updating properly, this
		// may help, but does not seem necessary
		// x and y center the String, adjust as necessary

		if (isQueen) {
			g.setFont(title);
			g.setColor(Color.RED);
			int x = (this.getWidth() / 2) - FONTSIZE / 4;
			// - letter.length()/2;
			int y = (this.getHeight() / 2) + FONTSIZE / 4;
			g.drawString("Q", x, y);
		}
	}

	/**
	 * Sets the background color of the ChessSquarePanel to a given parameter
	 * 
	 * @param c
	 *            the new background color
	 */
	public void setBackColor(Color c) {
		backColor = c;
		repaint(); // forces paintComponent to execute
	}

	/**
	 * Sets the state of the isQueen to a given boolean parameter
	 * 
	 * @param q
	 *            the boolean parameter to which isQueen will be assigned
	 */
	public void setQueen(boolean q) {
		isQueen = q;
		repaint();
	}
}
