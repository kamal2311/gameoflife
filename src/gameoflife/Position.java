package gameoflife;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Position {

	private int row;
	private int col;

	private Position(int row, int col) {
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

	public static Position withXY(int x, int y) {
		return new Position(x, y);
	}

	public Collection<Position> neighbors() {

		Collection<Position> neighbors = new ArrayList<>();

		neighbors.add(Position.withXY(row - 1, col - 1));
		neighbors.add(Position.withXY(row - 1, col));
		neighbors.add(Position.withXY(row - 1, col + 1));

		neighbors.add(Position.withXY(row, col - 1));
		neighbors.add(Position.withXY(row, col + 1));

		neighbors.add(Position.withXY(row + 1, col - 1));
		neighbors.add(Position.withXY(row + 1, col));
		neighbors.add(Position.withXY(row + 1, col + 1));

		return neighbors;
	}

}
