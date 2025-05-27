package com.tmmf.chess.core;

import com.tmmf.chess.core.game.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class ChessCoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void shouldCreateOneBoard() {
		Board board = new Board();
		Assertions.assertThat(board).isNotNull();
		Assertions.assertThat(board.getCells()).isNotNull();
		Assertions.assertThat(board.getCells()).isNotEmpty();
	}

}
