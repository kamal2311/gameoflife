package gameoflife;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class World {

	private final Set<Position> alivePositions = new HashSet<>();

	private World(Collection<Position> livePositions) {
		this.alivePositions.addAll(livePositions);
	}

	public boolean isAliveAt(Position position) {
		return alivePositions.contains(position);
	}

	public boolean willDieAt(Position position) {

		return isUnderPopulated(position) || isOverPopulated(position);
	}

	private boolean isOverPopulated(Position position) {
		return countLiveNeighbors(position) > 3;
	}

	private boolean isUnderPopulated(Position position) {
		return countLiveNeighbors(position) < 2;
	}

	public boolean willSpringToLife(Position position) {
		return isPerfectlyPopulated(position);
	}

	private boolean isPerfectlyPopulated(Position position) {
		return countLiveNeighbors(position) == 3;
	}

	private int countLiveNeighbors(Position position) {

		int count = 0;

		for (Position pos : position.neighbors()) {
			if (alivePositions.contains(pos)) {
				count++;
			}
		}

		return count;
	}

	public World nextGeneration() {

		Set<Position> examinedSoFar = new HashSet<>();

		Set<Position> nextgenAlivePositions = new HashSet<>();

		for (Position livePosition : alivePositions) {

			for (Position position : livePosition.neighbors()) {

				examinePosition(examinedSoFar, nextgenAlivePositions, position);

			}

		}

		return new World(nextgenAlivePositions);
	}

	private void examinePosition(Set<Position> examinedSoFar, Set<Position> nextgenAlivePositions, Position position) {

		if (!examinedSoFar.contains(position)) {

			if (willSpringToLife(position)) {
				nextgenAlivePositions.add(position);
			}

			if (isAliveAt(position) && !willDieAt(position)) {
				nextgenAlivePositions.add(position);
			}

		}

		examinedSoFar.add(position);
	}

	public static World withLivePositions(Collection<Position> livePositions) {
		return new World(livePositions);
	}

	@Override
	public String toString() {
		
		return displayRange(5,5);

	}
	
	public String displayRange(int x, int y) {
		
		StringBuilder representation = new StringBuilder();

		for (int i = -x; i < x ; i++) {
			for (int j = -y; j < y; j++) {

				if (isAliveAt(Position.withXY(i, j))) {
					representation.append("* ");
				} else {
					representation.append("- ");
				}

			}
			representation.append("\n");
		}

		return representation.toString();
	}

}
