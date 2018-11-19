package gameoflife;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class World {

	private final Set<Position> alivePositions = new HashSet<>();

	public World(Collection<Position> livePositions) {
		this.alivePositions.addAll(livePositions);
	}

	public World() {
		// TODO Auto-generated constructor stub
	}	

	public boolean isAliveAt(Position position) {
		return alivePositions.contains(position);
	}

	public boolean willCellDieAt(Position position) {

		return countLiveNeighbors(position) < 2 || countLiveNeighbors(position) > 3;
	}	

	public boolean willSpringToLife(Position position) {
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
		
		for(Position livePosition: alivePositions) {
			
			for (Position position: livePosition.neighbors()) {
				
				examinePosition(examinedSoFar, nextgenAlivePositions, position);
				
			}
			
		}
		
		return new World(nextgenAlivePositions);
	}

	private void examinePosition(Set<Position> examinedSoFar, Set<Position> nextgenAlivePositions, Position position) {
		
		if (!examinedSoFar.contains(position)) {
			
			if(willSpringToLife(position)) {
				nextgenAlivePositions.add(position);
			}
			
			if(isAliveAt(position) && !willCellDieAt(position)) {
				nextgenAlivePositions.add(position);
			}					
			
		}
		
		examinedSoFar.add(position);
	}


}
