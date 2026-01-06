package com.tmmf.chess.core.exceptions;

import com.tmmf.chess.core.game.Point;

public class MoveNotPermitException extends Exception {
    public MoveNotPermitException(Point point) {
        super("This move is not permitted ("+ point +")");
    }
}
