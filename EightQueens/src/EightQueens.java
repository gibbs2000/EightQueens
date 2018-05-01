import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	final int HEIGHT = 1000, WIDTH = 1000, ROWS = 8, COLUMNS = 8, GRID_SIZE = 800;
	private static final Color DEFAULT_BACKGROUND = Color.CYAN;
	private static final Font title = new Font("Comic Sans MS", Font.BOLD, 26);
	private static final Font labelText = new Font("Comic Sans MS", Font.PLAIN, 18);
	private static final String whiteTexture = "wht.jpg";
	private static final String blackTexture = "blk.jpeg";

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

	public void createHeader() {
		header = new JPanel();
		header.setBackground(DEFAULT_BACKGROUND);
		JLabel label = new JLabel("EIGHT QUEENS");
		label.setFont(title);
		header.add(label);

	}

	public void createGrid() {
		squares = new ChessSquarePanel[ROWS][COLUMNS];

		grid = new JPanel();
		grid.setMinimumSize(new Dimension(WIDTH, GRID_SIZE));
		grid.setMaximumSize(new Dimension(WIDTH, GRID_SIZE));
		grid.setPreferredSize(new Dimension(WIDTH, GRID_SIZE));
		grid.setLayout(new GridLayout(ROWS, COLUMNS));
		int x = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (x++ % 2 == 0) {
					squares[i][j] = new ChessSquarePanel(blackTexture, false);
					// squares[i][j] = new ChessSquarePanel(Color.DARK_GRAY, false);
				} else {
					squares[i][j] = new ChessSquarePanel(whiteTexture, false);
					// squares[i][j] = new ChessSquarePanel(Color.WHITE, false);
				}
				grid.add(squares[i][j]);
			}
			x++;
		}

	}

	public void createFooter() {
		footer = new JPanel();
		footer.setLayout(new BorderLayout(10, 10));
		footer.setBackground(DEFAULT_BACKGROUND);
		JLabel label = new JLabel("This program copyright Sean Gibbons, with graphics assistance by Mrs Kelly");
		label.setFont(labelText);
		footer.add(label, BorderLayout.BEFORE_FIRST_LINE);
		JButton showExample = exampleButton();
		JButton reset = resetButton();
		JButton runRecursion = recursionButton();

		footer.add(showExample, BorderLayout.LINE_START);
		footer.add(reset, BorderLayout.CENTER);
		footer.add(runRecursion, BorderLayout.LINE_END);

	}

	public JButton exampleButton() {
		JButton example = new JButton();
		example.setHorizontalTextPosition(SwingConstants.CENTER);
		JLabel label = new JLabel("Click to show an example");
		label.setFont(labelText);
		example.add(label);
		example.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exampleSolution();
			}
		});
		return example;

	}

	public JButton recursionButton() {
		JButton recursion = new JButton();
		recursion.setHorizontalTextPosition(SwingConstants.CENTER);

		JLabel label = new JLabel("Click to run the recursive method", JLabel.CENTER);
		label.setFont(labelText);
		recursion.add(label);
		recursion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO write the recursive method
			}
		});
		return recursion;
	}

	public boolean addQueens(ArrayList<Queen> alreadyPlaced) {
		if (alreadyPlaced.isEmpty()) {

		}
		if (alreadyPlaced.size() > 8) {
			return true;// base case
		} else {

			return false;

		}
	}

	public JButton resetButton() {
		JButton reset = new JButton();
		reset.setHorizontalTextPosition(SwingConstants.CENTER);
		JLabel label = new JLabel("Click to reset");
		label.setFont(labelText);
		reset.add(label);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		return reset;
	}

	public void exampleSolution() {
		Queen[] solution = { new Queen(0, 0), new Queen(4, 1), new Queen(7, 2), new Queen(5, 3), new Queen(2, 4),
				new Queen(6, 5), new Queen(1, 6), new Queen(3, 7) };
		for (Queen q : solution) {
			squares[q.getRow()][q.getColumn()].setQueen(true);
		}
	}

	public void reset() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				squares[i][j].setQueen(false);
			}
		}
	}
}
