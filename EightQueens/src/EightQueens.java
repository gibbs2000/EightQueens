import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
	 * An ArrayList representing currently placed queens on the grid
	 */
	ArrayList<Queen> curSolution;
	/**
	 * A boolean that indicates whether the recursive method is running to prevent
	 * stack overflow
	 */
	boolean isRunning = false;

	/**
	 * An ArrayList that represents all possible solutions to the EightQueens
	 * problem
	 */

	public static ArrayList<ArrayList<Queen>> solutions = new ArrayList<ArrayList<Queen>>();
	/**
	 * Integer constants that set the dimensions for the window as well as the
	 * number of rows and columns
	 */
	public static final int HEIGHT = 1000, WIDTH = 1000, ROWS = 8, COLUMNS = 8, TIME = 1000;
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
	private static final String TITLE = "Eight Queens", EXAMPLE = "Click to show an example", RESET = "Click to reset",
			RECURSE = "Click to run the recursive method", SOLUTION = "Solution Number: ";
	/**
	 * Counter fields to be used in tracking where queens have been placed so far
	 */
	private static int curSolutionNumber = 1, startColumn = 0;

	/**
	 * The basic no-args constructor to set up an EightQueens window with header,
	 * footer, and chess grid frames
	 */
	public EightQueens() {

		curSolution = new ArrayList<Queen>();

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
		JLabel label = new JLabel(SOLUTION + curSolutionNumber);
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
					try {
						generateSolutions();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		return recursion;
	}

	/**
	 * A helper method that updates the appearance of the ChessSquarePanel based on
	 * a parameter of an ArrayList of Queens
	 * 
	 * @param posSolution
	 *            an ArrayList that serves as one of the possible solutions
	 */
	public void updateQueens(ArrayList<Queen> posSolution) {
		for (Queen q : posSolution) {
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
		ArrayList<Queen> solution = new ArrayList<Queen>(Arrays.asList(new Queen(0, 0), new Queen(4, 1),
				new Queen(7, 2), new Queen(5, 3), new Queen(2, 4), new Queen(6, 5), new Queen(1, 6), new Queen(3, 7)));
		updateQueens(solution);
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

		// Resets both the current solution, the list of solutions, and the current
		// Solution Number
		curSolution = new ArrayList<Queen>();
		solutions = new ArrayList<ArrayList<Queen>>();
		curSolutionNumber = 1;
		startColumn = 0;
	}

	/**
	 * @throws InterruptedException
	 * 
	 */
	public void generateSolutions() throws InterruptedException {
		while (startColumn < 8) {
			ArrayList<Queen> temp = new ArrayList<Queen>();
			if (addQueens(temp)) {
				curSolution = temp;
				updateQueens(curSolution);
				curSolutionNumber++;
				Thread.sleep(TIME);
			}
		}
	}

	/**
	 * A recursive method that finds a solution to the EightQueens problem
	 * 
	 * @param alreadyPlaced
	 *            the ArrayList of Queens that have already been placed on the grid
	 *            of ChessSquarePanels
	 * @return whether this placement is valid
	 */
	public boolean addQueens(ArrayList<Queen> alreadyPlaced) {
		// because of the way that this code works, the solution is found row by row and
		// therefore the size of the array is the same as the current row
		if (alreadyPlaced.size() >= ROWS) {
			return true;// if the 8th row is reached, then it is a solution (yay)

		} else if (alreadyPlaced.isEmpty()) {
			// if this is the first Queen to be placed, change the starting position
			alreadyPlaced.add(new Queen(0, startColumn));
			return addQueens(alreadyPlaced);
		} else {
			for (int i = 0; i < COLUMNS; i++) {
				// Tries to add a Queen at the next Row and column
				Queen q = new Queen(alreadyPlaced.size(), i);
				if (isLegal(q, alreadyPlaced)) {
					alreadyPlaced.add(q);
					return addQueens(alreadyPlaced);
				}
			}
			// if there was not a solution in this row, remove the most recent one and try
			// again
			alreadyPlaced.remove(alreadyPlaced.size() - 1);
			return false;

		}

	}

	/**
	 * A helper method that checks a given Queen parameter against a ArrayList of
	 * other Queens in order to determine if the move will be legal. A move is legal
	 * if the Queen will not be on the same row, column, or diagonal as another
	 * Queen
	 * 
	 * @param q
	 *            the Queen to be placed
	 * @param alreadyPlaced
	 *            the ArrayList of Queens which the new Queen will be compared to
	 * @return whether the Queen to be placed is in a legal position
	 */
	public boolean isLegal(Queen q, ArrayList<Queen> alreadyPlaced) {

		for (Queen placed : alreadyPlaced) {
			if (q.getRow() == placed.getRow())
				// check row
				return false;
			if (q.getColumn() == placed.getColumn())
				// check column
				return false;
			if (Math.abs(q.getColumn() - q.getRow()) == Math.abs(placed.getColumn() - placed.getRow())) {
				// check diagonal
				return false;
			}
		}
		return true;
	}

}
