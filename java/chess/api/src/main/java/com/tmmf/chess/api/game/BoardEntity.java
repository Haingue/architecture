package com.tmmf.chess.api.game;

import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.piece.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
class BoardEntity {

    @Id
    private String id;
    private int cell1;
    private int cell2;
    private int cell3;
    private int cell4;
    private int cell5;
    private int cell6;
    private int cell7;
    private int cell8;
    private int cell9;
    private int cell10;
    private int cell11;
    private int cell12;
    private int cell13;
    private int cell14;
    private int cell15;
    private int cell16;
    private int cell17;
    private int cell18;
    private int cell19;
    private int cell20;
    private int cell21;
    private int cell22;
    private int cell23;
    private int cell24;
    private int cell25;
    private int cell26;
    private int cell27;
    private int cell28;
    private int cell29;
    private int cell30;
    private int cell31;
    private int cell32;
    private int cell33;
    private int cell34;
    private int cell35;
    private int cell36;
    private int cell37;
    private int cell38;
    private int cell39;
    private int cell40;
    private int cell41;
    private int cell42;
    private int cell43;
    private int cell44;
    private int cell45;
    private int cell46;
    private int cell47;
    private int cell48;
    private int cell49;
    private int cell50;
    private int cell51;
    private int cell52;
    private int cell53;
    private int cell54;
    private int cell55;
    private int cell56;
    private int cell57;
    private int cell58;
    private int cell59;
    private int cell60;
    private int cell61;
    private int cell62;
    private int cell63;
    private int cell64;

    public BoardEntity() {
    }

    public BoardEntity(String id, Board chessBoard) {
        this.id = id;
        Field[] fields = BoardEntity.class.getDeclaredFields();
        for (int idx = 0; idx < chessBoard.getCells().size(); idx++) {
            Field field = fields[idx];
            if (field.getName().startsWith("cell") && field.getType() == int.class) {
                field.setAccessible(true);
                try {
                    field.setInt(this, pieceToInteger(chessBoard.getCells().get(idx-1)));
                } catch (IllegalAccessException e) {
                    // TODO throw new RuntimeException(e);
                }
            }
        }
    }

    public Board mapToModel() throws IllegalAccessException {
        Board chessBoard = new Board();
        Field[] fields = BoardEntity.class.getDeclaredFields();
        for (int idx = 0; idx < chessBoard.getCells().size(); idx++) {
            Field field = fields[idx];
            if (field.getName().startsWith("cell") && field.getType() == int.class) {
                field.setAccessible(true);
                int value = field.getInt(this);
                chessBoard.getCells().set(idx-1, integerToPiece(value));
            }
        }
        return chessBoard;
    }

    private int pieceToInteger (Optional<Piece> piece) {
        if (piece.isEmpty()) {
            return 0;
        }
        Piece pieceValue = piece.get();
        char pieceChar = switch (pieceValue) {
            case King king -> 'K';
            case Pawn pawn -> 'P';
            case Bishop bishop -> 'B';
            case Rook rook -> 'R';
            case Knight knight -> 'C';
            case Queen queen -> 'Q';
            default -> throw new IllegalArgumentException("Invalid piece character: " + pieceValue);
        };
        return ((int) pieceChar) * (pieceValue.getColor() == Color.WHITE ? 1 : -1);
    }
    private Optional<Piece> integerToPiece (int value) {
        Color pieceColor = value > -1 ? Color.WHITE : Color.BLACK;
        char pieceChar = (char) (Math.abs(value));
        if (value == 0) {
            return Optional.empty();
        } else if (pieceChar == 'K') {
            return Optional.of(new King(pieceColor));
        } else if (pieceChar == 'P') {
            return Optional.of(new Pawn(pieceColor));
        } else if (pieceChar == 'B') {
            return Optional.of(new Bishop(pieceColor));
        } else if (pieceChar == 'R') {
            return Optional.of(new Rook(pieceColor));
        } else if (pieceChar == 'C') {
            return Optional.of(new Knight(pieceColor));
        } else if (pieceChar == 'Q') {
            return Optional.of(new Queen(pieceColor));
        }
        throw new IllegalArgumentException("Invalid piece character: " + pieceChar);
    }
}
