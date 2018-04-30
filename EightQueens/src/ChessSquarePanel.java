import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	private ImageIcon image;
	private static final int FONTSIZE = 30;
	private static final Font title = new Font("Comic Sans MS", Font.BOLD, FONTSIZE);

	ChessSquarePanel() {
		backColor = Color.BLUE;
		isQueen = false;
	}

	ChessSquarePanel(Color c, boolean q) {
		backColor = c;
		isQueen = q;
	}
	
	ChessSquarePanel(String s, boolean q){
		image = new ImageIcon(s);
		JLabel label = new JLabel("", image, JLabel.CENTER);
		this.add(label, BorderLayout.CENTER);
				
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setBackground(backColor);
		if (isQueen) {
			JLabel label = new JLabel("Q");
			label.setFont(title);
			label.setBackground(Color.RED);
			this.add(label, BorderLayout.CENTER);
			
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
