package gameoflife;

import java.util.HashSet;
import java.util.Set;

public class World {
	
	private Set<Position> alivePositions = new HashSet<>();

	public void setAlive(Position position) {
		alivePositions.add(position);		
	}

	public boolean isAliveAt(Position position) {
		return alivePositions.contains(position);
	}

}
