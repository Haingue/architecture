package com.tmmf.chess.core;

import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.game.Point;
import com.tmmf.chess.core.view.CliChessGame;

public class ChessCoreApplication {

	public static void main(String[] args) {
		CliChessGame chessGame = new CliChessGame();
		System.out.println(chessGame);

	}

}
