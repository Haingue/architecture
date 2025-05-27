package com.tmmf.chess.api.game;

import com.tmmf.chess.api.exception.ChessApiException;
import com.tmmf.chess.api.exception.GameNotFoundException;
import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.exceptions.PieceNotFoundException;
import com.tmmf.chess.core.game.*;
import com.tmmf.chess.core.piece.Piece;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ChessServiceImpl implements ChessGame<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChessServiceImpl.class);

    private final BoardRepository boardRepository;

    public ChessServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String createBoard() {
        LOGGER.debug("Create board");
        Board boardModel = new Board();

        Random random = new Random();
        String boardCode = IntStream.range(0, 5).mapToObj(i -> String.valueOf((char) ('a' +random.nextInt(25))))
                .collect(Collectors.joining());
        BoardEntity boardEntity = new BoardEntity(boardCode, boardModel);
        boardRepository.save(boardEntity);

        return boardCode;
    }

    @Override
    public Board getBoard(String identifier) {
        return getBoardModel(identifier);
    }

    @Override
    public Optional<Player> getWinner(String identifier) {
        return Optional.empty();
    }

    @Override
    public MoveResult playNextMove(String identifier, Point sourcePoint, Point destinationPoint) throws MoveNotPermitException, PieceNotFoundException {
        LOGGER.debug("Playing next move {}", identifier);
        Board boardModel = getBoardModel(identifier);
        MoveResult result = boardModel.move(sourcePoint, destinationPoint);

        BoardEntity boardEntity = new BoardEntity(identifier, boardModel);
        boardRepository.save(boardEntity);
        return result;
    }

    @Override
    public void resetBoard(String identifier) {
        LOGGER.debug("Reset board {}", identifier);
        Board boardModel = getBoardModel(identifier);
        boardModel.resetBoard();
    }

    @Override
    public void exportBoard(String identifier) {
        Board boardModel = getBoardModel(identifier);
        LOGGER.debug("Export board: {}", boardModel);
    }

    @Override
    public void reloadBoard(String identifier, List<Piece> pieces) {
        LOGGER.debug("Reload board: {}", pieces);
        Board boardModel = getBoardModel(identifier);
        boardModel.getCells().clear();
        boardModel.getCells().addAll(pieces.stream().map(Optional::of).toList());
    }

    private Board getBoardModel(String identifier) {
        Board boardModel = this.boardRepository.findById(identifier)
                .map(boardEntity -> {
                    try {
                        return boardEntity.mapToModel();
                    } catch (IllegalAccessException e) {
                        throw new ChessApiException(e);
                    }
                })
                .orElseThrow(() -> new GameNotFoundException(identifier));
        return boardModel;
    }
}
