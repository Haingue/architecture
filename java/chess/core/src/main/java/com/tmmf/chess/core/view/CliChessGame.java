package com.tmmf.chess.core.view;

import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.ChessGame;
import com.tmmf.chess.core.game.Player;
import com.tmmf.chess.core.game.Point;
import com.tmmf.chess.core.piece.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CliChessGame implements ChessGame {

    private final Board board;

    public CliChessGame() {
        this.board = new Board();
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public void resetBoard() {
        this.board.resetBoard();
    }

    @Override
    public void exportBoard() {
        System.out.println(this);
    }

    @Override
    public void reloadBoard(List<Piece> pieces) {
        this.getBoard().getCells().clear();
        pieces.stream().map(Optional::of).forEach(this.getBoard().getCells()::add);
    }

    @Override
    public void playNextMove(Point sourcePoint, Point destinationPoint) throws MoveNotPermitException, PieceNotFoundException {
        this.board.move(sourcePoint, destinationPoint);
    }

    @Override
    public Optional<Player> getWinner() {
        return this.board.getWinner();
    }

    private char toChar (Piece piece) {
        if (piece instanceof King king) {
            return 'K';
        } else if (piece instanceof Pawn pawn) {
            return 'P';
        } else if (piece instanceof Bishop bishop) {
            return 'B';
        } else if (piece instanceof Rook rook) {
            return 'R';
        } else if (piece instanceof Knight knight) {
            return 'C';
        } else if (piece instanceof Queen queen) {
            return 'Q';
        }
        return '?';
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("   .");
        Stream.iterate(1, i -> i + 1).limit(8).map(v -> (char) (v + 'A' - 1))
                .forEachOrdered(header -> stringBuilder.append(header).append('.'));
        stringBuilder.append('\n');
        for (int idx = 0; idx < this.board.getCells().size(); idx++) {
            Optional<Piece> piece = this.board.getCells().get(idx);

            if (idx % 8 == 0) {
                stringBuilder.append((idx / 8) + 1).append(": ").append('|');
            }
            stringBuilder
                    .append(piece.map(this::toChar).orElse('.'))
                    .append('|');
            if (idx % 8 == 7) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
