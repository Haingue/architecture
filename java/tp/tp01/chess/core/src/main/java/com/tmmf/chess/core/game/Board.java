package com.tmmf.chess.core.game;

import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Board {

    public static final int BOARD_SIZE = 8;
    
    private List<Optional<Piece>> cells;
    private List<Player> players;
    private int turn;

    public Board() {
        this.players = new ArrayList<>();
        this.resetBoard();
    }

    public void resetBoard(List<Player> players) {
        this.players = players;
        this.resetBoard();
    }

    public void resetBoard() {
        this.turn = 0;

        this.cells = new ArrayList<>();
        for (int lineIdx = 0; lineIdx < BOARD_SIZE; lineIdx++) {
            for (int columnsIdx = 0; columnsIdx < BOARD_SIZE; columnsIdx++) {
                Piece piece = null;

                if(lineIdx == 0 && columnsIdx == 4) {
                    piece = new King(Color.WHITE);
                } else if(lineIdx == 7 && columnsIdx == 3) {
                    piece = new King(Color.BLACK);
                }

                if(lineIdx == 0 && columnsIdx == 3) {
                    piece = new Queen(Color.WHITE);
                } else if(lineIdx == 7 && columnsIdx == 4) {
                    piece = new Queen(Color.BLACK);
                }

                else if(lineIdx == 0 && (columnsIdx == 2 || columnsIdx == 5)) {
                    piece = new Bishop(Color.WHITE);
                }
                else if(lineIdx == 7 && (columnsIdx == 2 || columnsIdx == 5)) {
                    piece = new Bishop(Color.BLACK);
                }

                else if(lineIdx == 0 && (columnsIdx == 1 || columnsIdx == 6)) {
                    piece = new Knight(Color.WHITE);
                }
                else if(lineIdx == 7 && (columnsIdx == 1 || columnsIdx == 6)) {
                    piece = new Knight(Color.BLACK);
                }

                else if(lineIdx == 0 && (columnsIdx == 0 || columnsIdx == 7)) {
                    piece = new Rook(Color.WHITE);
                }  else if(lineIdx == 7 && (columnsIdx == 0 || columnsIdx == 7)) {
                    piece = new Rook(Color.BLACK);
                }

                else if(lineIdx == 1) {
                    piece = new Pawn(Color.WHITE);
                } else if(lineIdx == 6) {
                    piece = new Pawn(Color.BLACK);
                }

                if (piece == null) {
                    this.cells.add(Optional.empty());
                } else {
                    this.cells.add(Optional.of(piece));
                }
            }
        }
    }

    public List<Optional<Piece>> getCells() {
        return cells;
    }

    private int getCellIndex(int lineIdx, int columnIdx) {
        return lineIdx * BOARD_SIZE + columnIdx;
    }
    private int getCellIndex(Point point) {
        return point.lineIdx() * BOARD_SIZE + point.columnIdx();
    }

    public Optional<Piece> getCell(int lineIdx, int columnIdx) {
        return cells.get(getCellIndex(lineIdx, columnIdx));
    }

    public Optional<Piece> setPiece(int lineIdx, int columnIdx, Piece piece) {
        return cells.set(getCellIndex(lineIdx, columnIdx), Optional.of(piece));
    }

    public boolean isInside(Point point) {
        return point.lineIdx() >= 0 && point.lineIdx() < BOARD_SIZE && point.columnIdx() >= 0 && point.columnIdx() < BOARD_SIZE;
    }

    public Optional<Piece> getPiece(Point coordinate) {
        return this.getCell(coordinate.lineIdx(), coordinate.columnIdx());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        if (players.isEmpty()) return null;
        return players.get(turn % players.size());
    }

    public int getTurn() {
        return turn;
    }

    public Optional<Player> getWinner() {
        List<Piece> kings = this.cells.stream().filter(Optional::isPresent).map(Optional::get).filter(p -> p instanceof King).toList();
        if (kings.size() > 1) return Optional.empty();
        Color firstKingColor = kings.stream().map(Piece::getColor).findFirst().orElse(null);
        return players.stream().filter(p -> p.getColor() == firstKingColor).findFirst();
    }

    public Set<Point> getAvailableMoves (Point source) throws PieceNotFoundException {
        Piece selectedPiece = getPiece(source).orElseThrow(() -> new PieceNotFoundException(source));
        return selectedPiece.getAvailableMoves(source, this);
    }

    public MoveResult move(Point source, Point destination) throws PieceNotFoundException, MoveNotPermitException {
        MoveResult result = new MoveResult(source, destination, this.turn);
        Set<Point> availableMoves = getAvailableMoves(source);
        if (!availableMoves.contains(destination)) throw new MoveNotPermitException(destination);

        int sourceIdx = this.getCellIndex(source);
        Optional<Piece> sourcePiece = this.cells.get(sourceIdx);
        int destinationIdx = this.getCellIndex(destination);
        Optional<Piece> destinationPiece = this.cells.get(destinationIdx);
        if (destinationPiece.isPresent()) {
            result.setResult(MoveResult.Result.TAKE);
        } else {
            result.setResult(MoveResult.Result.MOVE);
        }

        this.cells.set(sourceIdx, Optional.empty());
        this.cells.set(destinationIdx, sourcePiece);
        this.turn++;
        return result;
    }

}
