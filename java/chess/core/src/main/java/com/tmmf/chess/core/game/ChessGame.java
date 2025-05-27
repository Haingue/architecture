package com.tmmf.chess.core.game;

import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.piece.Piece;

import java.util.List;
import java.util.Optional;

public interface ChessGame {

    Board getBoard();
    Optional<Player> getWinner();
    void playNextMove(Point sourcePoint, Point destinationPoint) throws MoveNotPermitException, PieceNotFoundException;

    void resetBoard();
    void exportBoard();
    void reloadBoard(List<Piece> pieces);
}
