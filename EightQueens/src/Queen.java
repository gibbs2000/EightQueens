/**
 * 
 * @author Sean Gibbons
 *
 */
public class Queen {
	int row, column;

	public Queen() {
		row = 0;
		column = 0;
	}

	public Queen(int r, int c) {
		row = r;
		column = c;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
