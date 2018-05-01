import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

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
	Stack<Queen> queens;
	boolean isRunning;

	// These are constant fields for numerical values and colors
	public static final int HEIGHT = 1000, WIDTH = 1000, ROWS = 8, COLUMNS = 8;
	private static final Color DEFAULT_BACKGROUND = Color.CYAN;
	private static final Font title = new Font("Comic Sans MS", Font.BOLD, 26);
	private static final Font labelText = new Font("Comic Sans MS", Font.PLAIN, 18);
	private static final String COPYRIGHT = "This program is the legal property of Sean Gibbons, with graphics assistance by Mrs Kelly",
			TITLE = "Eight Queens", EXAMPLE = "Click to show an example", RESET = "Click to reset",
			RECURSE = "Click to run the recursive method";

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

	public void createFrame() {
		window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
	}

	public void createHeader() {
		header = new JPanel();
		header.setBackground(DEFAULT_BACKGROUND);
		JLabel label = new JLabel(TITLE);
		label.setFont(title);
		header.add(label);

	}

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
					addQueens(queens);
					updateQueens();
				}

			}
		});
		return recursion;
	}

	private void updateQueens() {
		for (Queen q : queens) {
			squares[q.getColumn()][q.getRow()].setQueen(true);
			;
		}
	}

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

	public boolean addQueens(Stack<Queen> alreadyPlaced) {
		isRunning = true;
		if (alreadyPlaced.isEmpty()) {
			queens.add(new Queen(0, 0));
		}
		if (alreadyPlaced.size() > 8) {
			isRunning = false;
			return true;// base case
		} else {
			for (int i = alreadyPlaced.peek().getRow(); i < ROWS; i++) {
				for (int j = alreadyPlaced.peek().getColumn(); j < ROWS; j++) {
					Queen q = new Queen(i, j);
					if (isLegal(q, alreadyPlaced)) {
						alreadyPlaced.add(q);
					}
				}
			}
		}
		return isRunning;
	}

	public boolean isLegal(Queen q, Stack<Queen> alreadyPlaced) {

		return true;
	}

}
