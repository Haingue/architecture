package com.tmmf.chess.core.view;

import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.game.*;
import com.tmmf.chess.core.piece.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CliChessGame implements ChessGame<Void> {

    private Board board;

    public CliChessGame() {
        this.board = new Board();
    }

    @Override
    public Void createBoard() {
        this.board = new Board();
        return null;
    }

    @Override
    public Board getBoard(Void identifier) {
        return this.board;
    }

    @Override
    public void resetBoard(Void identifier) {
        this.board.resetBoard();
    }

    @Override
    public void exportBoard(Void identifier) {
        System.out.println(this);
    }

    @Override
    public void reloadBoard(Void identifier, List<Piece> pieces) {
        this.getBoard(identifier).getCells().clear();
        pieces.stream().map(Optional::of).forEach(this.getBoard(identifier).getCells()::add);
    }

    @Override
    public MoveResult playNextMove(Void identifier, Point sourcePoint, Point destinationPoint) throws MoveNotPermitException, PieceNotFoundException {
        return this.board.move(sourcePoint, destinationPoint);
    }

    @Override
    public Optional<Player> getWinner(Void identifier) {
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
