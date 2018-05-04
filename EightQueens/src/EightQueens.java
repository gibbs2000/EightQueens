import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The main class representing an EightQueens window, with a header and footer
 * as well as a central grid for the chess squares to appear in. The class can
 * generate pre-written examples to the Eight Queens problem as well as
 * recursively find solutions
 * 
 * @author Sean Gibbons
 *
 */
public class EightQueens {
	/**
	 * The main JFrame that all of the elements are displayed in
	 */
	JFrame window;
	/**
	 * The three main components of the frame: header, footer, and grid. Each has
	 * different components but all are JPanels so that they work correctly within
	 * the window.
	 */
	JPanel header, footer, grid;
	/**
	 * A 2d array of ChessSquarePanels that display either white or black tiles in a
	 * pattern akin to a chessboard
	 */
	ChessSquarePanel[][] squares;
	/**
	 * A stack representing currently placed queens on the grid
	 */
	Stack<Queen> queens;
	/**
	 * A boolean that indicates whether the recursive method is running to prevent
	 * stack overflow
	 */
	boolean isRunning;

	/**
	 * Integer constants that set the dimensions for the window as well as the
	 * number of rows and columns
	 */
	public static final int HEIGHT = 1000, WIDTH = 1000, ROWS = 8, COLUMNS = 8;
	/**
	 * A constant that indicates the default background color for the frame
	 */
	private static final Color DEFAULT_BACKGROUND = Color.CYAN;
	/**
	 * Font constants to properly display the header, footer, and other relevant
	 * text
	 */
	private static final Font title = new Font("Comic Sans MS", Font.BOLD, 26),
			labelText = new Font("Comic Sans MS", Font.PLAIN, 18);
	/**
	 * The String constants used in header, footer, and other relevant text
	 */
	private static final String COPYRIGHT = "This program is the legal and intellectual property of Sean Gibbons, with graphics assistance by Mrs Kelly",
			TITLE = "Eight Queens", EXAMPLE = "Click to show an example", RESET = "Click to reset",
			RECURSE = "Click to run the recursive method";
	/**
	 * Counter fields to be used in tracking where queens have been placed so far
	 */
	private static int rowC = 0, colC = 0;

	/**
	 * The basic no args constructor to set up an EightQueens window with header,
	 * footer, and chess grid frames
	 */
	public EightQueens() {

		queens = new Stack<Queen>();

		createFrame();
		createHeader();
		createGrid();
		createFooter();

		window.add(header);
		window.add(grid);
		window.add(footer);

		window.setVisible(true);
	}

	/**
	 * A helper method that creates the overall frame for the EightQueens class
	 */
	public void createFrame() {
		window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
	}

	/**
	 * A helper method that creates the header frame to be placed incide the main
	 * window
	 */
	public void createHeader() {
		header = new JPanel();
		header.setBackground(DEFAULT_BACKGROUND);
		JLabel label = new JLabel(TITLE);
		label.setFont(title);
		header.add(label);

	}

	/**
	 * A helper method that creates the main JFramegrid of ChessSquarePanels and
	 * colors the ChessSquarePanels appropriately
	 */
	public void createGrid() {
		squares = new ChessSquarePanel[ROWS][COLUMNS];

		grid = new JPanel();
		grid.setMinimumSize(new Dimension(WIDTH, 800));
		grid.setMaximumSize(new Dimension(WIDTH, 800));
		grid.setPreferredSize(new Dimension(WIDTH, 800));
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

	/**
	 * A helper method that creates the footer JFrame with a copyright statement and
	 * three JButtons, showExample, reset, and runRecursion
	 */
	public void createFooter() {
		footer = new JPanel();
		footer.setLayout(new BorderLayout(10, 10));
		footer.setBackground(DEFAULT_BACKGROUND);
		JLabel label = new JLabel(COPYRIGHT);
		label.setFont(labelText);
		footer.add(label, BorderLayout.BEFORE_FIRST_LINE);
		JButton showExample = exampleButton();
		JButton reset = resetButton();
		JButton runRecursion = recursionButton();

		footer.add(showExample, BorderLayout.LINE_START);
		footer.add(reset, BorderLayout.CENTER);
		footer.add(runRecursion, BorderLayout.LINE_END);

	}

	/**
	 * A helper method that creates and returns a JButton which will call the
	 * exampleSolution method when clicked
	 * 
	 * @return a JButton that generates an example solution to the EightQueens
	 *         problem
	 */
	public JButton exampleButton() {
		JButton example = new JButton();
		example.setHorizontalTextPosition(SwingConstants.CENTER);
		JLabel label = new JLabel(EXAMPLE);
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

	/**
	 * A helper method that creates and returns a JButton which will call the
	 * runRecursion method when clicked
	 * 
	 * @return a JButton that recursively finds and displays a solution to the
	 *         EightQueens problem
	 */
	public JButton recursionButton() {
		JButton recursion = new JButton();
		recursion.setHorizontalTextPosition(SwingConstants.CENTER);

		JLabel label = new JLabel(RECURSE, JLabel.CENTER);
		label.setFont(labelText);
		recursion.add(label);
		recursion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isRunning) {
					isRunning = true;
					addQueens(queens);
					updateQueens();
				}

			}
		});
		return recursion;
	}

	/**
	 * A helper method that updates the appearance of the ChessSquarePanel based on
	 * the Stack of Queens
	 */
	public void updateQueens() {
		for (Queen q : queens) {
			squares[q.getColumn()][q.getRow()].setQueen(true);

		}
	}

	/**
	 * A helper method that creates and returns a JButton which will reset the board
	 * when clicked
	 * 
	 * @return a JButton that resets the board when clicked
	 */

	public JButton resetButton() {
		JButton reset = new JButton();
		reset.setHorizontalTextPosition(SwingConstants.CENTER);
		JLabel label = new JLabel(RESET);
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

	/**
	 * A helper method that generates a prewritten example solution to the Eight
	 * Queens problem and updates the panels appropriately
	 */
	public void exampleSolution() {
		Queen[] solution = { new Queen(0, 0), new Queen(4, 1), new Queen(7, 2), new Queen(5, 3), new Queen(2, 4),
				new Queen(6, 5), new Queen(1, 6), new Queen(3, 7) };
		for (Queen q : solution) {
			squares[q.getRow()][q.getColumn()].setQueen(true);
		}
	}

	/**
	 * A helper method that resets all of the ChessSquarePanels to false, making all
	 * the Queens disappear
	 */
	public void reset() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				squares[i][j].setQueen(false);

			}
		}
		queens = new Stack<Queen>();
	}

	/**
	 * A recursive method that finds a solution to the EightQueens problem and
	 * appropriately updates the queens field, a Stack of Queens
	 * 
	 * @param alreadyPlaced
	 *            the Stack of Queens that have already been placed on the grid of
	 *            ChessSquarePanels
	 * @return whether this placement is valid
	 */
	public boolean addQueens(Stack<Queen> alreadyPlaced) {
		if (alreadyPlaced.isEmpty()) {
			queens.add(new Queen(rowC, colC));
		}
		if (alreadyPlaced.size() > 8) {
			isRunning = false;
			return true;
		} else {
			Queen q = new Queen(rowC, colC);
			if (isLegal(q, queens)) {
				queens.push(q);
			}
			if (!addQueens(alreadyPlaced)) {
				queens.pop();
				rowC++;
			}
			if (rowC > ROWS) {
				rowC = 0;
			}
			if (colC > COLUMNS) {
				colC = 0;
			}
		}
		return false;

	}

	/**
	 * A helper method that checks a given Queen parameter against a Stack of other
	 * Queens in order to determine if the move will be legal. A move is legal if
	 * the Queen will not be on the same row, column, or diagonal as another Queen
	 * 
	 * @param q
	 *            the Queen to be placed
	 * @param alreadyPlaced
	 *            the Stack of Queens which the new Queen will be compared to
	 * @return whether the Queen to be placed is in a legal position
	 */
	public boolean isLegal(Queen q, Stack<Queen> alreadyPlaced) {

		for (Queen placed : alreadyPlaced) {
			if (q.getRow() == placed.getRow())
				return false;
			if (q.getColumn() == placed.getColumn())
				return false;
			if (Math.abs(q.getColumn() - q.getRow()) == Math.abs(placed.getColumn() - placed.getRow())) {
				return false;
			}
		}
		return true;
	}

}
