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
		
		for(Position liveCell: alivePositions) {
			
			for (Position neighbor: liveCell.neighbors()) {
				
				if (!examinedSoFar.contains(neighbor)) {
					
					if(willSpringToLife(neighbor)) {
						nextgenAlivePositions.add(neighbor);
					}
					
					if(isAliveAt(neighbor) && !willCellDieAt(neighbor)) {
						nextgenAlivePositions.add(neighbor);
					}					
					
				}
				
				examinedSoFar.add(neighbor);
				
			}
			
		}
		
		return new World(nextgenAlivePositions);
	}


}
