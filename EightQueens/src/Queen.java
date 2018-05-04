/**
 * A small class representing a Queen with row and column parameters
 * 
 * @author Sean Gibbons
 *
 */
public class Queen {
	/**
	 * The row and column values of the Queen object
	 */
	int row, column;

	/**
	 * Default no-args constructor makes a Queen of row and column of 0
	 */
	public Queen() {
		row = 0;
		column = 0;
	}

	/**
	 * Two args constructor that makes a Queen with the given row and column
	 * 
	 * @param r
	 *            the row parameter
	 * @param c
	 *            the column parameter
	 */
	public Queen(int r, int c) {
		row = r;
		column = c;
	}

	/**
	 * Returns the row value of this Queen
	 * 
	 * @return the row value of this Queen
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row value to a given int value
	 * 
	 * @param row
	 *            the new value to which the row will be changed
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Returns the column value of this Queen
	 * 
	 * @return the column value of this Queen
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Sets the column value to a given int value
	 * 
	 * @param column
	 *            the new value to which the row will be changed
	 */
	public void setColumn(int column) {
		this.column = column;
	}

}
