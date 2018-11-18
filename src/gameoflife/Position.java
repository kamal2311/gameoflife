package gameoflife;

import java.util.Objects;

public class Position {

	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}	
	
	@Override
	public boolean equals(Object obj) {
		
		Position that = (Position) obj;
		
		return this.row == that.row && this.col == that.col; 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}

}
