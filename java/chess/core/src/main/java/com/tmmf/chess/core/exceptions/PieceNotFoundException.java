package com.tmmf.chess.core.exceptions;

import com.tmmf.chess.core.game.Point;

public class PieceNotFoundException extends Exception {
    public PieceNotFoundException(Point selectedPiece) {
        super("No piece in the selected cell (" + selectedPiece + ")");
    }
}
