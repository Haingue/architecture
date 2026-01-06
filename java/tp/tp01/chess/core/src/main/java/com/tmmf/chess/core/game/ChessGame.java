package com.tmmf.chess.core.game;

import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.piece.Piece;

import java.util.List;
import java.util.Optional;

public interface ChessGame<K> {

    K createBoard();
    Board getBoard(K identifier);
    Optional<Player> getWinner(K identifier);
    MoveResult playNextMove(K identifier, Point sourcePoint, Point destinationPoint) throws MoveNotPermitException, PieceNotFoundException;

    void resetBoard(K identifier);
    void exportBoard(K identifier);
    void reloadBoard(K identifier, List<Piece> pieces);
}
