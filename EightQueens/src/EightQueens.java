import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EightQueens {
	JFrame window;
	JPanel header, footer, grid;
	ChessSquarePanel[][] squares;
	ArrayList<Queen> queens;
	// These are constant fields for numerical values
	final int HEIGHT = 400, WIDTH = 400, ROWS = 8, COLUMNS = 8;

	public EightQueens() {
		createFrame();

		createHeader();
		createFooter();
		createGrid();

		window.add(header);
		window.add(footer);
		window.add(grid);
		window.setVisible(true);
	}

	private void createFrame() {
		window = new JFrame("Practicing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		// could set min, max, and preferred dimensions, I think
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
	}

	private void createGrid() {
		// TODO Auto-generated method stub
	}

	private void createFooter() {
		// TODO Auto-generated method stub
	}

	private void createHeader() {
		// TODO Auto-generated method stub
	}
}
