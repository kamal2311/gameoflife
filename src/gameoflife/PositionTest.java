package gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class PositionTest {

	@Test
	public void positions_are_identical_if_same_xy() throws Exception {

		assertThat(Position.withXY(1, 1)).isEqualTo(Position.withXY(1, 1));

	}

	@Test
	public void positions_are_not_identical_if_diff_xy() throws Exception {

		assertThat(Position.withXY(1, 1)).isNotEqualTo(Position.withXY(1, 2));

	}
	
	@Test
	public void should_answer_neighbors() throws Exception {
		
		Collection<Position> neighbors= Position.withXY(1, 1).neighbors();		
		assertThat(neighbors.size()).isEqualTo(8);
	}

}
