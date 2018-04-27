import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Sean Gibbons
 *
 */
public class EightQueens {
	JFrame window;
	JPanel header, footer, grid;
	ChessSquarePanel[][] squares;
	ArrayList<Queen> queens;

	// These are constant fields for numerical values and colors
	final int HEIGHT = 800, WIDTH = 800, ROWS = 8, COLUMNS = 8;
	private static final Color DEFAULT_BACKGROUND = Color.CYAN;

	public EightQueens() {
		createFrame();

		createHeader();
		createGrid();
		createFooter();

		window.add(header);
		window.add(grid);
		window.add(footer);

		window.setVisible(true);
	}

	public void createFrame() {
		window = new JFrame("Practicing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
	}

	public void createGrid() {
		squares = new ChessSquarePanel[ROWS][COLUMNS];

		grid = new JPanel();
		grid.setLayout(new GridLayout(ROWS, COLUMNS));
		int x = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (x++ % 2 == 0)
					squares[i][j] = new ChessSquarePanel(Color.GRAY, false);

				else
					squares[i][j] = new ChessSquarePanel(Color.WHITE, false);
				grid.add(squares[i][j]);
			}
			x++;
		}

	}

	public void createFooter() {
		footer = new JPanel();
		footer.setMinimumSize(new Dimension(WIDTH, 10));
		footer.setMaximumSize(new Dimension(WIDTH, 50));
		footer.setPreferredSize(new Dimension(WIDTH, 40));
		footer.setBackground(DEFAULT_BACKGROUND);
		footer.add(new JLabel("This program copyright Sean Gibbons, with graphics assistance by Mrs Kelly"));
		JButton showExample = new JButton();
		JButton runRecursion = new JButton();

		footer.add(showExample);
		footer.add(runRecursion);

	}

	public void createHeader() {
		header = new JPanel();
		header.setMinimumSize(new Dimension(WIDTH, 10));
		header.setMaximumSize(new Dimension(WIDTH, 50));
		header.setPreferredSize(new Dimension(WIDTH, 40));
		header.setBackground(DEFAULT_BACKGROUND);
		header.add(new JLabel("Now Displaying the Eight Queens Problem"));
	}

	public void exampleSolution() {
		Queen[] solution = { new Queen(0, 0), new Queen(4, 1), new Queen(7, 2), new Queen(5, 3), new Queen(2, 4),
				new Queen(6, 5), new Queen(1, 6), new Queen(3, 7) };
		for (Queen q : solution) {
			squares[q.getRow()][q.getColumn()].setQueen(true);
		}

	}
}
