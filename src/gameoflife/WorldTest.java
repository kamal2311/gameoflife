package gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WorldTest {

	World world = new World();

	@Test
	public void should_initialize() throws Exception {

		assertThat(world).isNotNull();

	}

	@Test
	public void should_set_a_cell_alive_at_a_position() throws Exception {

		
		Position position = new Position(1,1);

		world.setAlive(position);		
				
		assertThat(world.isAliveAt(new Position(1,1))).isTrue();	

	}
	
	@Test
	public void positions_are_identical_if_same_xy() throws Exception {
		
		assertThat(new Position(1,1)).isEqualTo(new Position(1,1));
		
	}
	
	@Test
	public void positions_are_not_identical_if_diff_xy() throws Exception {
		
		assertThat(new Position(1,1)).isNotEqualTo(new Position(1,2));
		
	}
	
	@Test
	public void should_report_a_dead_cell() throws Exception {
		
		Position emptyPosition = new Position(0,0);
		
		assertThat(world.isAliveAt(emptyPosition)).isFalse();
	}



}
