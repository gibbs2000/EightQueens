import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * @author Sean Gibbons
 *
 */
@SuppressWarnings("serial")
public class ChessSquarePanel extends JPanel {

	// These fields are used whenever PaintComponent is executed to update the panel
	private boolean isQueen;
	private Color backColor;
	private static final int FONTSIZE = 30;

	ChessSquarePanel() {
		backColor = Color.BLUE;
		isQueen = false;
	}

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
			g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
			g.setColor(Color.RED);
			int x = (this.getWidth() / 2) - FONTSIZE / 4; // - letter.length()/2;
			int y = (this.getHeight() / 2) + FONTSIZE / 4;
			g.drawString("Q", x, y);
		}
	}

	public void setBackColor(Color c) {
		backColor = c;
		repaint(); // forces paintComponent to execute
	}

	public void setQueen(boolean q) {
		isQueen = q;
		repaint();
	}
}
