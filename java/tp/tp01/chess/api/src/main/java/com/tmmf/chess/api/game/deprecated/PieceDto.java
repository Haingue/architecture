package com.tmmf.chess.api.game.deprecated;

import com.tmmf.chess.core.game.Color;
import com.tmmf.chess.core.game.Point;
import com.tmmf.chess.core.piece.*;

import java.util.Optional;

public record PieceDto(
        Color color,
        String name,
        Point position
) {

    public static PieceDto of(Optional<Piece> piece, Point position) {
        return piece.map(value -> new PieceDto(value.getColor(), value.getClass().getName(), position))
                .orElseGet(() -> new PieceDto(null, null, position));
    }
    public static Piece from(PieceDto pieceDto) {
        return switch (pieceDto.name()) {
            case "King" -> new King(pieceDto.color);
            case "Pawn" -> new Pawn(pieceDto.color);
            case "Bishop" -> new Bishop(pieceDto.color);
            case "Rook" -> new Rook(pieceDto.color);
            case "Knight" -> new Knight(pieceDto.color);
            case "Queen" -> new Queen(pieceDto.color);
            default -> throw new IllegalArgumentException("Invalid piece character: " + pieceDto.name());
        };
    }

}
