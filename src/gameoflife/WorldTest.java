package gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class WorldTest {

	

	@Test
	public void should_initialize() throws Exception {

		World world = new World();
		assertThat(world).isNotNull();

	}

	@Test
	public void should_initialize_with_a_given_set_of_live_cells() throws Exception {

		// 0 1 1 1
		// 1 0 1 1
		// 0 0 0 0
		World world = setupWorld();

		assertThat(world).isNotNull();

	}

	private World setupWorld() {
		
		// 0 1 1 1
		// 1 0 1 1
		// 0 0 0 1
		
		Collection<Position> livePositions = new ArrayList<>();

		livePositions.add(Position.withXY(0, 1));
		livePositions.add(Position.withXY(0, 2));
		livePositions.add(Position.withXY(0, 3));
		livePositions.add(Position.withXY(1, 0));
		livePositions.add(Position.withXY(1, 2));
		livePositions.add(Position.withXY(1, 3));
		livePositions.add(Position.withXY(2, 3));
		
				
		World world = new World(livePositions);
		return world;
	}

	
	@Test
	public void should_be_able_to_report_if_a_cell_is_alive_at_a_position() throws Exception {

		World world = setupWorld();
		
		assertThat(world.isAliveAt(Position.withXY(0, 1))).isTrue();

	}

	@Test
	public void should_be_able_to_report_if_a_cell_is_not_alive_at_a_position() throws Exception {

		World world = new World();
		Position nonAlivePosition = Position.withXY(0, 0);

		assertThat(world.isAliveAt(nonAlivePosition)).isFalse();
	}
	

	@Test
	public void should_answer_if_a_cell_will_die_due_to_loneliness_in_next_gen() throws Exception {

		World world = setupWorld();

		assertThat(world.willCellDieAt(Position.withXY(1, 0))).isTrue();
		

	}

	@Test
	public void should_answer_if_a_live_cell_will_die_due_to_overpopulation_in_next_gen() throws Exception {

		World world = setupWorld();
		Position overcrowdedPosition = Position.withXY(1, 2);
		
		assertThat(world.willCellDieAt(overcrowdedPosition)).isTrue();

	}

	@Test
	public void should_answer_if_a_live_cell_will_survive_in_next_gen() throws Exception {


		World world = setupWorld();
		
		Position stableCell = Position.withXY(0, 3);
		
		assertThat(world.willCellDieAt(stableCell)).isFalse();

	}

	@Test
	public void should_answer_if_a_dead_cell_will_spring_to_life_in_next_gen() throws Exception {

		// 0 1 1 1
		// 1 0 1 1
		// 0 0 0 1
		World world = setupWorld();

		Position deadCell = Position.withXY(2, 2);
		
		assertThat(world.willSpringToLife(deadCell)).isTrue();

	}
	
	@Test
	public void should_produce_next_generation_as_per_rules() throws Exception {
		
		// 0 1 1 1
		// 1 0 1 1
		// 0 0 0 1
				
		World world = setupWorld();
		
		World nextGenWorld = world.nextGeneration();
		
		// 0 | 0 0 1 0 | 0
	    // --------------------- 
		// 0 | 0 1 0 1 | 0
		// 0 | 0 0 0 0 | 1
		// 0 | 0 0 1 1 | 0		
		//------------------------
		// 0 | 0 0 0 0 | 0

		
		assertThat(nextGenWorld.isAliveAt(Position.withXY(-1, 2))).isTrue();
		//-------------------------------------------------------------------
		assertThat(nextGenWorld.isAliveAt(Position.withXY(0, 0))).isFalse();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(0, 1))).isTrue();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(0, 2))).isFalse();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(0, 3))).isTrue();
		
		assertThat(nextGenWorld.isAliveAt(Position.withXY(1, 0))).isFalse();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(1, 1))).isFalse();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(1, 2))).isFalse();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(1, 3))).isFalse();
		//-------------------------------------------------------------------
		assertThat(nextGenWorld.isAliveAt(Position.withXY(1, 4))).isTrue();
		
		assertThat(nextGenWorld.isAliveAt(Position.withXY(2, 0))).isFalse();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(2, 1))).isFalse();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(2, 2))).isTrue();
		assertThat(nextGenWorld.isAliveAt(Position.withXY(2, 3))).isTrue();
		
	}

}
